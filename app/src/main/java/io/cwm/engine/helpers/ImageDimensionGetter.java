package io.cwm.engine.helpers;

import io.cwm.engine.game_logic.StartupHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;

public class ImageDimensionGetter  {
    public static Dimension getImageDimensionsFromResource(ResourceLocator resource) throws IOException {
        BufferedImage image;
        image = ImageIO.read(resource.getResourceAsStream());
        return new Dimension(image.getWidth(), image.getHeight());
    }
}
