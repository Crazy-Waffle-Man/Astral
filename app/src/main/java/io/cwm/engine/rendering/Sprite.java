package io.cwm.engine.rendering;

import io.cwm.engine.logic.Updatable;
import io.cwm.engine.logic.math.Vec2;

public class Sprite extends ImagePanel implements Updatable {


    private final SpriteSheet animation;
    int currentFrame = 0;

    public Sprite(SpriteSheet asset) {
        super(new Vec2(0, 0));
        animation = asset;
        setBounds(0, 0, asset.getDim().xi(), asset.getDim().yi());
        image = animation.getFrame(0);
    }
    public Sprite(SpriteSheet asset, Vec2 position) {
        super(position);
        animation = asset;
        setBounds(position.xi(), position.yi(), asset.getDim().xi(), asset.getDim().yi());
        image = animation.getFrame(0);
    }

    /**
     *  When overriding update, you MUST call {@code super.update()} so that the frames advance.
     */
    @Override
    public void update() {
        currentFrame++;
        currentFrame %= animation.getLength();
        image = animation.getFrame(currentFrame);
    }
}
