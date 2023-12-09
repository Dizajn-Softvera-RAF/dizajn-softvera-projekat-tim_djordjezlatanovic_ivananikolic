package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.implementation.DiagramElements;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.ClassContent;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter

public abstract class Interclass extends DiagramElements {
    private String naziv;
    private String vidljivost;
    private List<Integer> tacke;
    private List<ClassContent> classContentList = new ArrayList<>();

    public Interclass(ClassyNode parent, String name) {
        super(parent, name);
    }
}
