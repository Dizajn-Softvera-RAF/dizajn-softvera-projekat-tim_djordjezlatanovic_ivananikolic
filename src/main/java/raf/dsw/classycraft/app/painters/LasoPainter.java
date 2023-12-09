package raf.dsw.classycraft.app.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LasoPainter extends ElementPainteri {
    private List<ElementPainteri> painteriList = new ArrayList<>();
    private Point endPoint;
    private Point firstPoint;

    public LasoPainter(Paint paint, String name, DiagramElements diagramElements, int x, int y, int width, int height) {
        super(paint, name, diagramElements, x, y, width, height);
    }

    @Override
    public void draw(Graphics g, ElementPainteri elementPainteri, DiagramView diagramView) {
        Graphics2D g2 = (Graphics2D) g;
        BasicStroke dashed2 = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
        g2.setStroke(dashed2);
        g2.drawRect(firstPoint.x, firstPoint.y, endPoint.x - firstPoint.x, endPoint.y - firstPoint.y);
    }

    @Override
    public boolean elementAt(Point pos, DiagramView diagramView, String s, ElementPainteri elementPainteri) {
        Rectangle r = new Rectangle(firstPoint.x, firstPoint.y, endPoint.x - firstPoint.x, endPoint.y - firstPoint.y);
        if(elementPainteri instanceof KlasaPainter){
            if(elementPainteri.getWidth() != 130){
                if (r.contains(elementPainteri.getX(), elementPainteri.getY()) || r.contains(elementPainteri.getX() + elementPainteri.getWidth(), elementPainteri.getY()) || r.contains(elementPainteri.getX(), elementPainteri.getY() + elementPainteri.getSuma()) || r.contains(elementPainteri.getX() + elementPainteri.getWidth(), elementPainteri.getY() + elementPainteri.getSuma())) {
                    return true;
                }
            }
            else{
                if (r.contains(elementPainteri.getX(), elementPainteri.getY()) || r.contains(elementPainteri.getX() + elementPainteri.getWidth(), elementPainteri.getY()) || r.contains(elementPainteri.getX(), elementPainteri.getY() + elementPainteri.getHeight()) || r.contains(elementPainteri.getX() + elementPainteri.getWidth(), elementPainteri.getY() + elementPainteri.getHeight())) {
                    return true;
                }
            }
        }
        return false;
    }
}
