package raf.dsw.classycraft.app.painters;

import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Enumm;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interfejs;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;

public class EnumPainter extends ElementPainteri {


    public EnumPainter(String name,DiagramElements diagramElements) {
        super(name, diagramElements);


    }

    @Override
    public void draw(Graphics g, DiagramView diagramView) {
        Enumm k = ((Enumm)getDiagramElements());
        Graphics2D g2 = (Graphics2D) g;
        if(!(k.getWidth() == 250)){
            g.setColor((Color) k.getPaint());
            g.drawRect(k.getX(), k.getY(), k.getWidth(), k.getSuma());
            g.drawString(k.getName(), (int) ((int)((2 * k.getX() + k.getWidth())) / 2.15), (k.getY() + 10));
            Point p1, p2, p3, p4;
            p1 = new Point((2 * k.getX() + k.getWidth()) / 2, k.getY());
            p2 = new Point(k.getX(), (2 * k.getY() + k.getSuma()) / 2);
            p3 = new Point(k.getX() + k.getWidth(), (2 * k.getY() + k.getSuma()) / 2);
            p4 = new Point((2 * k.getX() + k.getWidth()) / 2, k.getY() + k.getSuma());
            k.getTackeIcrtavanja().get(0).setLocation(p1);
            k.getTackeIcrtavanja().get(1).setLocation(p2);
            k.getTackeIcrtavanja().get(2).setLocation(p3);
            k.getTackeIcrtavanja().get(3).setLocation(p4);
            if(this.getRectangle()!= null)
                this.setRectangle(k.getWidth(), k.getSuma());

        }
        else{
            g.setColor((Color) k.getPaint());
            g.drawRect(k.getX(), k.getY(), k.getWidth(), k.getHeight());
            g.drawString(k.getName(), (int) ((int)((2 * k.getX() + k.getWidth())) / 2.15), (k.getY() + 10));
            Point p1, p2, p3, p4;
            p1 = new Point((2 * k.getX() + k.getWidth()) / 2, k.getY());
            p2 = new Point(k.getX(), (2 * k.getY() +k.getHeight()) / 2);
            p3 = new Point(k.getX() + k.getWidth(), (2 * k.getY() + k.getHeight()) / 2);
            p4 = new Point((2 * k.getX() + k.getWidth()) / 2, k.getY() + k.getHeight());
            k.getTackeIcrtavanja().clear();
            k.getTackeIcrtavanja().add(p1);
            k.getTackeIcrtavanja().add(p2);
            k.getTackeIcrtavanja().add(p3);
            k.getTackeIcrtavanja().add(p4);
        }

        if(this.getRectangle() != null){
            BasicStroke dashed2 = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
            ((Graphics2D) g).setStroke(dashed2);
            g.setColor(Color.blue);
            ((Graphics2D) g).draw(this.getRectangle());
            g.setColor(Color.BLACK);
            ((Graphics2D) g).setStroke(new BasicStroke(1.0f));
        }

//        Enumm e = ((Enumm)getDiagramElements());
//        g.setColor((Color) e.getPaint());
//        g.drawRect(e.getX(), e.getY(), e.getWidth(), e.getHeight());
//        g.drawString(e.getName(), ((e.getX() + e.getWidth())) / 2, (e.getY() + 10));
    }


    @Override
    public boolean elementAt(Point pos, DiagramView diagramView, String s, ElementPainteri elementPainteri) {
        Enumm e = ((Enumm)getDiagramElements());
        Rectangle r = new Rectangle();
        r.setSize(e.getWidth(), e.getHeight());
        r.setLocation(e.getX(),e.getY());
        if(r.contains(pos.x, pos.y) || r.contains(pos.x + e.getHeight() , pos.y) || r.contains(pos.x , pos.y + e.getWidth()) || r.contains(pos.x + e.getHeight(), pos.y + e.getHeight())){
            return true;
        }
        return false;
    }
}
