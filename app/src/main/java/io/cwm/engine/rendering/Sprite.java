package io.cwm.engine.rendering;

import io.cwm.engine.logic.Updatable;
import io.cwm.engine.logic.math.Vec2;

public class Sprite extends ImagePanel implements Updatable {


    public final SpriteSheet ANIMATION;
    private int currentFrame = 0;
    public int getCurrentFrame() {return currentFrame / fps;}

    public final int fps;

    /**
     * Note that {@param fps} dictates the number of update calls that must pass for the ANIMATION frame to update, not an actual time difference.
     * @param asset
     * @param fps
     * @param position
     */
    public Sprite(SpriteSheet asset, int fps) {
        super(new Vec2(0, 0));
        ANIMATION = asset;
        setBounds(0, 0, asset.getDim().xi(), asset.getDim().yi());
        image = ANIMATION.getFrame(0);
        this.fps = fps;
    }

    /**
     * Note that {@param fps} dictates the number of update calls that must pass for the ANIMATION frame to update, not an actual time difference.
     * @param asset
     * @param fps
     * @param position
     */
    public Sprite(SpriteSheet asset, int fps, Vec2 position) {
        super(position);
        ANIMATION = asset;
        setBounds(position.xi(), position.yi(), asset.getDim().xi(), asset.getDim().yi());
        image = ANIMATION.getFrame(0);
        this.fps = fps;
    }

    /**
     *  When overriding update, you MUST call {@code super.update();} so that the frames advance.
     *  Ideally, this is done before any other behavior in {@code update()}
     */
    @Override
    public void update() {
        currentFrame ++;
        currentFrame %= (ANIMATION.getLength() * fps);
        image = ANIMATION.getFrame(currentFrame / fps);
    }
}
