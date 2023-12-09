package raf.dsw.classycraft.app.stateSablon;

import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Agregacija;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.painters.AgregacijaPainter;
import raf.dsw.classycraft.app.painters.ElementPainteri;

import java.awt.*;

public class DodavanjeVeza implements State{
    private AgregacijaPainter agregacijaPainter;
    private Agregacija agregacija;
    private ElementPainteri e;
    public DodavanjeVeza(String s) {
    }

    public void misKliknut(int x, int y, DiagramView diagramView){
        boolean flag = false;
        DiagramElements diagramElements = null;
        e = null;
        for(ElementPainteri element : diagramView.getPainteri()){

            Point point = new Point(x, y);
            if(element.elementAt(point, diagramView, "selekcija", element)){
                flag = true;
                e = element;
                diagramElements = element.getDiagramElements();
            }
            if (flag)
                break;
        }

        if(flag){
            agregacija = new Agregacija(diagramElements, "agregacija");
            agregacija.setFrom((Interclass) diagramElements);

            agregacijaPainter = new AgregacijaPainter(Color.black, "agregaciija", diagramElements, x, y);

            diagramView.getPainteri().add(agregacijaPainter);

        }

    }
    public void misPovucen(int x, int y, DiagramView diagramView){
        agregacijaPainter.setEndPoint(new Point(x, y));
        diagramView.getDiagram().notifySubscriber("", "crtanje");


    }
    public void misOtpusten(int x, int y, DiagramView diagramView){
        agregacijaPainter.setPrvi(e);
        boolean flag = false;
        DiagramElements diagramElements2 = null;
        ElementPainteri en = null;
        for(ElementPainteri element : diagramView.getPainteri()){

            Point point = new Point(x, y);
            if(element.elementAt(point, diagramView, "selekcija", element)){
                flag = true;
                en = element;
                diagramElements2 = element.getDiagramElements();
            }
            if (flag)
                break;
        }
        if(flag){
            agregacija.setTo((Interclass) diagramElements2);
            agregacijaPainter.setPoslednji(en);
            agregacijaPainter.setEndPoint(new Point(x, y));
            diagramView.getDiagram().notifySubscriber("", "crtanje");
        }
        else {
            diagramView.getPainteri().remove(agregacijaPainter);
            diagramView.getDiagram().notifySubscriber("", "crtanje");
        }
    }
}
