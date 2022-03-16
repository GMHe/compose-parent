//package cn.compose.sync;
//
//import cn.compose.sync.biz.SyncBiz;
//import javafx.application.Application;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//public class SynaBizTest {
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    private SyncBiz syncBiz;
//
//    @Test
//    public void task01() {
//        long now = System.currentTimeMillis();
//        logger.info("[task01][开始执行]");
//
//        syncBiz.execute01();
//        syncBiz.execute02();
//
//        logger.info("[task01][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
//    }
//
//
//    @Test
//    public void task02() {
//        long now = System.currentTimeMillis();
//        logger.info("[task02][开始执行]");
//
//        syncBiz.execute01Async();
//        syncBiz.execute02Async();
//
//        logger.info("[task02][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
//    }
//
//}