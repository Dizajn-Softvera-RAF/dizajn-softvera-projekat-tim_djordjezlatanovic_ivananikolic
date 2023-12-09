package raf.dsw.classycraft.app.stateSablon;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.painters.ElementPainteri;
import raf.dsw.classycraft.app.painters.LasoPainter;

import java.awt.*;

public class Selekcija implements State{
    private LasoPainter lasoPainter = null;
    private Point point;
    public void misKliknut(int x, int y, DiagramView diagramView){
        boolean flag = false;
        ElementPainteri element = null;
        String s = "selekcija";
        for(ElementPainteri elementPainteri : diagramView.getPainteri()){
            Point point = new Point(x, y);
            if(elementPainteri.elementAt(point, diagramView, s, elementPainteri)){
                flag = true;
                element = elementPainteri;
            }
            if(flag)
                break;
        }
        if(flag){
            System.out.println("usao u true");
            diagramView.getSelectionModel().add(element);
            if(element.getSuma() == 20){
                element.napraviRectangle(element.getX() - 10, element.getY() - 10, element.getWidth() + 30, element.getHeight() + 20);
            }else {
                element.napraviRectangle(element.getX() - 10, element.getY() - 10, element.getWidth() + 30, element.getSuma() + 20);
            }
            diagramView.getDiagram().selektovano();

        }
        else if(flag == false){
            for(ElementPainteri e : diagramView.getSelectionModel()){
                e.setRectangle(null);
                diagramView.getDiagram().selektovano();
            }
            diagramView.getSelectionModel().clear();

        }
        point = new Point(x, y);

    }
    public void misPovucen(int x, int y, DiagramView diagramView){
        if(lasoPainter == null){
            lasoPainter = new LasoPainter(Color.BLUE, "laso", null, x, y, 0, 0);
            lasoPainter.setFirstPoint(point);
            diagramView.getPainteri().add(lasoPainter);
        }
        lasoPainter.setEndPoint(new Point(x, y));
        boolean flag = false;
        ElementPainteri element = null;
        String s = "laso";
        for(ElementPainteri elementPainteri : diagramView.getPainteri()){
            Point point = new Point(x, y);// ovo nam je sad nebitno
            if(lasoPainter.elementAt(point, diagramView, s, elementPainteri)){
                lasoPainter.getPainteriList().add(elementPainteri);
                diagramView.getSelectionModel().add(elementPainteri);
                element = elementPainteri;
                if(elementPainteri.getHeight() != 130)
                    element.napraviRectangle(element.getX() - 10, elementPainteri.getY() - 10, element.getWidth() + 30, elementPainteri.getSuma() + 30);
                else
                    element.napraviRectangle(element.getX() - 10, elementPainteri.getY() - 10, element.getWidth() + 30, elementPainteri.getHeight() + 30);
                diagramView.getDiagram().notifySubscriber("", "crtanje");
            }

        }



    }
    public void misOtpusten(int x, int y, DiagramView diagramView){
        System.out.println("usao2");
        diagramView.getPainteri().remove(lasoPainter);
        lasoPainter = null;
        diagramView.getDiagram().notifySubscriber("", "crtanje");
    }
}
