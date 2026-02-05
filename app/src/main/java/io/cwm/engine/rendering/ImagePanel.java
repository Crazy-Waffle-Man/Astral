package io.cwm.engine.rendering;

import io.cwm.engine.logic.Drawable;
import io.cwm.engine.resources.ResourceLocator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImagePanel extends JPanel implements Drawable {
    private BufferedImage image;
    private final ResourceLocator resource;
    public ImagePanel(ResourceLocator resourceLocator) {
        resource = resourceLocator;
        try {
            image = ImageIO.read(resource.getResource());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
    }

    @Override
    public void draw() {
        this.repaint();
    }
}
