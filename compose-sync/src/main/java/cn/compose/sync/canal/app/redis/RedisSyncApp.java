package cn.compose.sync.canal.app.redis;

import cn.compose.sync.canal.app.DataSyncApp;
import cn.compose.sync.canal.config.CanalConfig;
import cn.compose.sync.canal.event.EventInfo;
import cn.compose.sync.canal.service.EventHandlerFactory;
import cn.compose.sync.canal.service.IEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据同步到redis
 * @author maowei
 * @package_name com.vlinklink.sync.app
 * @date 2020/9/7
 * @time 14:45
 */
@Slf4j
@Component
public class RedisSyncApp extends DataSyncApp {

    @Autowired
    private EventHandlerFactory factory;

    @Autowired
    private CanalConfig canalConfig;

    @Override
    public IEventHandler createHandlerByEventType(EventInfo info) {
        Boolean custom = canalConfig.getCustom();
        if (custom){
            //自定义的表处理 , 即不同表有不同的处理逻辑, 也有可能共用一个处理逻辑
            return factory.getHandler(info.getUnionKey());
        }else{
            //全局的处理
            return factory.getHandler(EventHandlerFactory.createUnionKey(info.getEventType()));
        }
    }

}
