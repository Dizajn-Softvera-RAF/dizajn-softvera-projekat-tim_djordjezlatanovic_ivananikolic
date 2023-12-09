package raf.dsw.classycraft.app.painters;

import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;

public class MetodaPainter extends ElementPainteri {

    public MetodaPainter(Paint paint, String name, DiagramElements diagramElements, int x, int y) {
        super(paint, name, diagramElements, x, y, 75, 90);


    }

    @Override
    public void draw(Graphics g, ElementPainteri elementPainteri, DiagramView diagramView) {

    }


    @Override
    public boolean elementAt(Point pos, DiagramView diagramView, String s, ElementPainteri elementPainteri) {
        return false;
    }
}
