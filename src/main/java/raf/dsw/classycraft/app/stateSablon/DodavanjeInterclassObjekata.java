package raf.dsw.classycraft.app.stateSablon;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.factory.ClassyNodeFactory;
import raf.dsw.classycraft.app.composite.factory.FactoryUtil;
import raf.dsw.classycraft.app.composite.implementation.Diagram;
import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Enumm;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interfejs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.painters.ElementPainteri;
import raf.dsw.classycraft.app.painters.EnumPainter;
import raf.dsw.classycraft.app.painters.InterfejsPainter;
import raf.dsw.classycraft.app.painters.KlasaPainter;

import java.awt.*;

public class DodavanjeInterclassObjekata implements State{
    private String s;

    public DodavanjeInterclassObjekata(String s) {
        this.s = s;
    }

    public void misKliknut(int x, int y, DiagramView diagramView){
        Klasa k = null;
        Interfejs i = null;
        Enumm e = null;
        Diagram d = diagramView.getDiagram();

        boolean flag = false;
        for(ElementPainteri element : diagramView.getPainteri()){

            Point point = new Point(x, y);
            if(element.elementAt(point, diagramView, "", element)){
                flag = true;
            }
            if (flag)
                break;
        }

        if(!flag){
            ClassyNodeFactory factory = FactoryUtil.createFactory(d);
            ClassyNode dete = factory.getChild(s);
            if(dete instanceof Klasa){


                KlasaPainter painter = new KlasaPainter(Color.black, "klasa", (DiagramElements) dete, x ,y);
                diagramView.getPainteri().add(painter);
                d.addChild(dete);



            }
            else if(dete instanceof Enumm){
                EnumPainter painter = new EnumPainter(Color.black, "enum", (DiagramElements) dete, x ,y);
                diagramView.getPainteri().add(painter);
                d.addChild(dete);
            }
            else{
                InterfejsPainter painter = new InterfejsPainter(Color.black, "interface", (DiagramElements) dete, x ,y);
                diagramView.getPainteri().add(painter);
                d.addChild(dete);
            }
        }




    }
    public void misPovucen(int x, int y, DiagramView diagramView){

    }
    public void misOtpusten(int x, int y, DiagramView diagramView){

    }
}
