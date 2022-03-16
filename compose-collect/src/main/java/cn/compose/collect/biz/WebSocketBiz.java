package cn.compose.collect.biz;

import cn.compose.collect.base.IMsgHandler;
import cn.compose.collect.constant.Constants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName WebSocketBiz
 * @Description 类描述
 * @Author hgm
 * @Date 2021/9/29 18:22
 * @Version 1.0
 **/
@Service
@Slf4j
public class WebSocketBiz implements IMsgHandler {

    @Override
    public void processMsg(String message) {
        log.info("处理device消息刷新缓存");
        //处理消息刷新缓存
        JSONObject json= JSON.parseObject(message);
        String syncType=json.getString("syncType");
        //平台信息
        if(Constants.SYNC_MGR_TYPE_PLATFORMINFO.equals(syncType)){
//            SysPlatformInfoListVO sysPlatformInfoListVO=new SysPlatformInfoListVO();
//            sysPlatformInfoListVO.setPlatFlag(json.getString("platFlag"));
//            sysPlatformInfoListVO.setPlatRel(json.getString("platRel"));
//            sysPlatformInfoService.selectDbPlatfrom(sysPlatformInfoListVO);
        }
        //设备信息
        else if(Constants.SYNC_MGR_TYPE_DEVICE.equals(syncType)){

        }
    }

    @Override
    public String getHandlerType() {
        return Constants.SYNC_MGR_UPDATE_LIST_CACHE;
    }
}
