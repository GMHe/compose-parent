package cn.compose.sync.utils;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Hashtable;

@Slf4j
public class QRCodeUtils {

    /**
     * 二维码解码
     *
     * @param url
     * @return
     */
    public static String decode(String url) {
        try {
            URL urls = new URL(url);
            BufferedImage image;
            image = toBufferedImage(Toolkit.getDefaultToolkit().getImage(urls));
            if (image == null) {
                return null;
            }
            BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result result;
            Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            result = new MultiFormatReader().decode(bitmap, hints);
            String resultStr = result.getText();
            return resultStr;
        } catch (Exception e) {

        }
        return "";
    }

    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(
                    image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            log.info("image to BufferedImage error");
        }

        if (bimage == null) {
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
        Graphics g = bimage.createGraphics();

        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;

    }

}
