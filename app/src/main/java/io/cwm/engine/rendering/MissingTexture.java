package io.cwm.engine.rendering;

import io.cwm.engine.logic.math.Vec2;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MissingTexture {
    public static BufferedImage create(Vec2 size, int cellSize) {
        BufferedImage img = new BufferedImage(
                size.xi(), size.yi(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = img.createGraphics();

        Color magenta = new Color(255, 0, 255);
        Color black = Color.BLACK;

        for (int y = 0; y < size.yi(); y += cellSize) {
            for (int x = 0; x < size.xi(); x += cellSize) {
                boolean even = ((x + y) / cellSize) % 2 == 0;
                g.setColor(even ? magenta : black);
                g.fillRect(x, y, cellSize, cellSize);
            }
        }

        g.dispose();
        return img;
    }
}
