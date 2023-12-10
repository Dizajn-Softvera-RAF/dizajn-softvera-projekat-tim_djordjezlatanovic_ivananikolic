package raf.dsw.classycraft.app.stateSablon;

import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Atributs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.ClassContent;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.observer.message.MessageType;
import raf.dsw.classycraft.app.painters.AtributPainter;
import raf.dsw.classycraft.app.painters.ElementPainteri;
import raf.dsw.classycraft.app.painters.KlasaPainter;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Iterator;

public class Brisanje implements State{
    String a;
    public Brisanje(String s) {
        this.a = s;
    }

    public void misKliknut(int x, int y, DiagramView diagramView){
        if(diagramView.getSelectionModel().isEmpty()) {
            if (a.toLowerCase().equals("diagram element")) {
                boolean flag = false;
                ElementPainteri elements = null;
                for (ElementPainteri element : diagramView.getPainteri()) {

                    Point point = new Point(x, y);
                    if (element.elementAt(point, diagramView, "", element)) {
                        if (element.getDiagramElements() instanceof Klasa) {
                            for (int i = 0; i < ((Klasa) element.getDiagramElements()).getAtributsList().size(); i++) {
                                ClassContent k = ((Klasa) element.getDiagramElements()).getAtributsList().get(i);
                                Iterator e = diagramView.getPainteri().iterator();
                                while (e.hasNext()) {
                                    ElementPainteri next = (ElementPainteri) e.next();

                                    String novarec[] = null;
                                    if (next instanceof AtributPainter) {
                                        //novarec = next.getDiagramElements().getName().split(" ");
                                        //System.out.println(novarec[2]);
                                        if (((AtributPainter) next).getAtribut().equals(k)) {
                                            e.remove();
                                        }
                                    }
                                }
                            }
                            ((Klasa) element.getDiagramElements()).getAtributsList().clear();
                            diagramView.getPainteri().remove(element);
                            diagramView.getDiagram().deleteChild(element.getDiagramElements());
                            flag = true;
                        }
                    }
                    if (flag)
                        break;
                }
            } else {
                boolean flag = false;
                ElementPainteri element = null;
                for (ElementPainteri e : diagramView.getPainteri()) {
                    Point point = new Point(x, y);
                    if (e.elementAt(point, diagramView, "selekcija", element)) {
                        if (e.getDiagramElements() instanceof Klasa) {
                            JDialog.setDefaultLookAndFeelDecorated(true);
                            Object[] selectionValues = new Object[100];
                            for (int i = 0; i < ((Klasa) e.getDiagramElements()).getAtributsList().size(); i++) {
                                String rec = ((Klasa) e.getDiagramElements()).getAtributsList().get(i).toString();
                                selectionValues[i] = rec;
                            }
                            Object basicSelection = selectionValues[0];

                            Object selection = JOptionPane.showInputDialog(null, "Koji cvor zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
                            System.out.println(basicSelection);
                            while (selection == null) {
                                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                                selection = JOptionPane.showInputDialog(null, "Koji cvor zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
                            }
                            int cnt = 0;
                            Iterator iterator = diagramView.getPainteri().iterator();
                            while (iterator.hasNext()) {
                                ElementPainteri elementPainteri = (ElementPainteri) iterator.next();
                                if (elementPainteri instanceof AtributPainter) {
                                    cnt++;
                                    //System.out.println(((AtributPainter) elementPainteri).getAtribut().getName() +"   " + selection);
                                    //rec = elementPainteri.getDiagramElements().getName().split(" ");
                                    //DiagramElements s = (DiagramElements) selection;
                                    //System.out.println();
                                    if (((AtributPainter) elementPainteri).getAtribut().toString().equals(selection)) {
                                        ((Interclass) e.getDiagramElements()).getDuzinaAtributa().remove(cnt - 1);
                                        iterator.remove();
                                        break;
                                    }
                                }
                            }
                            int max = -1;
                            for (int i = 0; i < ((Interclass) e.getDiagramElements()).getDuzinaAtributa().size(); i++) {
                                if (max < ((Interclass) e.getDiagramElements()).getDuzinaAtributa().get(i))
                                    max = ((Interclass) e.getDiagramElements()).getDuzinaAtributa().get(i);
                            }
                            ((Interclass) e.getDiagramElements()).setNajveciWidth(max);
                            Iterator iterator2 = ((Klasa) e.getDiagramElements()).getAtributsList().iterator();
                            while (iterator2.hasNext()) {
                                cnt++;
                                ClassContent ele = (ClassContent) iterator2.next();
                                String rec2 = null;
                                if (ele instanceof Atributs) {
                                    Atributs a = (Atributs) ele;
                                    rec2 = ele.getName();
                                    String s = selection.toString();
                                    if (rec2.equals(s)) {
                                        System.out.println("jdksada");
                                        ((Klasa) e.getDiagramElements()).getAtributsList().remove(ele);
                                        diagramView.getDiagram().notifySubscriber("", "crtanje");
                                        iterator2.remove();
                                        break;
                                    }
                                }
                            }
                         /*if(cnt == ((Klasa)element.getDiagramElements()).getAtributsList().size() + 1){
                             element.umanjiSumu();
                         }*/


                            flag = true;
                        }

                    }
                    if (flag)
                        break;
                }
            }
        }else{
            boolean flag = false;
            ElementPainteri elements = null;
            for(ElementPainteri element : diagramView.getSelectionModel()){
                if(element.getDiagramElements() instanceof Klasa){
                    for(int i = 0; i < ((Klasa) element.getDiagramElements()).getAtributsList().size(); i++){
                        ClassContent k = ((Klasa)element.getDiagramElements()).getAtributsList().get(i);
                        Iterator e = diagramView.getPainteri().iterator();
                        while(e.hasNext()){
                            ElementPainteri next = (ElementPainteri) e.next();
                            if(next instanceof AtributPainter){

                                if(((AtributPainter) next).getAtribut().equals(k)){
                                    e.remove();
                                }
                            }
                        }
                    }
                    ((Klasa) element.getDiagramElements()).getAtributsList().clear();
                    diagramView.getPainteri().remove(element);
                    diagramView.getDiagram().deleteChild(element.getDiagramElements());

                }


            }
            diagramView.getSelectionModel().clear();
            diagramView.getDiagram().notifySubscriber("", "crtanje");
        }



    }
    public void misPovucen(int x, int y, DiagramView diagramView){

    }
    public void misOtpusten(int x, int y, DiagramView diagramView){

    }
}
