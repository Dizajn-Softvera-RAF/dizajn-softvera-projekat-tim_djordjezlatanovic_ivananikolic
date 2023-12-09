package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ClassContent {
    private String name;

    public ClassContent(String name) {
        this.name = name;
    }
}
