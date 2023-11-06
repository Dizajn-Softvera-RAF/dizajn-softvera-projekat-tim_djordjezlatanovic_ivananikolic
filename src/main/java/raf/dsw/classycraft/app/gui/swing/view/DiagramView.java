package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.implementation.Diagram;
import raf.dsw.classycraft.app.observer.Subscriber;

import javax.swing.*;
@Getter
@Setter

public class DiagramView extends JPanel implements Subscriber {
    private Diagram diagram;

    private JPanel panel;
    private String imeTaba;
    private PackageView packageView;
    private int brojac;

    public DiagramView(Diagram prosledjeniDiagram, int brojac, PackageView prosledjeniPackageView) {
        this.packageView = prosledjeniPackageView;
        this.imeTaba = prosledjeniDiagram.getName();
        this.brojac = brojac;
        this.diagram = prosledjeniDiagram;
        this.diagram.addSubscriber(this);
    }


    @Override
    public void update(Object var1,String tekst) {
        imeTaba = String.valueOf(var1);
        packageView.promenaImena(imeTaba, brojac);
    }
}
