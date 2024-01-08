package raf.dsw.classycraft.app.undo;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.composite.implementation.Package;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Enumm;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interfejs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.ClassContent;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.painters.ElementPainteri;

public class AddElementsCommnad extends AbstractCommand{

    private ClassContent classContent;
    private ElementPainteri elementPainteri;
    private Interclass interclass;
    private DiagramView diagramView;

    public AddElementsCommnad(ClassContent classContent, ElementPainteri elementPainteri, Interclass interclass, DiagramView diagramView) {
        this.classContent = classContent;
        this.elementPainteri = elementPainteri;
        this.interclass = interclass;
        this.diagramView = diagramView;
    }

    @Override
    public void doCommand() {
        if(interclass instanceof Klasa){
            if(!((Klasa)interclass).getAtributsList().contains(classContent))
                 ((Klasa)interclass).getAtributsList().add(classContent);
        } else if (interclass instanceof Enumm) {
            if(!((Enumm)interclass).getAtributsList().contains(classContent))
            ((Enumm)interclass).getAtributsList().add(classContent);
        }
        else {
            if(!((Interfejs)interclass).getAtributsList().contains(classContent))
            ((Interfejs)interclass).getAtributsList().add(classContent);
        }

        if(!(diagramView.getPainteri().contains(elementPainteri)))
             diagramView.getPainteri().add(elementPainteri);
        diagramView.getDiagram().selektovano();
    }

    @Override
    public void undoCommand() {
        if(interclass instanceof Klasa){
            ((Klasa)interclass).getAtributsList().remove(classContent);
        } else if (interclass instanceof Enumm) {
            ((Enumm)interclass).getAtributsList().remove(classContent);
        }
        else ((Interfejs)interclass).getAtributsList().remove(classContent);

        diagramView.getPainteri().remove(elementPainteri);
        diagramView.getDiagram().selektovano();
    }
}
