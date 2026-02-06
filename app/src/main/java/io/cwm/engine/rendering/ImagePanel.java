package io.cwm.engine.rendering;

import io.cwm.engine.logic.Drawable;
import io.cwm.engine.logic.math.Vec2;
import io.cwm.engine.resources.ResourceLocator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImagePanel extends JPanel implements Drawable {
    protected BufferedImage image;
    public Vec2 pos;
    private final ResourceLocator resource;
    protected ImagePanel(Vec2 position) {
        pos = position;
        resource = null;
    }
    public ImagePanel(ResourceLocator resourceLocator) {
        pos = new Vec2(0, 0);
        resource = resourceLocator;
        try {
            image = ImageIO.read(resource.getResource());
        } catch (IllegalArgumentException | IOException e) {
            setImageToMissing();
        }
        setBounds(pos.xi(), pos.yi(), image.getWidth(), image.getHeight());
    }

    public ImagePanel(ResourceLocator resourceLocator, Vec2 position) {
        pos = position;
        resource = resourceLocator;
        try {
            image = ImageIO.read(resource.getResource());
        } catch (IllegalArgumentException | IOException e) {
            setImageToMissing();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
    }

    private void setImageToMissing() {
        image = MissingTexture.create(new Vec2(100, 100), 10);
        setBounds(pos.xi(), pos.yi(), 100, 100);
    }

    @Override
    public void draw() {
        this.repaint();
    }
}
