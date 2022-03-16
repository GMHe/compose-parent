//package cn.compose.admin.jodconverter;
//
//import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
//import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.net.ConnectException;
//
//@Component
//public class WordToPdf {
//
//    /**
//     * @param fromPath 初始文件的路径，例：C:\Users\...\Desktop\test\1.doc
//     * @param toPath   转换后的存储路径，例：C:\Users\...\Desktop\test\
//     * @param name     转换后的文件保存名字
//     */
//    public void convert(String fromPath, String toPath, String name) {
//
//        File fromFile = new File(fromPath);
//        if (!fromFile.exists()) {
//            System.out.println("源文件不存在");
//            return;
//        }
//        File toFile = new File(toPath + name + ".pdf");
//
////        SocketOpenOfficeConnection connection = new SocketOpenOfficeConnection("0.0.0.0", 8100);
//        SocketOpenOfficeConnection connection = new SocketOpenOfficeConnection("0.0.0.0", 8100);
//        try {
//            connection.connect();
//            System.out.println("获取连接成功！");
//        } catch (ConnectException e) {
//            System.out.println("获取连接失败！");
//            e.printStackTrace();
//        }
////        OpenOfficeDocumentConverter converter = new OpenOfficeDocumentConverter(connection);
//        StreamOpenOfficeDocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
//        System.out.println("开始转换！");
//        converter.convert(fromFile, toFile);
//        System.out.println("转换成功！");
//        System.out.println("关闭连接！");
//        connection.disconnect();
//    }
//}