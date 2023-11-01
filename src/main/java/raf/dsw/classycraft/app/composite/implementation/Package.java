package raf.dsw.classycraft.app.composite.implementation;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeComposite;

public class Package extends ClassyNodeComposite {


    public Package(ClassyNode parent, String name) {
        super(parent, name);
    }

    @Override
    public void addChild(ClassyNode child) {
        if(super.getParent() instanceof Project){
            if(child instanceof Package || child instanceof Diagram){
                if(!super.getChildren().contains(child)){
                    super.getChildren().add(child);
                    child.setParent(this);
                }
            }
        }
    }

    @Override
    public void deleteChild(ClassyNode child) {
        if(super.getChildren().contains(child)){
            super.getChildren().remove(child);
            child.setParent(null);
        }
    }
}
