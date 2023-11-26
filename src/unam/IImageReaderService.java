package unam;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface IImageReaderService {
    public BufferedImage readImage(String filesource) throws IOException;
}