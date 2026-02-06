package io.cwm.engine.rendering;

import io.cwm.engine.logic.math.Vec2;
import io.cwm.engine.resources.ResourceLocator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class SpriteSheet {
    private final BufferedImage[] frames;
    public final ResourceLocator resource;
    private final Vec2 size;
    public SpriteSheet(ResourceLocator resource, Vec2 dim) {
        this.resource = resource;
        ArrayList<BufferedImage> constructorFrames = new ArrayList<>();
        BufferedImage img;
        try {
            img = ImageIO.read(resource.getResource());
        } catch (IOException | IllegalArgumentException e) {
            img = MissingTexture.create(dim, dim.xi() / 5);
        }
        for (int i = 0; i < img.getWidth() / dim.xi(); i++) {
            constructorFrames.add(
                    img.getSubimage(
                            dim.xi() * i,
                            0,
                            dim.xi(),
                            dim.yi()
                    )
            );
        }
        frames = constructorFrames.toArray(BufferedImage[]::new);
        size = dim;
    }

    public BufferedImage getFrame(int index) {
        return frames[index];
    }
    public int getLength() {return frames.length;}
    public Vec2 getDim() {return size;}
}
