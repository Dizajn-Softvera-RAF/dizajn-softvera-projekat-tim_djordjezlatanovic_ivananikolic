package raf.dsw.classycraft.app.composite.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeComposite;

import java.util.List;

@Getter
@Setter

public class Project extends ClassyNodeComposite {
    private String imeAutora;
    private String fajl;

    public Project(ClassyNode parent, String name, String imeAutora, String fajl) {
        super(parent, name);
        this.imeAutora = imeAutora;
        this.fajl = fajl;
    }


    @Override
    public void addChild(ClassyNode child) {
        if(super.getParent() instanceof ProjectExplorer){
            if(child instanceof Package){
                if(!super.getChildren().contains(child)){
                    super.getChildren().add(child);
                    child.setParent(this);
                }
            }
        }
    }



}
