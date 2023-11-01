package raf.dsw.classycraft.app.composite.implementation;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeLeaf;

public class Diagram extends ClassyNodeLeaf {
    private String ime;


    public Diagram(ClassyNode parent, String name) {
        super(parent, name);
    }
}
