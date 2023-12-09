package raf.dsw.classycraft.app.painters;

import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;

public class KlasaPainter extends ElementPainteri {


    public KlasaPainter(Paint paint, String name, DiagramElements diagramElements, int x, int y) {
        super(paint, name, diagramElements, x, y, 250, 130);


    }


    @Override
    public void draw(Graphics g, ElementPainteri elementPainter, DiagramView diagramView) {

            g = (Graphics2D) g;
            if(!(getWidth() == 250)){
                g.setColor((Color) elementPainter.getPaint());
                g.drawRect(elementPainter.getX(), elementPainter.getY(), getWidth(), getSuma());
                g.drawString(elementPainter.getDiagramElements().getName(), (int) ((int)((2 * elementPainter.getX() + getWidth())) / 2.15), (elementPainter.getY() + 10));
                Point p1, p2, p3, p4;
                p1 = new Point((2 * this.getX() + this.getWidth()) / 2, getY());
                p2 = new Point(getX(), (2 * getY() + getSuma()) / 2);
                p3 = new Point(getX() + getWidth(), (2 * getY() + getSuma()) / 2);
                p4 = new Point((2 * this.getX() + this.getWidth()) / 2, getY() + getSuma());
                this.getTackeIcrtavanja().get(0).setLocation(p1);
                this.getTackeIcrtavanja().get(1).setLocation(p2);
                this.getTackeIcrtavanja().get(2).setLocation(p3);
                this.getTackeIcrtavanja().get(3).setLocation(p4);
                if(this.getRectangle()!= null)
                this.setRectangle(getWidth(), getSuma());

            }
            else{
                g.setColor((Color) elementPainter.getPaint());
                g.drawRect(elementPainter.getX(), elementPainter.getY(), getWidth(), getHeight());
                g.drawString(elementPainter.getDiagramElements().getName(), (int) ((int)((2 * elementPainter.getX() + getWidth())) / 2.15), (elementPainter.getY() + 10));
                Point p1, p2, p3, p4;
                p1 = new Point((2 * this.getX() + this.getWidth()) / 2, getY());
                p2 = new Point(getX(), (2 * getY() + getHeight()) / 2);
                p3 = new Point(getX() + getWidth(), (2 * getY() + getHeight()) / 2);
                p4 = new Point((2 * this.getX() + this.getWidth()) / 2, getY() + getHeight());
                this.getTackeIcrtavanja().clear();
                this.getTackeIcrtavanja().add(p1);
                this.getTackeIcrtavanja().add(p2);
                this.getTackeIcrtavanja().add(p3);
                this.getTackeIcrtavanja().add(p4);
            }

            if(this.getRectangle() != null){
                BasicStroke dashed2 = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
                ((Graphics2D) g).setStroke(dashed2);
                g.setColor(Color.blue);
                ((Graphics2D) g).draw(this.getRectangle());
                g.setColor(Color.BLACK);
                ((Graphics2D) g).setStroke(new BasicStroke(1.0f));
            }






    }

    @Override
    public boolean elementAt(Point pos, DiagramView diagramView, String s, ElementPainteri elementPainteri) {
        Rectangle r = new Rectangle();
        if(!(getHeight() == 130)){
            r.setSize(getWidth(), getSuma());
        }
        else r.setSize(getWidth(), getHeight());

        r.setLocation(this.getX(), this.getY());
        if(s.equals("selekcija")){
            if(r.contains(pos.x, pos.y))
                return true;
        }else{
            if(r.contains(pos.x, pos.y) || r.contains(pos.x + getWidth() , pos.y) || r.contains(pos.x , pos.y + getHeight()) || r.contains(pos.x + getWidth(), pos.y + getHeight())){
                return true;
            }
        }

        return false;
    }

}
