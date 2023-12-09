package raf.dsw.classycraft.app.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Connection;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;

@Setter
@Getter
public class AgregacijaPainter extends ElementPainteri {
    private Point endPoint;
    private ElementPainteri prvi;
    private ElementPainteri poslednji = null;

    public AgregacijaPainter(Paint paint, String name, DiagramElements diagramElements, int x, int y) {
        super(paint, name, diagramElements, x, y, 75, 90);


    }
    @Override
    public void draw(Graphics g, ElementPainteri elementPainteri, DiagramView diagramView) {
        if(getPoslednji() == null){
            g.setColor(Color.BLACK);
            g.drawLine(this.getX(), this.getY(), endPoint.x, endPoint.y);
        }
        else{
            Point point1 = null;
            Point point2 = null;
            int najkraca = Integer.MAX_VALUE;
            for(Point p : prvi.getTackeIcrtavanja()){
                for(Point p2 : poslednji.getTackeIcrtavanja()){
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
