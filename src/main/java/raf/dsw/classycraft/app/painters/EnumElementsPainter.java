package raf.dsw.classycraft.app.painters;

import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;

public class EnumElementsPainter extends ElementPainteri {


    public EnumElementsPainter(String name,DiagramElements diagramElements) {
        super(name, diagramElements);


    }

    @Override
    public void draw(Graphics g, DiagramView diagramView) {

    }


    @Override
    public boolean elementAt(Point pos, DiagramView diagramView, String s, ElementPainteri elementPainteri) {
        return false;
    }
}
