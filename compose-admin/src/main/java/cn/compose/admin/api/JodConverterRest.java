//package cn.compose.admin.api;
//
//import cn.compose.admin.biz.JodConverterBiz;
//import cn.compose.admin.jodconverter.WordToPdf;
//import lombok.extern.slf4j.Slf4j;
//import org.jodconverter.core.DocumentConverter;
//import org.jodconverter.core.office.OfficeException;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//
//@RestController
//@Slf4j
//@RequestMapping("/admin/file")
//public class JodConverterRest {
//
//    @Resource
//    private DocumentConverter converter;
//
//    @Resource
//    private WordToPdf wordToPdf;
//
//    @Resource
//    private JodConverterBiz jodConverterBiz;
//
//    private String newFilePath = "../temp";
//    private String newFileName = "tests.pdf";
//
//    @GetMapping("/toPdf")
//    public void fileToPdf(HttpServletResponse response) {
//        String filePath = "/file/test.docx";
//        try {
//            ClassPathResource resource = new ClassPathResource(filePath);
//            if (!resource.exists()) {
//                log.warn("{}文件不存在！", filePath);
//                return;
//            }
//            File file = resource.getFile();
//            InputStream in;
//            OutputStream out;
//            File tempFile = new File(newFilePath);
//            if (!tempFile.exists()) {
//                tempFile.mkdirs();
//            }
//            File newFile = new File(tempFile.getAbsolutePath() + File.separator + newFileName);
//            // 清除首部空白行
//            response.reset();
//            converter.convert(file).to(newFile).execute();
//            in = new BufferedInputStream(new FileInputStream(newFile));
//            byte[] b = new byte[in.available()];
//            in.read(b);
//            out = response.getOutputStream();
//            out.write(b);
//            in.close();
//            out.flush();
//            out.close();
//        } catch (OfficeException e) {
//            log.error("OfficeException", e);
//        } catch (FileNotFoundException e) {
//            log.error("FileNotFoundException", e);
//        } catch (IOException e) {
//            log.error("IOException", e);
//        }
//    }
//
//
//    @GetMapping("/onlinePreview")
//    public void onlinePreview(@RequestParam("url") String url, HttpServletResponse response) throws Exception {
//        String filePath = "/file/test.docx";
//
//        jodConverterBiz.onlinePreview(filePath, response);
//    }
//
//    @GetMapping("/localPreview")
//    public void localPreview(HttpServletResponse response) throws Exception {
//        String filePath = "/file/test.docx";
//        String suffic = ".docx";
//
//        jodConverterBiz.onlineLocalFle(filePath, suffic, response);
//    }
//}
