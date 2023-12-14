package raf.dsw.classycraft.app.stateSablon;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.*;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Atributs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.ClassContent;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.EnumElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Methods;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.message.MessageType;
import raf.dsw.classycraft.app.painters.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class Brisanje implements State{
    String a;
    public Brisanje(String s) {
        this.a = s;
    }

    public void misKliknut(int x, int y, DiagramView diagramView){
        if(diagramView.getSelectionModel().isEmpty()) {
            JDialog.setDefaultLookAndFeelDecorated(true);
            Object[] selectionValue = {"Element", "Diagram element"};
            String basicSelectio = "Element";
            Object selectio = JOptionPane.showInputDialog(null, "Koji cvor zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValue, basicSelectio);
            while (selectio == null) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
                selectio = JOptionPane.showInputDialog(null, "Koji cvor zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValue, basicSelectio);
            }
            String a = selectio.toString();

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
                                    } else if (next instanceof MetodaPainter) {
                                        if (((MetodaPainter) next).getMethods().equals(k)) {
                                            e.remove();
                                        }
                                    } else if (next instanceof EnumElementsPainter) {
                                        if (((EnumElementsPainter) next).getEnumElements().equals(k)) {
                                            e.remove();
                                        }
                                    }
                                }
                            }
                            for (int i = 0; i < ((Klasa) element.getDiagramElements()).getKonekcije().size(); i++) {
                                System.out.println("usao");
                                Connection k = ((Klasa) element.getDiagramElements()).getKonekcije().get(i);
                                Iterator e = diagramView.getPainteri().iterator();
                                while (e.hasNext()) {
                                    ElementPainteri next = (ElementPainteri) e.next();
                                    System.out.println(next.toString() + " next");
                                    if (next instanceof AgregacijaPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((AgregacijaPainter) next).getDiagramElements().equals(k)) {
                                            e.remove();
                                        }
                                    }
                                    else if (next instanceof ZavisnostPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((ZavisnostPainter) next).getDiagramElements().equals(k)) {
                                            e.remove();
                                        }
                                    }
                                    else if (next instanceof GeneralizacijaPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((GeneralizacijaPainter) next).getDiagramElements().equals(k)) {
                                            e.remove();
                                        }
                                    }
                                    else if (next instanceof KompozicijaPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((KompozicijaPainter) next).getDiagramElements().equals(k)) {
                                            e.remove();
                                        }
                                    }
                                }
                            }
                            ((Klasa) element.getDiagramElements()).getKonekcije().clear();
                            ((Klasa) element.getDiagramElements()).getAtributsList().clear();
                            diagramView.getPainteri().remove(element);
                            diagramView.getDiagram().deleteChild(element.getDiagramElements());
                            flag = true;
                        } else if (element.getDiagramElements() instanceof Interfejs) {
                            for (int i = 0; i < ((Interfejs) element.getDiagramElements()).getAtributsList().size(); i++) {
                                ClassContent k = ((Interfejs) element.getDiagramElements()).getAtributsList().get(i);
                                Iterator e = diagramView.getPainteri().iterator();
                                while (e.hasNext()) {
                                    ElementPainteri next = (ElementPainteri) e.next();

                                    String novarec[] = null;
                                    if (next instanceof MetodaPainter) {
                                        if (((MetodaPainter) next).getMethods().equals(k)) {
                                            e.remove();
                                        }
                                    }
                                }
                            }
                            for (int i = 0; i < ((Interfejs) element.getDiagramElements()).getKonekcije().size(); i++) {
                                Connection k = ((Interfejs) element.getDiagramElements()).getKonekcije().get(i);
                                Iterator e = diagramView.getPainteri().iterator();
                                while (e.hasNext()) {
                                    ElementPainteri next = (ElementPainteri) e.next();
                                    System.out.println(next.toString() + " next");
                                    if (next instanceof AgregacijaPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((AgregacijaPainter) next).getDiagramElements().equals(k)) {
                                            e.remove();
                                        }
                                    }
                                    else if (next instanceof ZavisnostPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((ZavisnostPainter) next).getDiagramElements().equals(k)) {
                                            e.remove();
                                        }
                                    }
                                    else if (next instanceof GeneralizacijaPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((GeneralizacijaPainter) next).getDiagramElements().equals(k)) {
                                            e.remove();
                                        }
                                    }
                                    else if (next instanceof KompozicijaPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((KompozicijaPainter) next).getDiagramElements().equals(k)) {
                                            e.remove();
                                        }
                                    }
                                }
                            }
                            ((Interfejs) element.getDiagramElements()).getKonekcije().clear();
                            ((Interfejs) element.getDiagramElements()).getAtributsList().clear();
                            diagramView.getPainteri().remove(element);
                            diagramView.getDiagram().deleteChild(element.getDiagramElements());
                            flag = true;
                        } else if (element.getDiagramElements() instanceof Enumm) {
                            for (int i = 0; i < ((Enumm) element.getDiagramElements()).getAtributsList().size(); i++) {
                                ClassContent k = ((Enumm) element.getDiagramElements()).getAtributsList().get(i);
                                Iterator e = diagramView.getPainteri().iterator();
                                while (e.hasNext()) {
                                    ElementPainteri next = (ElementPainteri) e.next();

                                    String novarec[] = null;
                                    if (next instanceof EnumElementsPainter) {
                                        if (((EnumElementsPainter) next).getEnumElements().equals(k)) {
                                            e.remove();
                                        }
                                    }
                                }
                            }
                            for (int i = 0; i < ((Enumm) element.getDiagramElements()).getKonekcije().size(); i++) {
                                Connection k = ((Enumm) element.getDiagramElements()).getKonekcije().get(i);
                                Iterator e = diagramView.getPainteri().iterator();
                                while (e.hasNext()) {
                                    ElementPainteri next = (ElementPainteri) e.next();
                                    System.out.println(next.toString() + " next");
                                    if (next instanceof AgregacijaPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((AgregacijaPainter) next).getDiagramElements().equals(k)) {
                                            e.remove();
                                        }
                                    }
                                    else if (next instanceof ZavisnostPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((ZavisnostPainter) next).getDiagramElements().equals(k)) {
                                            e.remove();
                                        }
                                    }
                                    else if (next instanceof GeneralizacijaPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((GeneralizacijaPainter) next).getDiagramElements().equals(k)) {
                                            e.remove();
                                        }
                                    }
                                    else if (next instanceof KompozicijaPainter) {
                                        next.getClassyTreeItem().removeFromParent();
                                        if (((KompozicijaPainter) next).getDiagramElements().equals(k)) {
                                            e.remove();
                                        }
                                    }
                                }
                            }
                            ((Enumm) element.getDiagramElements()).getKonekcije().clear();
                            ((Enumm) element.getDiagramElements()).getAtributsList().clear();
                            diagramView.getPainteri().remove(element);
                            diagramView.getDiagram().deleteChild(element.getDiagramElements());
                            flag = true;


                        } else if (element.getDiagramElements() instanceof Connection) {
                            diagramView.getPainteri().remove(element);
                            diagramView.getDiagram().notifySubscriber("", "crtanje");
                        }

                    }
                    if (flag) {
                        element.getClassyTreeItem().removeFromParent();
                        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                        break;
                    }

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
                                    if (((AtributPainter) elementPainteri).getAtribut().toString().equals(selection)) {
                                        if (((Interclass) e.getDiagramElements()).getDuzinaAtributa().size() == 1)
                                            ((Interclass) e.getDiagramElements()).getDuzinaAtributa().clear();
                                        else
                                            ((Interclass) e.getDiagramElements()).getDuzinaAtributa().remove(cnt - 1);
                                        iterator.remove();
                                        break;
                                    }
                                } else if (elementPainteri instanceof MetodaPainter) {
                                    cnt++;
                                    if (((MetodaPainter) elementPainteri).getMethods().toString().equals(selection)) {
                                        if (((Interclass) e.getDiagramElements()).getDuzinaAtributa().size() == 1)
                                            ((Interclass) e.getDiagramElements()).getDuzinaAtributa().clear();
                                        else
                                            ((Interclass) e.getDiagramElements()).getDuzinaAtributa().remove(cnt - 1);
                                        iterator.remove();
                                        break;
                                    }
                                } else if (elementPainteri instanceof EnumElementsPainter) {
                                    cnt++;
                                    if (((EnumElementsPainter) elementPainteri).getEnumElements().toString().equals(selection)) {
                                        if (((Interclass) e.getDiagramElements()).getDuzinaAtributa().size() == 1)
                                            ((Interclass) e.getDiagramElements()).getDuzinaAtributa().clear();
                                        else
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
                                    Atributs at = (Atributs) ele;
                                    rec2 = ele.toString();
                                    String s = selection.toString();
                                    if (rec2.equals(s)) {
                                        ((Klasa) e.getDiagramElements()).umanjiSumu();
                                        ((Klasa) e.getDiagramElements()).setBroj(5);
                                        ((Klasa) e.getDiagramElements()).getAtributsList().remove(ele);
                                        try {
                                            iterator2.remove();
                                        } catch (ConcurrentModificationException e1) {
                                            System.out.println("");

                                        }

                                        diagramView.getDiagram().notifySubscriber("", "crtanje");

                                        break;
                                    }
                                } else if (ele instanceof Methods) {
                                    Methods at = (Methods) ele;
                                    rec2 = ele.toString();
                                    String s = selection.toString();
                                    if (rec2.equals(s)) {
                                        System.out.println("jdksada");
                                        ((Klasa) e.getDiagramElements()).umanjiSumu();
                                        ((Klasa) e.getDiagramElements()).setBroj(5);
                                        ((Klasa) e.getDiagramElements()).getAtributsList().remove(ele);
                                        try {
                                            iterator2.remove();
                                        } catch (ConcurrentModificationException e1) {
                                            System.out.println("");

                                        }

                                        diagramView.getDiagram().notifySubscriber("", "crtanje");

                                        break;
                                    }
                                } else if (ele instanceof EnumElements) {
                                    EnumElements at = (EnumElements) ele;
                                    rec2 = ele.toString();
                                    String s = selection.toString();
                                    if (rec2.equals(s)) {
                                        ((Klasa) e.getDiagramElements()).umanjiSumu();
                                        ((Klasa) e.getDiagramElements()).setBroj(5);
                                        ((Klasa) e.getDiagramElements()).getAtributsList().remove(ele);
                                        try {
                                            iterator2.remove();
                                        } catch (ConcurrentModificationException e1) {
                                            System.out.println("");

                                        }

                                        diagramView.getDiagram().notifySubscriber("", "crtanje");

                                        break;
                                    }
                                }
                            }
                         /*if(cnt == ((Klasa)element.getDiagramElements()).getAtributsList().size() + 1){
                             element.umanjiSumu();
                         }*/


                            flag = true;
                        } else if (e.getDiagramElements() instanceof Interfejs) {
                            JDialog.setDefaultLookAndFeelDecorated(true);
                            Object[] selectionValues = new Object[100];
                            for (int i = 0; i < ((Interfejs) e.getDiagramElements()).getAtributsList().size(); i++) {
                                String rec = ((Interfejs) e.getDiagramElements()).getAtributsList().get(i).toString();
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
                                if (elementPainteri instanceof MetodaPainter) {
                                    cnt++;
                                    if (((MetodaPainter) elementPainteri).getMethods().toString().equals(selection)) {
                                        if (((Interclass) e.getDiagramElements()).getDuzinaAtributa().size() == 1)
                                            ((Interclass) e.getDiagramElements()).getDuzinaAtributa().clear();
                                        else
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
                            Iterator iterator2 = ((Interfejs) e.getDiagramElements()).getAtributsList().iterator();
                            while (iterator2.hasNext()) {
                                cnt++;
                                ClassContent ele = (ClassContent) iterator2.next();
                                String rec2 = null;
                                if (ele instanceof Methods) {
                                    Methods at = (Methods) ele;
                                    rec2 = ele.toString();
                                    String s = selection.toString();
                                    if (rec2.equals(s)) {
                                        System.out.println("jdksada");
                                        ((Interfejs) e.getDiagramElements()).umanjiSumu();
                                        ((Interfejs) e.getDiagramElements()).setBroj(5);
                                        ((Interfejs) e.getDiagramElements()).getAtributsList().remove(ele);
                                        try {
                                            iterator2.remove();
                                        } catch (ConcurrentModificationException e1) {
                                            System.out.println("");

                                        }

                                        diagramView.getDiagram().notifySubscriber("", "crtanje");

                                        break;
                                    }
                                }


                            }
                            flag = true;
                        } else if (e.getDiagramElements() instanceof Enumm) {
                            JDialog.setDefaultLookAndFeelDecorated(true);
                            Object[] selectionValues = new Object[100];
                            for (int i = 0; i < ((Enumm) e.getDiagramElements()).getAtributsList().size(); i++) {
                                String rec = ((Enumm) e.getDiagramElements()).getAtributsList().get(i).toString();
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
                                if (elementPainteri instanceof EnumElementsPainter) {
                                    cnt++;
                                    if (((EnumElementsPainter) elementPainteri).getEnumElements().toString().equals(selection)) {
                                        if (((Interclass) e.getDiagramElements()).getDuzinaAtributa().size() == 1)
                                            ((Interclass) e.getDiagramElements()).getDuzinaAtributa().clear();
                                        else
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
                            Iterator iterator2 = ((Enumm) e.getDiagramElements()).getAtributsList().iterator();
                            while (iterator2.hasNext()) {
                                cnt++;
                                ClassContent ele = (ClassContent) iterator2.next();
                                String rec2 = null;
                                if (ele instanceof EnumElements) {
                                    EnumElements at = (EnumElements) ele;
                                    rec2 = ele.toString();
                                    String s = selection.toString();
                                    if (rec2.equals(s)) {
                                        ((Enumm) e.getDiagramElements()).umanjiSumu();
                                        ((Enumm) e.getDiagramElements()).setBroj(5);
                                        ((Enumm) e.getDiagramElements()).getAtributsList().remove(ele);
                                        try {
                                            iterator2.remove();
                                        } catch (ConcurrentModificationException e1) {
                                            System.out.println("");

                                        }

                                        diagramView.getDiagram().notifySubscriber("", "crtanje");

                                        break;
                                    }
                                }


                            }
                            flag = true;
                        }
                        if (flag)
                            break;
                    }
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
                            } else if (next instanceof MetodaPainter) {
                                if(((MetodaPainter) next).getMethods().equals(k)){
                                    e.remove();
                                }
                            }
                            else if(next instanceof EnumElementsPainter){
                                if(((EnumElementsPainter) next).getEnumElements().equals(k)){
                                    e.remove();
                                }
                            }
                        }
                    }
                    for (int i = 0; i < ((Klasa) element.getDiagramElements()).getKonekcije().size(); i++) {

                        Connection k = ((Klasa) element.getDiagramElements()).getKonekcije().get(i);
                        System.out.println(k.toString());
                        Iterator e = diagramView.getPainteri().iterator();
                        while (e.hasNext()) {
                            ElementPainteri next = (ElementPainteri) e.next();
                            if (next instanceof AgregacijaPainter) {
                                if (((AgregacijaPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }
                            }
                            else if (next instanceof ZavisnostPainter) {
                                next.getClassyTreeItem().removeFromParent();
                                if (((ZavisnostPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }
                            }
                            else if (next instanceof GeneralizacijaPainter) {
                                next.getClassyTreeItem().removeFromParent();
                                if (((GeneralizacijaPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }
                            }
                            else if (next instanceof KompozicijaPainter) {
                                next.getClassyTreeItem().removeFromParent();
                                if (((KompozicijaPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }
                            }
                        }
                    }
                    ((Klasa) element.getDiagramElements()).getKonekcije().clear();
                    ((Klasa) element.getDiagramElements()).getAtributsList().clear();
                    diagramView.getPainteri().remove(element);
                    try{
                        element.getClassyTreeItem().removeFromParent();
                    }
                    catch (NullPointerException e){
                        System.out.print("");
                    }

                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                    diagramView.getDiagram().deleteChild(element.getDiagramElements());

                }
                else if(element.getDiagramElements() instanceof Interfejs){
                    for(int i = 0; i < ((Interfejs) element.getDiagramElements()).getAtributsList().size(); i++){
                        ClassContent k = ((Interfejs)element.getDiagramElements()).getAtributsList().get(i);
                        Iterator e = diagramView.getPainteri().iterator();
                        while(e.hasNext()){
                            ElementPainteri next = (ElementPainteri) e.next();
                            if (next instanceof MetodaPainter) {
                                if(((MetodaPainter) next).getMethods().equals(k)){
                                    e.remove();
                                }
                            }
                        }
                    }
                    for (int i = 0; i < ((Interfejs) element.getDiagramElements()).getKonekcije().size(); i++) {
                        System.out.println("usao");
                        Connection k = ((Interfejs) element.getDiagramElements()).getKonekcije().get(i);
                        System.out.println(k.toString());
                        Iterator e = diagramView.getPainteri().iterator();
                        while (e.hasNext()) {
                            ElementPainteri next = (ElementPainteri) e.next();
                            if (next instanceof AgregacijaPainter) {
                                if (((AgregacijaPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }
                            }
                            else if (next instanceof ZavisnostPainter) {
                                next.getClassyTreeItem().removeFromParent();
                                if (((ZavisnostPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }
                            }
                            else if (next instanceof GeneralizacijaPainter) {
                                next.getClassyTreeItem().removeFromParent();
                                if (((GeneralizacijaPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }
                            }
                            else if (next instanceof KompozicijaPainter) {
                                next.getClassyTreeItem().removeFromParent();
                                if (((KompozicijaPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }
                            }
                        }
                    }
                    ((Interfejs) element.getDiagramElements()).getKonekcije().clear();
                    ((Interfejs) element.getDiagramElements()).getAtributsList().clear();
                    diagramView.getPainteri().remove(element);
                    try{
                        element.getClassyTreeItem().removeFromParent();
                    }
                    catch (NullPointerException e){
                        System.out.print("");
                    }

                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                    diagramView.getDiagram().deleteChild(element.getDiagramElements());

                }
                else if(element.getDiagramElements() instanceof Enumm){
                    for(int i = 0; i < ((Enumm) element.getDiagramElements()).getAtributsList().size(); i++){
                        ClassContent k = ((Enumm)element.getDiagramElements()).getAtributsList().get(i);
                        Iterator e = diagramView.getPainteri().iterator();
                        while(e.hasNext()){
                            ElementPainteri next = (ElementPainteri) e.next();
                            if (next instanceof EnumElementsPainter) {
                                if(((EnumElementsPainter) next).getEnumElements().equals(k)){
                                    e.remove();
                                }
                            }
                        }
                    }
                    for (int i = 0; i < ((Enumm) element.getDiagramElements()).getKonekcije().size(); i++) {
                        Connection k = ((Enumm) element.getDiagramElements()).getKonekcije().get(i);
                        System.out.println(k.toString());
                        Iterator e = diagramView.getPainteri().iterator();
                        while (e.hasNext()) {
                            ElementPainteri next = (ElementPainteri) e.next();
                            //System.out.println(next.toString() + " next");
                            if (next instanceof AgregacijaPainter) {
                                if (((AgregacijaPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }
                            }
                            else if (next instanceof ZavisnostPainter) {
                                next.getClassyTreeItem().removeFromParent();
                                if (((ZavisnostPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }
                            }
                            else if (next instanceof GeneralizacijaPainter) {
                                next.getClassyTreeItem().removeFromParent();
                                if (((GeneralizacijaPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }
                            }
                            else if (next instanceof KompozicijaPainter) {
                                next.getClassyTreeItem().removeFromParent();
                                if (((KompozicijaPainter) next).getDiagramElements().equals(k)) {
                                    e.remove();
                                }
                            }
                        }
                    }
                    ((Enumm) element.getDiagramElements()).getKonekcije().clear();
                    ((Enumm) element.getDiagramElements()).getAtributsList().clear();
                    diagramView.getPainteri().remove(element);
                    try{
                        element.getClassyTreeItem().removeFromParent();
                    }
                    catch (NullPointerException e){
                        System.out.print("");
                    }

                    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                    diagramView.getDiagram().deleteChild(element.getDiagramElements());

                }

                else if(element.getDiagramElements() instanceof Connection){
                        element.getClassyTreeItem().removeFromParent();
                        diagramView.getPainteri().remove(element);
                        diagramView.getDiagram().notifySubscriber("", "crtanje");

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
