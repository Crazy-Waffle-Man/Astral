package io.cwm.engine.logic;

import javax.swing.*;
import java.util.ArrayList;

public class GameManager {
    private ArrayList<Drawable> drawables;
    private ArrayList<Updatable> updatables;

    public void addObject(Object resource) {
        boolean handled = false;

        if (resource instanceof Drawable) {
            drawables.add((Drawable) resource);
            handled = true;
        }
        if (resource instanceof Updatable) {
            updatables.add((Updatable) resource);
            handled = true;
        }

        if (!handled) {
            throw new IllegalArgumentException(
                    "GameManager.addObject("+resource.getClass()+"); " +
                    "is not allowed. It must implement io.cwm.engine.logic.Updatable, " +
                    "io.cwm.engine.logic.Drawable, or both.");
        }

    }

    /**
     * Starts the game loop on Javax's EDT (Event Dispatch Thread).
     * loop order is {@code draw();}, {@code update();}.
     * @param targetFPS is the goal for frames per second.
     */
    public void startLoop(int targetFPS) {
        SwingUtilities.invokeLater(
                () -> new Timer(1000 / targetFPS,
                        (a) -> {
                            draw();
                            update();
                        }
                ).start()
        );
    }
    private void draw() {
        for (Drawable drawable : drawables) {
            drawable.draw();
        }
    }
    private void update() {
        for (Updatable updatable : updatables) {
            updatable.update();
        }
    }
}
