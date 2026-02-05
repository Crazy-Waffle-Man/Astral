package io.cwm.engine.rendering;

import io.cwm.engine.game_logic.StartupHandler;
import io.cwm.engine.helpers.ImageDimensionGetter;
import io.cwm.engine.helpers.ResourceLocator;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.util.logging.Level;

public class StaticImage extends Image {
    private ResourceLocator RESOURCE;
    private final int WIDTH;
    private final int HEIGHT;
    private BufferedImage loadedImage;
    public StaticImage(ResourceLocator resourceLocator) {
        RESOURCE = resourceLocator;
        Dimension staticImageDimension = null;
        try {
            staticImageDimension = ImageDimensionGetter.getImageDimensionsFromResource(RESOURCE);
        } catch (IOException e) {
            StartupHandler.engineLogger.log(Level.WARNING, "No image found at [" + RESOURCE.getPath() + "]. Replacing with missing texture.");
            // replace image with missing texture to avoid a total crash
            RESOURCE = ResourceLocator.MISSING_TEXTURE;
            // RESOURCE.getFile() will never return null when used here, we can safely call this without worrying about the IOException
            try {
                staticImageDimension = ImageDimensionGetter.getImageDimensionsFromResource(RESOURCE);
            } catch (IOException ex) {
                StartupHandler.engineLogger.log(Level.SEVERE, "Missing texture not found. This error cannot be recovered.");
                throw new RuntimeException(ex);
            }
        } finally {
            assert staticImageDimension != null;
            WIDTH = staticImageDimension.width;
            HEIGHT = staticImageDimension.height;
        }

        this.loadImage();
    }

    public ResourceLocator getResource() {
        return RESOURCE;
    }

    @Override
    public int getWidth(ImageObserver observer) {
        return WIDTH;
    }

    @Override
    public int getHeight(ImageObserver observer) {
        return HEIGHT;
    }

    public void loadImage() {
        this.loadedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public ImageProducer getSource() {
        return null;
    }

    @Override
    public Graphics getGraphics() {
        return this.loadedImage.getGraphics();
    }

    @Override
    public Object getProperty(String name, ImageObserver observer) {
        return null;
    }
}
