package io.cwm.astral;

import io.cwm.engine.helpers.ResourceLocator;
import io.cwm.engine.rendering.GamePanel;
import io.cwm.engine.rendering.StaticImage;

public class AstralGamePanel extends GamePanel {
    public static final StaticImage phys_test = new StaticImage(new ResourceLocator("assets/textures/img.png"));


    @Override
    public void queueDrawChanges() {
        this.update(phys_test.getGraphics());
    }

    @Override
    public void updatePanelState() {

    }
}
