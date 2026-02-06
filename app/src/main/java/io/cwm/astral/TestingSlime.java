package io.cwm.astral;

import io.cwm.engine.logic.Drawable;
import io.cwm.engine.logic.Updatable;
import io.cwm.engine.logic.math.Vec2;
import io.cwm.engine.rendering.Sprite;
import io.cwm.engine.rendering.SpriteSheet;
import io.cwm.engine.resources.ResourceLocator;

public class TestingSlime implements Drawable, Updatable {
    public final Sprite SPAWN = new Sprite(
            new SpriteSheet(
                    new ResourceLocator("assets/textures/slime_spawn.png"),
                    new Vec2(100, 100)
            ),
            10
    );
    public final Sprite IDLE = new Sprite(
            new SpriteSheet(
                    new ResourceLocator("assets/textures/slime_idle.png"),
                    new Vec2(100, 65)
            ),
            10
    );

    public Sprite currentSprite = SPAWN;

    @Override
    public void draw() {
        currentSprite.draw();
    }

    @Override
    public void update() {
        currentSprite.update();
        if (currentSprite.getCurrentFrame() == currentSprite.ANIMATION.getLength()) {
            if (currentSprite == SPAWN) {
                currentSprite = IDLE;
            } else {
                currentSprite = SPAWN;
            }
        }
    }

    public TestingSlime(Vec2 pos) {
        //TODO: make it so that position is held by something other than ImagePanel
        SPAWN.pos = pos;
        IDLE.pos = pos;
    }
}
