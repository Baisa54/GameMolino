package ar.edu.unlu.molino195157.Vista;

import javax.swing.*;
import java.awt.*;

class PanelLinea extends JPanel {
    private final boolean horizontal;
    private final boolean vertical;

    public PanelLinea(boolean horizontal, boolean vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        int w = getWidth();
        int h = getHeight();
        if (horizontal) {
            g.drawLine(0, h / 2, w, h / 2);
        }
        if (vertical) {
            g.drawLine(w / 2, 0, w / 2, h);
        }
    }
}
