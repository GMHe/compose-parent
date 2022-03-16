package cn.compose.alert.biz;

import cn.compose.alert.apiFeign.AccountFeignRest;
import cn.compose.alert.apiFeign.StorageFeignRest;
import cn.compose.alert.base.ApiCodes;
import cn.compose.alert.base.Response;
import cn.compose.alert.base.ResponseBuilder;
import cn.compose.alert.biz.ibiz.IAlertSeataBiz;
import cn.compose.alert.dao.TOrderMapper;
import cn.compose.alert.entity.Storage;
import cn.compose.alert.entity.TOrder;
import cn.hutool.core.date.DateUtil;
import com.contive.plugin.did.DistributedId;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.core.model.TransactionManager;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.TransactionManagerHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName AlertSeataBiz
 * @Description 类描述
 * @Author hgm
 * @Date 2021/11/15 15:35
 * @Version 1.0
 **/
@Service
@Slf4j
public class AlertSeataBiz implements IAlertSeataBiz {

    @Resource
    AccountFeignRest accountFeignRest;
    @Resource
    StorageFeignRest storageFeignRest;
    @Resource
    DistributedId distributedId;
    @Resource
    TOrderMapper tOrderMapper;

    @GlobalTransactional(name = "alert-create-order",rollbackFor = Exception.class)
    @Override
    public Response create(String userId,Long storageId,Long num){
        Response accResult=new Response();
        String xid = RootContext.getXID();
        TransactionManager manager = TransactionManagerHolder.get();
        try {

            log.info("扣减库存。。。。。");
            accResult = storageFeignRest.deduct(storageId, num);
            if (!ApiCodes.OK.equals(accResult.getCode())) {
                manager.rollback(xid);
                log.info("xid:{},扣减库存时事务回滚",xid);
                return ResponseBuilder.error(ApiCodes.INTERNAL_ERROR, accResult.getMsg());
            }


            log.info("扣减余额。。。。。");
            Response<Storage> response = storageFeignRest.getStorageById(storageId);
            Storage storage = response.getData();
            accResult = accountFeignRest.deduct(userId, storage.getPrice() * num);

            if (!ApiCodes.OK.equals(accResult.getCode())) {
                manager.rollback(xid);
                log.info("xid:{},扣减余额时事务回滚",xid);
                return ResponseBuilder.error(ApiCodes.INTERNAL_ERROR, accResult.getMsg());
            }

            log.info("创建订单。。。。。");
            TOrder order = new TOrder();
            order.setId(distributedId.nextId());
            order.setUserId(userId);
            order.setProductId(storageId);
            order.setNum(num);
            order.setStatus(2);
            order.setCreateTime(new Date());
            tOrderMapper.insert(order);
            return ResponseBuilder.ok(order, "创建订单成功！");

        }catch (Exception e){
            try {
                log.info("xid:{},创建订单事务回滚",xid);
                manager.rollback(xid);
            } catch (TransactionException te) {
                log.error("创建订单事务回滚异常：",te);
            }
            log.error("创建订单异常：",e);
        }
         return ResponseBuilder.error(ApiCodes.INTERNAL_ERROR,accResult.getMsg());
    }
}
