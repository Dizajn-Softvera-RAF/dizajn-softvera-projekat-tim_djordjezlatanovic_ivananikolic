package raf.dsw.classycraft.app.stateSablon;

import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Atributs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.ClassContent;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.observer.message.MessageType;
import raf.dsw.classycraft.app.painters.AtributPainter;
import raf.dsw.classycraft.app.painters.ElementPainteri;

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
        if(a.toLowerCase().equals("diagram element")){
            boolean flag = false;
            ElementPainteri elements = null;
            for(ElementPainteri element : diagramView.getPainteri()){

                Point point = new Point(x, y);
                if(element.elementAt(point, diagramView, "", element)){
                    if(element.getDiagramElements() instanceof Klasa){
                        for(int i = 0; i < ((Klasa) element.getDiagramElements()).getAtributsList().size(); i++){
                            ClassContent k = ((Klasa)element.getDiagramElements()).getAtributsList().get(i);
                            Iterator e = diagramView.getPainteri().iterator();
                            while(e.hasNext()){
                                ElementPainteri next = (ElementPainteri) e.next();
                                String novarec[] = null;
                                if(next instanceof AtributPainter){
                                    novarec = next.getName().split(" ");
                                    System.out.println(novarec[2]);
                                    if(novarec[2].equals(k.getName())){
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
        }
        else{
                boolean flag= false;
                ElementPainteri element = null;
                for(ElementPainteri e : diagramView.getPainteri()){
                    Point point = new Point(x, y);
                    if(e.elementAt(point, diagramView, "selekcija", element)){
                     if(e.getDiagramElements() instanceof Klasa){
                         JDialog.setDefaultLookAndFeelDecorated(true);
                         Object[] selectionValues = new Object[100];
                         for(int i = 0; i < ((Klasa) e.getDiagramElements()).getAtributsList().size(); i++){
                             String rec = ((Klasa) e.getDiagramElements()).getAtributsList().get(i).getName();
                             selectionValues[i] = rec;
                         }
                         String basicSelection = (String) selectionValues[0];
                         Object selection = JOptionPane.showInputDialog(null, "Koji cvor zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
                         while(selection == null){
                             ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                             selection = JOptionPane.showInputDialog(null, "Koji cvor zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
                         }
                        int cnt = 0;
                         Iterator iterator = diagramView.getPainteri().iterator();
                         while (iterator.hasNext()){
                             ElementPainteri elementPainteri = (ElementPainteri) iterator.next();
                             String rec[] = null;
                             if (elementPainteri instanceof AtributPainter){
                                 cnt++;
                                 rec = elementPainteri.getName().split(" ");
                                 String s = selection.toString();
                                 if(rec[2].equals(s)){
                                        e.getDuzinaAtributa().remove(cnt - 1);
                                        iterator.remove();
                                        break;
                                 }
                             }
                         }
                         int max = - 1;
                         for(int i = 0; i < e.getDuzinaAtributa().size(); i++){
                             if(max < e.getDuzinaAtributa().get(i))
                                 max = e.getDuzinaAtributa().get(i);
                         }
                         e.setNajveciWidth(max);
                         Iterator iterator2 =  ((Klasa) e.getDiagramElements()).getAtributsList().iterator();
                         while (iterator2.hasNext()){
                             cnt++;
                             ClassContent elementPainteri = (ClassContent) iterator2.next();
                             String rec2 = null;
                             if (elementPainteri instanceof Atributs){
                                 rec2 = elementPainteri.getName();
                                 String s = selection.toString();
                                 if(rec2.equals(s)){
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

                    }if(flag)
                        break;
                }
        }



    }
    public void misPovucen(int x, int y, DiagramView diagramView){

    }
    public void misOtpusten(int x, int y, DiagramView diagramView){

    }
}
