package io.cwm.engine.rendering;

import javax.swing.*;

public abstract class GamePanel extends JPanel {
    public static final int TARGET_FPS = 60;

    public abstract void queueDrawChanges();
    public abstract void updatePanelState();


    public final void doLoop() {
        queueDrawChanges();
        this.repaint();
        updatePanelState();
    }

}
