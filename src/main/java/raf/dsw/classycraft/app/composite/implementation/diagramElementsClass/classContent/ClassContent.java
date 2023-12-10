package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public abstract class ClassContent {
    private String name;
    private Paint paint;

    public ClassContent(Paint paint,String name) {
        this.name = name;
    }
}
