package raf.dsw.classycraft.app.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Connection;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;

@Setter
@Getter
public class AgregacijaPainter extends ElementPainteri {
//    private Point endPoint;
//    private ElementPainteri prvi;
//    private ElementPainteri poslednji = null;

    public AgregacijaPainter(String name,DiagramElements diagramElements) {
        super(name, diagramElements);


    }
    @Override
    public void draw(Graphics g, DiagramView diagramView) {
        Connection c = (Connection) getDiagramElements();

        if(c.getTo() == null){
            g.setColor(Color.BLACK);
            g.drawLine(c.getX(), c.getY(), c.getEndPoint().x, c.getEndPoint().y);
        }
        else{
            Point point1 = null;
            Point point2 = null;
            int najkraca = Integer.MAX_VALUE;
            for(Point p : c.getFrom().getTackeIcrtavanja()){
                for(Point p2 :c.getTo().getTackeIcrtavanja()){
                    int broj = (int) Math.sqrt((p2.x - p.x) * (p2.x - p.x) + (p2.y - p.y) * (p2.y - p.y));
                    if(najkraca > broj){
                        najkraca = broj;
                        point1 = new Point(p);
                        point2 = new Point(p2);
                    }
                }
            }
            g.drawLine(point1.x, point1.y, point2.x, point2.y);
        }
    }


    @Override
    public boolean elementAt(Point pos, DiagramView diagramView, String s, ElementPainteri elementPainteri) {
        return false;
    }
}
