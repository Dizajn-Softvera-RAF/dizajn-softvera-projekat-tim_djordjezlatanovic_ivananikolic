package raf.dsw.classycraft.app.composite.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeComposite;
@Getter
@Setter

public class Project extends ClassyNodeComposite {
    private String ime;
    private String imeAutora;
    private String fajl;


    public Project(String ime, String imeAutora, String fajl) {
        this.ime = ime;
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

    @Override
    public void deleteChild(ClassyNode child) {
        if(super.getChildren().contains(child)){
            super.getChildren().remove(child);
            child.setParent(null);
        }
    }
}
