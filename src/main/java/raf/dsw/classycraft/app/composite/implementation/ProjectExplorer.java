package raf.dsw.classycraft.app.composite.implementation;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeComposite;

public class ProjectExplorer extends ClassyNodeComposite {

    public ProjectExplorer(String name) {
        super(null, name);
    }

    @Override
    public void addChild(ClassyNode child) {
            if(child instanceof Project){
                if(!super.getChildren().contains(child)){
                    super.getChildren().add(child);
                }
            }
    }

    @Override
    public void deleteChild(ClassyNode child) {
        if(super.getChildren().contains(child)){
            super.getChildren().remove(child);
        }
    }
}
