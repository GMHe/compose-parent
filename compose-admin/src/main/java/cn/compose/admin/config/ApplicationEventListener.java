package cn.compose.admin.config;

import com.contive.plugin.did.DistributedIdFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.Watchdog;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

/**
 * Spring事件监听类
 */
@Slf4j
@Component
public class ApplicationEventListener implements ApplicationListener<ApplicationEvent> {

    /**
     * 这里监听容器事件
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {
            //server start
            log.info("                                                   __                 __   \n" +
                    "  ______ ______________  __ ___________    _______/  |______ ________/  |_ \n" +
                    " /  ___// __ \\_  __ \\  \\/ // __ \\_  __ \\  /  ___/\\   __\\__  \\\\_  __ \\   __\\\n" +
                    " \\___ \\\\  ___/|  | \\/\\   /\\  ___/|  | \\/  \\___ \\  |  |  / __ \\|  | \\/|  |  \n" +
                    "/____  >\\___  >__|    \\_/  \\___  >__|    /____  > |__| (____  /__|   |__|  \n" +
                    "     \\/     \\/                 \\/             \\/            \\/             ");
        } else if (event instanceof ContextStoppedEvent) {
            // server stop
            log.info("                                                   __                 \n" +
                    "  ______ ______________  __ ___________    _______/  |_  ____ ______  \n" +
                    " /  ___// __ \\_  __ \\  \\/ // __ \\_  __ \\  /  ___/\\   __\\/  _ \\\\____ \\ \n" +
                    " \\___ \\\\  ___/|  | \\/\\   /\\  ___/|  | \\/  \\___ \\  |  | (  <_> )  |_> >\n" +
                    "/____  >\\___  >__|    \\_/  \\___  >__|    /____  > |__|  \\____/|   __/ \n" +
                    "     \\/     \\/                 \\/             \\/              |__|    ");
            DistributedIdFactory.stop();
            Watchdog watchdog = new Watchdog(30000);
            Thread thread = Thread.currentThread();
            watchdog.addTimeoutObserver(w -> thread.interrupt());
            watchdog.start();
            try {
                //耗时操作
                watchdog.stop();
            } catch (Exception e) {
                log.error("error stop Watchdog", e);
            } finally {
                watchdog.stop();
            }
        }
    }

}