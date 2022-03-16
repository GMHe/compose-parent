package cn.compose.alert.biz;

import cn.compose.alert.dao.OrderInfoMapper;
import cn.compose.alert.dao.ProvOrderMapper;
import cn.compose.alert.base.ApiCodes;
import cn.compose.alert.dao.ProvOrderDao;
import cn.compose.alert.dao.ResourceDao;
import cn.compose.alert.dto.ResourceDTO;
import cn.compose.alert.entity.OrderInfo;
import cn.compose.alert.entity.ProvOrder;
import cn.compose.alert.entity.ProvOrderExample;
import cn.compose.alert.pay.FMPayConfig;
import cn.compose.alert.utils.MD5Utils;
import cn.compose.alert.utils.OkHttpUtils;
import cn.compose.alert.vo.OrderVO;
import com.alibaba.fastjson.JSONObject;
import com.contive.plugin.did.DistributedId;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName OrderBiz
 * @Description 类描述
 * @Author hgm
 * @Date 2021/8/4 19:18
 * @Version 1.0
 **/
@Service
@Slf4j
public class OrderBiz {
    @Resource
    private ProvOrderMapper provOrderMapper;
    @Resource
    private ResourceDao resourceDao;
    @Resource
    private ProvOrderDao provOrderDao;
    @Resource
    private DistributedId distributedId;
    @Resource
    private OrderInfoMapper orderInfoMapper;

    public OrderVO createOrder(String userId, String resourceId, String payType) {
        ResourceDTO resourceDTO = resourceDao.getResourceByResourceId(resourceId);
        String orderNumber = String.valueOf(distributedId.nextId());
        ProvOrder provOrder = new ProvOrder();
        provOrder.setUserId(Long.parseLong(userId));
        provOrder.setResourceId(Long.parseLong(resourceId));
        provOrder.setOrderNumber(orderNumber);
        provOrder.setResourceId(Long.parseLong(resourceId));
        provOrder.setPayAmount(resourceDTO.getPrice());
        provOrder.setResorceAmount(resourceDTO.getPrice());
        provOrder.setValidTime(5);
        provOrder.setStatus(0);
        provOrder.setCreateTime(new Date());

        provOrderMapper.insert(provOrder);

        provOrder = createFMOrder(provOrder, resourceDTO, payType);
        OrderVO orderVO=new OrderVO();
        orderVO.setBuyerId(Long.getLong(userId));
        orderVO.setPayUrl(provOrder.getPayUrl());
        return orderVO;
    }


    private ProvOrder createFMOrder(ProvOrder provOrder, ResourceDTO resourceDTO, String payType) {
        ProvOrder order=new ProvOrder();
        try {
            FMPayConfig fmPayConfig = new FMPayConfig();
            Map<String, Object> params = new HashMap<>();
            StringBuffer restUrl = new StringBuffer();
            restUrl.append(fmPayConfig.getRestUrl());
            restUrl.append("&merchantNum=").append(fmPayConfig.getMerchantNum());
            restUrl.append("&orderNo=").append(provOrder.getOrderNumber());
            restUrl.append("&amount=").append(provOrder.getPayAmount());
            restUrl.append("&returnUrl=").append(fmPayConfig.getReturnUrl());
            restUrl.append("&notifyUrl=").append(fmPayConfig.getNotifyUrl());
            restUrl.append("&payType=").append(payType);
            restUrl.append("&subject=").append(resourceDTO.getResourceName());
            restUrl.append("&body=").append(resourceDTO.getDescribe());
            restUrl.append("&payDuration=").append(5);
            restUrl.append("&sign=").append(MD5Utils.sign(provOrder.getOrderNumber(), provOrder.getPayAmount(), fmPayConfig.getNotifyUrl()));
            Map<String, String> headers = new HashMap<>();
            Response response = null;

            response = OkHttpUtils.postJson(restUrl.toString(), headers, params);
            if (response != null && response.isSuccessful()) {
                JSONObject resultJson = JSONObject.parseObject(response.body().string());
                if (ApiCodes.HTTP_RES_CODE_200.equals(resultJson.getString("code"))) {
                    log.info("创建订单成功！");
                    JSONObject result = resultJson.getJSONObject("data");
                    System.err.println("支付地址："+result.getString("payUrl"));
                    //修改商户订单号
                    order.setPlatformOrderNo(result.getString("id"));
                    order.setPayUrl(result.getString("payUrl"));
                    order.setOrderNumber(provOrder.getOrderNumber());
                    provOrderDao.updateProvOrder(order);

                    return order;
                }
            }
        } catch (IOException e) {
            log.error("error.", e);
        }
        return order;
    }

    @Transactional
    public void updateOrder(String orderNo,String platformOrderNo, BigDecimal actualPayAmount){
        ProvOrder provOrder=new ProvOrder();
        provOrder.setPlatformOrderNo(platformOrderNo);
        provOrder.setPayAmountActual(actualPayAmount);
        provOrder.setOrderNumber(orderNo);
        provOrder.setPayTime(new Date());
        provOrder.setStatus(2);
        provOrderDao.updateProvOrder(provOrder);

        ProvOrderExample provOrderExample=new ProvOrderExample();
        provOrderExample.createCriteria().andOrderNumberEqualTo(orderNo);
        List<ProvOrder> provOrderList = provOrderMapper.selectByExample(provOrderExample);
        provOrder = provOrderList.get(0);
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setCreateTime(new Date());
        orderInfo.setOrderNumber(provOrder.getOrderNumber());
        orderInfo.setOuterTradeNo(provOrder.getPlatformOrderNo());
        orderInfo.setResourceId(provOrder.getResourceId());
        orderInfo.setPayAmount(provOrder.getPayAmountActual());
        orderInfo.setTotalAmount(provOrder.getResorceAmount());
        orderInfo.setPayTime(provOrder.getPayTime());
        orderInfo.setUserId(provOrder.getUserId());
        orderInfo.setStatus(provOrder.getStatus());
        orderInfoMapper.insert(orderInfo);
    }
}
