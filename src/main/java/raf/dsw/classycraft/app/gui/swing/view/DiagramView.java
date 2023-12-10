package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.implementation.Diagram;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;
import raf.dsw.classycraft.app.listeners.DiagramListener;
import raf.dsw.classycraft.app.observer.Subscriber;
import raf.dsw.classycraft.app.painters.ElementPainteri;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class DiagramView extends JPanel implements Subscriber {
    private Diagram diagram;
    private Klasa klasa;

    private JPanel panel;
    private String imeTaba;
    private PackageView packageView;

    private List<ElementPainteri> painteri = new ArrayList<>();
    private List<ElementPainteri> selectionModel = new ArrayList<>();


    public DiagramView(Diagram prosledjeniDiagram, PackageView prosledjeniPackageView) {
        this.packageView = prosledjeniPackageView;
        this.imeTaba = prosledjeniDiagram.getName();
        this.diagram = prosledjeniDiagram;
        this.diagram.addSubscriber(this);
        //addMouseListener(new MouseDragged(this));
        var xd = new DiagramListener(this);
        addMouseListener(xd);
        addMouseMotionListener(xd);
    }


    @Override
    public void update(Object var1,String tekst) {
        String staroIme = this.imeTaba;
        if(tekst.equals("crtanje")){
            repaint();
        }
        else if(var1 == "null"){
            packageView.promenaImena("null", staroIme);
        }else{
            imeTaba = String.valueOf(var1);
            packageView.promenaImena(imeTaba, staroIme);
        }



    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g = (Graphics2D)g;
        for(ElementPainteri elementi : this.painteri){
                elementi.draw(g, this);
        }
    }
}
