package raf.dsw.classycraft.app.stateSablon;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.factory.ClassyNodeFactory;
import raf.dsw.classycraft.app.composite.factory.FactoryUtil;
import raf.dsw.classycraft.app.composite.implementation.Diagram;
import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Enumm;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interfejs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.painters.ElementPainteri;
import raf.dsw.classycraft.app.painters.EnumPainter;
import raf.dsw.classycraft.app.painters.InterfejsPainter;
import raf.dsw.classycraft.app.painters.KlasaPainter;

import java.awt.*;

public class DodavanjeInterclassObjekata implements State{
    private String s;
    private static int cnt = 1;
    private static int cnt2 = 1;
    private static int cnt3 = 1;

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
            if(s.toLowerCase().equals("klasa")){
                k = new Klasa(d, "Klasa" + String.valueOf(cnt),x,y,250,130,Color.black);
                cnt++;
                KlasaPainter painter = new KlasaPainter(s,k);
                diagramView.getPainteri().add(painter);
                d.addChild(k);
            }
            else if(s.toLowerCase().equals("interface")){
                i = new Interfejs(d, "Interfejs" + String.valueOf(cnt2),x,y,250,130,Color.black);
                cnt2++;
                InterfejsPainter painter = new InterfejsPainter(s,i);
                diagramView.getPainteri().add(painter);
                d.addChild(i);

            }
            else {
                e = new Enumm(d, "Enum" + String.valueOf(cnt3),x,y,250,130,Color.black);
                cnt3++;
                EnumPainter painter = new EnumPainter(s,e);
                diagramView.getPainteri().add(painter);
                d.addChild(e);
            }


        }
    }
    public void misPovucen(int x, int y, DiagramView diagramView){

    }
    public void misOtpusten(int x, int y, DiagramView diagramView){

    }
}
