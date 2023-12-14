package raf.dsw.classycraft.app.stateSablon;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.*;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.message.MessageType;
import raf.dsw.classycraft.app.painters.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class DodavanjeVeza implements State{

    private AgregacijaPainter agregacijaPainter;
    private KompozicijaPainter kompozicijaPainter;
    private ZavisnostPainter zavisnostPainter;
    private GeneralizacijaPainter generalizacijaPainter;
    private Connection connection;
    private String s;
    private ElementPainteri e;
    public DodavanjeVeza(String s) {
        this.s = s;
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
            String novaRec[] = s.split(" ");
            if(novaRec[0].toLowerCase().equals("agregacija")){
                connection = new Agregacija(diagramElements, "agregacija",x,y, novaRec[1], novaRec[2]);
                connection.setFrom((Interclass) diagramElements);
                ((Interclass)diagramElements).getKonekcije().add(connection);

                agregacijaPainter = new AgregacijaPainter("agregacija",connection);

                diagramView.getPainteri().add(agregacijaPainter);
            }
            else if(novaRec[0].toLowerCase().equals("kompozicija")){
                connection = new Kompozicija(diagramElements, "kompozicija",x,y, novaRec[1], novaRec[2]);
                connection.setFrom((Interclass) diagramElements);
                ((Interclass)diagramElements).getKonekcije().add(connection);

                kompozicijaPainter = new KompozicijaPainter("kompozicija",connection);

                diagramView.getPainteri().add(kompozicijaPainter);
            } else if (novaRec[0].toLowerCase().equals("zavisnost")) {
                connection = new Zavisnost(diagramElements, "zavisnost",x,y, novaRec[1]);
                connection.setFrom((Interclass) diagramElements);
                ((Interclass)diagramElements).getKonekcije().add(connection);

                zavisnostPainter = new ZavisnostPainter("zavisnost",connection);

                diagramView.getPainteri().add(zavisnostPainter);
            }
            else{
                connection = new Generalizacija(diagramElements, "generalizacija",x,y);
                connection.setFrom((Interclass) diagramElements);
                ((Interclass)diagramElements).getKonekcije().add(connection);

                generalizacijaPainter = new GeneralizacijaPainter("generalizacija",connection);

                diagramView.getPainteri().add(generalizacijaPainter);
            }


        }


    }
    public void misPovucen(int x, int y, DiagramView diagramView){
        Connection c = null;
        if(agregacijaPainter != null)
            c = (Connection)agregacijaPainter.getDiagramElements();
        else if(zavisnostPainter != null)
            c = (Connection)zavisnostPainter.getDiagramElements();
        else if(kompozicijaPainter != null)
            c = (Connection)kompozicijaPainter.getDiagramElements();
        else if(generalizacijaPainter != null)
            c = (Connection)generalizacijaPainter.getDiagramElements();
        c.setEndPoint(new Point(x, y));
        diagramView.getDiagram().notifySubscriber("", "crtanje");


    }
    public void misOtpusten(int x, int y, DiagramView diagramView){
        Connection connection = null;
        if(agregacijaPainter != null)
            connection = (Connection)agregacijaPainter.getDiagramElements();
        else if(zavisnostPainter != null)
            connection = (Connection)zavisnostPainter.getDiagramElements();
        else if(kompozicijaPainter != null)
            connection = (Connection)kompozicijaPainter.getDiagramElements();
        else if(generalizacijaPainter != null)
            connection = (Connection)generalizacijaPainter.getDiagramElements();
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
            connection.setTo((Interclass) diagramElements2);
            connection.setEndPoint(new Point(x, y));
            diagramView.getDiagram().addChild(connection);
            ((Interclass)diagramElements2).getKonekcije().add(connection);
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
                ClassyTreeItem c2 = new ClassyTreeItem(connection);
                nova.add(c2);
                Connection c = null;
                if(agregacijaPainter != null)
                    agregacijaPainter.setClassyTreeItem(c2);
                else if(zavisnostPainter != null)
                    zavisnostPainter.setClassyTreeItem(c2);
                else if(kompozicijaPainter != null)
                    kompozicijaPainter.setClassyTreeItem(c2);
                else if(generalizacijaPainter != null)
                    generalizacijaPainter.setClassyTreeItem(c2);

                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
            }
            diagramView.getDiagram().notifySubscriber("", "crtanje");
        }
        else {
            if(agregacijaPainter != null)
                diagramView.getPainteri().remove(agregacijaPainter);
            else if(zavisnostPainter != null)
                diagramView.getPainteri().remove(zavisnostPainter);
            else if(kompozicijaPainter != null)
                diagramView.getPainteri().remove(kompozicijaPainter);
            else if(generalizacijaPainter != null)
                diagramView.getPainteri().remove(generalizacijaPainter);
            diagramView.getDiagram().notifySubscriber("", "crtanje");
        }
    }
}
