package raf.dsw.classycraft.app.stateSablon;

import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Enumm;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interfejs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Atributs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.EnumElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Methods;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.painters.AtributPainter;
import raf.dsw.classycraft.app.painters.ElementPainteri;
import raf.dsw.classycraft.app.painters.EnumPainter;

import java.awt.*;

public class DodavanjeSadzrajaKlase implements State{
    private String a;
    public DodavanjeSadzrajaKlase(String s) {
        this.a = s;
    }

    public void misKliknut(int x, int y, DiagramView diagramView){
        Point point = new Point(x, y);
        boolean flag =false;
        ElementPainteri element = null;
        for(ElementPainteri elementPainteri : diagramView.getPainteri()){
            if(elementPainteri.elementAt(point, diagramView, "selekcija", elementPainteri)){
                flag = true;
                element = elementPainteri;
            }
            if(flag)
                break;
        }
        String novaRec[] = a.split(" ");
        if(flag){
            if(element.getDiagramElements() instanceof Klasa){

                AtributPainter atributPainter = null;


                boolean flag2 = false;
                boolean flag3 = false;
                if(novaRec[0].toLowerCase().equals("atribut")){
                    if(novaRec[4].toLowerCase().equals("static"))
                        flag2 = true;
                    if(novaRec[5].toLowerCase().equals("apstrakt"))
                        flag3 = true;

                    Atributs atributs = new Atributs(Color.black,novaRec[2], novaRec[3], flag2, flag3);

                    if(((Klasa) element.getDiagramElements()).dodaj(atributs)){
                        //System.out.println("dodaj");

                        ((Klasa) element.getDiagramElements()).povecajSumu();
                        ((Klasa) element.getDiagramElements()).dodaj(atributs);
                        atributs.addSubscriber(diagramView);
                        atributs.setVidljivost(novaRec[3]);;
                        //System.out.println(atributs.toString());
                        atributPainter = new AtributPainter(atributs.toString(), element.getDiagramElements(),atributs);
                    }



                }
                else if (novaRec[0].toLowerCase().equals("metoda")){
                    if(novaRec[4].toLowerCase().equals("static"))
                        flag2 = true;
                    if(novaRec[5].toLowerCase().equals("apstrakt"))
                        flag3 = true;

                    Methods methods = new Methods(Color.black,novaRec[2], novaRec[3], flag2, flag3);
                }
                diagramView.getPainteri().add(atributPainter);
            } else if (element.getDiagramElements() instanceof Interfejs) {
                ////////////////////////////////////////////////
            } else if (element.getDiagramElements() instanceof Enumm) {
                EnumElements enumm = new EnumElements(Color.black,novaRec[2]);
                //EnumPainter enumPainter = new EnumPainter(element.getDiagramElements());

            }
        }
    }
    public void misPovucen(int x, int y, DiagramView diagramView){

    }
    public void misOtpusten(int x, int y, DiagramView diagramView){

    }
}
