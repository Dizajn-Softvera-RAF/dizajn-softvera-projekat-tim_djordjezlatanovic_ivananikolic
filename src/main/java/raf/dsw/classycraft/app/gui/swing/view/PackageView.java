package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.implementation.Diagram;
import raf.dsw.classycraft.app.composite.implementation.Package;
import raf.dsw.classycraft.app.composite.implementation.Project;
import raf.dsw.classycraft.app.composite.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.observer.Subscriber;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter

public class PackageView extends JPanel implements Subscriber {

    private Package aPackage;
    private Project aProject;
    private ProjectExplorer aProjectExplorer;
    private JLabel imeAutora;
    private JLabel imeProjekta;
    private JTabbedPane jTabbedPane;

    private static int brojac = 0;
    private JPanel box;

    public PackageView(LayoutManager layoutManager){
        super(layoutManager);
        jTabbedPane = new JTabbedPane();
        jTabbedPane.setTabPlacement(JTabbedPane.TOP);
        jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        jTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane.setPreferredSize(new Dimension(500, 300));
        add(jTabbedPane);
        imeAutora = new JLabel();
        imeProjekta = new JLabel();
        box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        add(box);
    }
    public void dodajLabele(String imeAutora, String imeParenta){
        this.imeProjekta.removeAll();
        this.imeAutora.removeAll();
        this.imeProjekta.setText("Ime projekta: "+imeParenta);
        this.imeAutora.setText("Ime autora: "+imeAutora);
        box.add(this.imeAutora);
        box.add(this.imeProjekta);
        box.setPreferredSize(new Dimension(150, 30));
        box.revalidate();
        box.repaint();
    }

    public void setaPackage(Package aPackage, Project aProject, ProjectExplorer aProjectExplorer) {
        this.aPackage = aPackage;
        aPackage.addSubscriber(this);
        aProject.addSubscriber(this);
        aProjectExplorer.addSubscriber(this);
        dodajTab();
    }

    private void dodajTab() {
        System.out.println(aPackage.getChildren());
        for(ClassyNode c : aPackage.getChildren()){

            if(c instanceof Diagram){
                DiagramView diagramView = new DiagramView((Diagram) c, this.brojac, this);

                jTabbedPane.add(diagramView.getImeTaba(), diagramView);
                brojac++;
            }
        }

        jTabbedPane.revalidate();
        jTabbedPane.repaint();
    }
    public void promenaImena(String ime, int brojac){
        jTabbedPane.setTitleAt(brojac, ime);
    }
    public void refreshTabs(Diagram var1){
        for (int i =0; i< jTabbedPane.getTabCount();i++){
            //if(jTabbedPane.getComponentAt(i).para)
        }
    }

    @Override
    public void update(Object var1) {
        if(var1 instanceof Diagram)
            refreshTabs((Diagram)var1);

    }
}
