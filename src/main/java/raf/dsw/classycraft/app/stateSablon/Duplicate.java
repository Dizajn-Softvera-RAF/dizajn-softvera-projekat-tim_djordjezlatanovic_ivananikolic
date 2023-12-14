package raf.dsw.classycraft.app.stateSablon;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Enumm;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interfejs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Atributs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.ClassContent;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.EnumElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Methods;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.painters.*;

import javax.swing.*;
import java.awt.*;

public class Duplicate implements State {
    private DiagramElements diagramElements;
    private ElementPainteri element;

    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        Point point = new Point(x, y);
        boolean flag =false;
       // ElementPainteri element = null;
        for(ElementPainteri elementPainteri : diagramView.getPainteri()){
            if(elementPainteri.elementAt(point, diagramView, "selekcija", elementPainteri)){
                flag = true;
                element = elementPainteri;
            }
            if(flag)
                break;
        }
        if(flag){

            if(element.getDiagramElements() instanceof Klasa){
                Klasa k = (Klasa) element.getDiagramElements();
                if(k.getWidth() != 250)
                    diagramElements = new Klasa(diagramView.getDiagram(), k.getName(), x, y, k.getWidth(), k.getSuma(), Color.black);

                else
                    diagramElements = new Klasa(diagramView.getDiagram(), k.getName(), x, y, k.getWidth(), k.getHeight(), Color.black);
            }
            else if (element.getDiagramElements() instanceof Interfejs) {
                Interfejs k = (Interfejs) element.getDiagramElements();
                if(k.getWidth() != 250)
                    diagramElements = new Interfejs(diagramView.getDiagram(), k.getName(), x, y, k.getWidth(), k.getSuma(), Color.black);

                else
                    diagramElements = new Interfejs(diagramView.getDiagram(), k.getName(), x, y, k.getWidth(), k.getHeight(), Color.black);

            }
            else if(element.getDiagramElements() instanceof Enumm){
                Enumm k = (Enumm) element.getDiagramElements();
                if(k.getWidth() != 250)
                    diagramElements = new Enumm(diagramView.getDiagram(), k.getName(), x, y, k.getWidth(), k.getSuma(), Color.black);

                else
                    diagramElements = new Enumm(diagramView.getDiagram(), k.getName(), x, y, k.getWidth(), k.getHeight(), Color.black);

            }
        }
        else if(!flag){
            System.out.println("usao");
            System.out.println(diagramElements);
            if(diagramElements != null){
                flag = false;
                for(ElementPainteri element2 : diagramView.getPainteri()){
                    Point point2 = new Point(x, y);
                    if(element2.elementAt(point2, diagramView, "", element2)){
                        flag = true;
                    }
                    if (flag)
                        break;
                }
                if(!flag){
                    KlasaPainter painter = null;
                    EnumPainter enumPainter = null;
                    InterfejsPainter interfejsPainter = null;
                    if(diagramElements instanceof Klasa){
                        ((Interclass)diagramElements).setX(x);
                        ((Interclass)diagramElements).setY(y);
                         painter = new KlasaPainter(((Interclass)diagramElements).getName(), diagramElements);
                        diagramView.getPainteri().add(painter);
                        for(ClassContent c : ((Klasa)element.getDiagramElements()).getAtributsList()){
                            if(c instanceof Atributs){
                                Atributs atributs = new Atributs(diagramElements, Color.black, c.getName() + " ", ((Atributs) c).getTip(), ((Atributs) c).isStatic(), ((Atributs) c).isAbstract());
                                AtributPainter atributPainter = new AtributPainter(atributs.toString(), diagramElements, atributs);
                                diagramView.getPainteri().add(atributPainter);
                                atributs.setVidljivost(((Atributs) c).getVidljivost());
                                ((Interclass) diagramElements).povecajSumu();
                                ((Klasa) diagramElements).dodaj(atributs);
                            }
                            else if(c instanceof Methods){
                                Methods atributs = new Methods(diagramElements, Color.black, c.getName(), ((Methods) c).getTip() + " ", ((Methods) c).isStatic(), ((Methods) c).isAbstract());
                                MetodaPainter atributPainter = new MetodaPainter(atributs.toString(), diagramElements, atributs);
                                diagramView.getPainteri().add(atributPainter);
                                atributs.setUlazniElementi(((Methods) c).getUlazniElementi());
                                atributs.setVidljivost(((Methods) c).getVidljivost());
                                ((Interclass) diagramElements).povecajSumu();
                                ((Klasa) diagramElements).dodaj(atributs);
                            }
                            else if(c instanceof EnumElements){
                                EnumElements atributs = new EnumElements(diagramElements, Color.black, c.getName() + " ");
                                EnumElementsPainter atributPainter = new EnumElementsPainter(atributs.toString(), diagramElements, atributs);
                                diagramView.getPainteri().add(atributPainter);
                                ((Interclass) diagramElements).povecajSumu();
                                ((Klasa) diagramElements).dodaj(atributs);
                            }
                        }
                    }
                    else if(diagramElements instanceof Interfejs){
                        ((Interclass)diagramElements).setX(x);
                        ((Interclass)diagramElements).setY(y);
                        interfejsPainter = new InterfejsPainter(((Interclass)diagramElements).getName(), diagramElements);
                        diagramView.getPainteri().add(interfejsPainter);
                        for(ClassContent c : ((Interfejs)element.getDiagramElements()).getAtributsList()){
                            if(c instanceof Methods){
                                Methods atributs = new Methods(diagramElements, Color.black, c.getName(), ((Methods) c).getTip() + " ", ((Methods) c).isStatic(), ((Methods) c).isAbstract());
                                atributs.setUlazniElementi(((Methods) c).getUlazniElementi());
                                MetodaPainter atributPainter = new MetodaPainter(atributs.toString(), diagramElements, atributs);
                                diagramView.getPainteri().add(atributPainter);
                                atributs.setVidljivost(((Methods) c).getVidljivost());
                                ((Interclass) diagramElements).povecajSumu();
                                ((Interfejs) diagramElements).dodaj(atributs);
                            }
                        }

                    }
                    else if(diagramElements instanceof Enumm){
                        ((Interclass)diagramElements).setX(x);
                        ((Interclass)diagramElements).setY(y);
                        enumPainter = new EnumPainter(((Interclass)diagramElements).getName(), diagramElements);
                        diagramView.getPainteri().add(enumPainter);
                        for(ClassContent c : ((Enumm)element.getDiagramElements()).getAtributsList()){
                            if(c instanceof EnumElements){
                                EnumElements atributs = new EnumElements(diagramElements, Color.black, c.getName() + " ");
                                EnumElementsPainter atributPainter = new EnumElementsPainter(atributs.toString(), diagramElements, atributs);
                                diagramView.getPainteri().add(atributPainter);
                                ((Interclass) diagramElements).povecajSumu();
                                ((Enumm) diagramElements).dodaj(atributs);
                            }
                        }

                    }


                    ClassyTreeItem nova = new ClassyTreeItem(diagramView.getDiagram());
                    int childCount = diagramView.getClassyTreeItem().getChildCount();
                    boolean flag1 = false;
                    for (int i1 = 0; i1 < childCount; i1++) {
                        ClassyTreeItem childNode = (ClassyTreeItem) diagramView.getClassyTreeItem().getChildAt(i1);
                        if (childNode.getClassyNode().equals(diagramView.getDiagram())) {
                            nova = childNode;
                            flag1 = true;
                        }
                    }
                    if(flag1==true){
                        ClassyTreeItem c = new ClassyTreeItem(diagramElements);
                        nova.add(c);
                        if(painter != null)
                            painter.setClassyTreeItem(c);
                        else if(interfejsPainter != null)
                            interfejsPainter.setClassyTreeItem(c);
                        else if(enumPainter != null)
                            enumPainter.setClassyTreeItem(c);
                        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                    }

                    diagramView.getDiagram().addChild(diagramElements);
                }


                diagramElements = null;
            }
        }
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {

    }
}
