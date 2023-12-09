package raf.dsw.classycraft.app.painters;

import lombok.Getter;
import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Atributs;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;

@Getter
public class AtributPainter extends ElementPainteri {
    private Atributs atribut;
    private ElementPainteri elementPainteri;


    public AtributPainter(Paint paint, String name, DiagramElements diagramElements, int x, int y, Atributs atributs, ElementPainteri elementPainteri) {
        super(paint, name, diagramElements, x, y, 75, 90);
        this.atribut = atributs;
        this.elementPainteri = elementPainteri;


    }

    @Override
    public void draw(Graphics g, ElementPainteri elementPainteri, DiagramView diagramView) {
        Font font2 = new Font(this.atribut.toString(), Font.PLAIN, 13);
        FontMetrics fm = g.getFontMetrics(font2);
        int width = fm.stringWidth(this.atribut.toString());
        if(!(this.elementPainteri.getDuzinaAtributa().contains(width))){
            this.elementPainteri.getDuzinaAtributa().add(width);
        }
        if(this.elementPainteri.getNajveciWidth() == 0){
            this.elementPainteri.setNajveciWidth(width);
            this.elementPainteri.setWidth(width + 10);
        }
        if(this.elementPainteri.getNajveciWidth() <= width){
            this.elementPainteri.setNajveciWidth(width);
            this.elementPainteri.setWidth(width + 10);
        }
        if(this.atribut.isAbstract()) {
            Font font = new Font(this.atribut.toString(), Font.ITALIC, 13);
            g.setFont(font);
        }
        if(this.atribut.isStatic()){

        }
        g.drawString(this.atribut.toString(), this.elementPainteri.getX(), this.elementPainteri.getY() + atribut.getBroj());
        //diagramView.getDiagram().notifySubscriber("", "crtanje");//pitaj
        diagramView.repaint();
    }


    @Override
    public boolean elementAt(Point pos, DiagramView diagramView, String s, ElementPainteri elementPainteri) {
        return false;
    }
}
