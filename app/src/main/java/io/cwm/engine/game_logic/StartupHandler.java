package io.cwm.engine.game_logic;

import io.cwm.engine.rendering.GamePanel;

import javax.swing.*;
import java.util.logging.*;

public abstract class StartupHandler {
    public static Logger engineLogger = Logger.getLogger("Engine");
    public static void startGame(final String NAME, final int WIDTH, final int HEIGHT, GamePanel panel) {
        startLogging();
        SwingUtilities.invokeLater(
                setupGame(NAME, WIDTH, HEIGHT, panel)
        );
    }

    private static void startLogging() {
        engineLogger.setUseParentHandlers(false);
        engineLogger.setLevel(Level.ALL);
        ConsoleHandler console = new ConsoleHandler();
        console.setLevel(Level.ALL);
        engineLogger.addHandler(console);
    }

    private static Runnable setupGame(final String NAME, final int WIDTH, final int HEIGHT, GamePanel panel) {
        //setup

        //Window setup, we want this running on the EDT, so we MUST wrap the returned lambda with SwingUtilities.invokeLater(), which we do in startGame().
        return ()->{
            JFrame frame = new JFrame(NAME);

            frame.add(panel);
            frame.setSize(WIDTH, HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            new Timer(
                GamePanel.TARGET_FPS,
                e -> {
                    panel.doLoop();
                }
            ).start();
        };
    }
}

