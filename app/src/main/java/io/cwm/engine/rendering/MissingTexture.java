package io.cwm.engine.rendering;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MissingTexture {
    public static BufferedImage create(int size, int cellSize) {
        BufferedImage img = new BufferedImage(
                size, size, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = img.createGraphics();

        Color magenta = new Color(255, 0, 255);
        Color black = Color.BLACK;

        for (int y = 0; y < size; y += cellSize) {
            for (int x = 0; x < size; x += cellSize) {
                boolean even = ((x + y) / cellSize) % 2 == 0;
                g.setColor(even ? magenta : black);
                g.fillRect(x, y, cellSize, cellSize);
            }
        }

        g.dispose();
        return img;
    }
}
