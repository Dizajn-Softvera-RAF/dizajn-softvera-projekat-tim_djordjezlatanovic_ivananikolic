package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;

public class Agregacija extends Connection{
    private String vidljivost;

    public Agregacija(ClassyNode parent, String name, int x, int y) {
        super(parent, name, x, y);
    }
}
