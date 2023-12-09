package raf.dsw.classycraft.app.painters;

import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;

public class EnumPainter extends ElementPainteri {


    public EnumPainter(Paint paint, String name, DiagramElements diagramElements, int x, int y) {
        super(paint, name, diagramElements, x, y, 250, 130);


    }

    @Override
    public void draw(Graphics g, ElementPainteri elementPainteri, DiagramView diagramView) {
        g.setColor((Color) elementPainteri.getPaint());
        g.drawRect(elementPainteri.getX(), elementPainteri.getY(), getWidth(), getHeight());
        g.drawString(elementPainteri.getDiagramElements().getName(), ((elementPainteri.getX() + getWidth())) / 2, (elementPainteri.getY() + 10));
    }


    @Override
    public boolean elementAt(Point pos, DiagramView diagramView, String s, ElementPainteri elementPainteri) {
        Rectangle r = new Rectangle();
        r.setSize(getWidth(), getHeight());
        r.setLocation(getX(), getY());
        if(r.contains(pos.x, pos.y) || r.contains(pos.x + getHeight() , pos.y) || r.contains(pos.x , pos.y + getWidth()) || r.contains(pos.x + getHeight(), pos.y + getHeight())){
            return true;
        }
        return false;
    }
}
