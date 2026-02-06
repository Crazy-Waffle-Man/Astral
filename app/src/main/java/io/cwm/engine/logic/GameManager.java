package io.cwm.engine.logic;

import io.cwm.engine.rendering.ImagePanel;
import io.cwm.engine.resources.ResourceLocator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameManager {
    private static ArrayList<Drawable> drawables = new ArrayList<>();
    private static ArrayList<Updatable> updatables = new ArrayList<>();
    public static boolean debugMode = false;

    public static void addObject(Object resource) {
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
     * @param targetFPS the goal for frames per second.
     */
    public static void startLoop(int targetFPS) {
        if (debugMode) {
            System.out.println("Launching in debug mode. Hitboxes will be drawn, and custom behavior added by the game classes will activate.");
        }

        SwingUtilities.invokeLater(
                () -> {
                    JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(400, 400);
                    frame.setVisible(true);

                    for (Drawable drawable : drawables) {
                        if (drawable instanceof Component) {
                            frame.add((Component) drawable);
                        }
                    }


                    new Timer(1000 / targetFPS,
                            (a) -> {
                                draw();
                                update();
                            }
                    ).start();
                }
        );
    }
    private static void draw() {
        for (Drawable drawable : drawables) {
            drawable.draw();
        }
    }
    private static void update() {
        for (Updatable updatable : updatables) {
            updatable.update();
        }
    }
}
