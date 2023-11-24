package unam;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageReader implements IImageReader {
    public BufferedImage readImage(String fileSource) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(fileSource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}