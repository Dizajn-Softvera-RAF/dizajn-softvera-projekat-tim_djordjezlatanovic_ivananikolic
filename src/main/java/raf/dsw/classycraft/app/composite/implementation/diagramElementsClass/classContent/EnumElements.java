package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeLeaf;

import java.awt.*;

public class EnumElements extends ClassContent{

    public EnumElements(ClassyNodeLeaf classyNodeLeaf, Paint paint, String name) {
        super(classyNodeLeaf, paint, name);
    }

    @Override
    public boolean equals(Object obj) {
        return this.getName().equals(obj.toString());
    }

    @Override
    public String toString() {
        return "Enum: " + this.getName();
    }
}
