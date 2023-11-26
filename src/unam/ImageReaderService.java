package unam;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageReaderService implements IImageReaderService {
    public BufferedImage readImage(String fileSource) throws IOException {
        return ImageIO.read(new File(fileSource));
    }
}