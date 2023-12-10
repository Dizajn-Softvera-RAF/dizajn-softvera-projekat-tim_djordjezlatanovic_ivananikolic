package raf.dsw.classycraft.app.painters;

import lombok.Getter;
import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Atributs;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;

@Getter
public class AtributPainter extends ElementPainteri {
    private Atributs atribut;
    //private ElementPainteri elementPainteri;


    public AtributPainter(String name, DiagramElements diagramElements, Atributs atributs) {
        super(name, diagramElements);
        this.atribut = atributs;
        //this.elementPainteri = elementPainteri;


    }

    @Override
    public void draw(Graphics g, DiagramView diagramView) {
        Font font2 = new Font(this.atribut.toString(), Font.PLAIN, 13);
        FontMetrics fm = g.getFontMetrics(font2);
        int width = fm.stringWidth(this.atribut.toString());
        if(!((Interclass)getDiagramElements()).getDuzinaAtributa().contains(width)){
            ((Interclass)getDiagramElements()).getDuzinaAtributa().add(width);
        }
        if(((Interclass)getDiagramElements()).getNajveciWidth() == 0){
            ((Interclass)getDiagramElements()).setNajveciWidth(width);
            ((Interclass)getDiagramElements()).setWidth(width + 10);
        }
        if(((Interclass)getDiagramElements()).getNajveciWidth() <= width){
            ((Interclass)getDiagramElements()).setNajveciWidth(width);
            ((Interclass)getDiagramElements()).setWidth(width + 10);
        }
        if(this.atribut.isAbstract()) {
            Font font = new Font(this.atribut.toString(), Font.ITALIC, 13);
            g.setFont(font);
        }
        if(this.atribut.isStatic()){

        }
        g.drawString(this.atribut.toString(), ((Interclass)getDiagramElements()).getX(), ((Interclass)getDiagramElements()).getY() + atribut.getBroj());
        //diagramView.getDiagram().notifySubscriber("", "crtanje");//pitaj
        diagramView.repaint();
    }


    @Override
    public boolean elementAt(Point pos, DiagramView diagramView, String s, ElementPainteri elementPainteri) {
        return false;
    }
}
