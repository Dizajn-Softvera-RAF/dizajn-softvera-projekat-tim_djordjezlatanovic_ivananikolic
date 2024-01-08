package raf.dsw.classycraft.app.undo;

import raf.dsw.classycraft.app.composite.implementation.Package;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interclass;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Interfejs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.Klasa;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.EnumElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Methods;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.painters.AtributPainter;
import raf.dsw.classycraft.app.painters.ElementPainteri;
import raf.dsw.classycraft.app.painters.EnumElementsPainter;
import raf.dsw.classycraft.app.painters.MetodaPainter;

public class BrisanjeElementCommand extends AbstractCommand{
    private ElementPainteri elementPainteri;
    private AtributPainter atributPainter;
    private MetodaPainter metodaPainter;
    private EnumElementsPainter enumElements;
    private DiagramView diagramView;
    private boolean flag = false;
    private Interclass interclass;

    public BrisanjeElementCommand(ElementPainteri elementPainteri, DiagramView diagramView, Interclass interclass) {
        this.elementPainteri = elementPainteri;
        this.diagramView = diagramView;
        this.interclass = interclass;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setAtributPainter(AtributPainter atributPainter) {
        this.atributPainter = atributPainter;
    }

    public void setMetodaPainter(MetodaPainter metodaPainter) {
        this.metodaPainter = metodaPainter;
    }

    public void setEnumElements(EnumElementsPainter enumElements) {
        this.enumElements = enumElements;
    }

    @Override
    public void doCommand() {
        if(flag){
            diagramView.getPainteri().remove(elementPainteri);

            if(interclass instanceof Klasa){
                if(!(atributPainter == null))
                    ((Klasa)interclass).getAtributsList().remove(atributPainter.getAtribut());
                else if(!(metodaPainter == null))
                    ((Klasa)interclass).getAtributsList().remove(metodaPainter.getMethods());
                else if(!(enumElements == null))
                    ((Klasa)interclass).getAtributsList().remove(enumElements.getEnumElements());
            }
            else if(interclass instanceof Interfejs){
                if(!(metodaPainter == null))
                    ((Klasa)interclass).getAtributsList().remove(metodaPainter.getMethods());
            }
            else {
                if(!(enumElements == null))
                    ((Klasa)interclass).getAtributsList().remove(enumElements.getEnumElements());
            }
            diagramView.getDiagram().selektovano();
        }

    }

    @Override
    public void undoCommand() {
        this.flag = true;
        diagramView.getPainteri().add(elementPainteri);

        if(interclass instanceof Klasa){
            if(!(atributPainter == null))
             ((Klasa)interclass).getAtributsList().add(atributPainter.getAtribut());
            else if(!(metodaPainter == null))
                ((Klasa)interclass).getAtributsList().add(metodaPainter.getMethods());
            else if(!(enumElements == null))
                ((Klasa)interclass).getAtributsList().add(enumElements.getEnumElements());
        }
        else if(interclass instanceof Interfejs){
            if(!(metodaPainter == null))
                ((Klasa)interclass).getAtributsList().add(metodaPainter.getMethods());
        }
        else {
            if(!(enumElements == null))
                ((Klasa)interclass).getAtributsList().add(enumElements.getEnumElements());
        }
        diagramView.getDiagram().selektovano();
    }
}
