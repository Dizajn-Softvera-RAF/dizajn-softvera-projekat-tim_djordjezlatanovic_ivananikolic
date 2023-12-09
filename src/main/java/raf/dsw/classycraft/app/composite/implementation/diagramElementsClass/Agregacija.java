package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;

public class Agregacija extends Connection{
    private String vidljivost;
    private int kardinalnost;
    public Agregacija(ClassyNode parent, String name) {
        super(parent, name);
    }
}
