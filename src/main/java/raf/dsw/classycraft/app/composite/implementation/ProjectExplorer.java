package raf.dsw.classycraft.app.composite.implementation;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeComposite;

import java.util.List;

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


    private void delete(List<ClassyNode> deca,ClassyNodeComposite root,ClassyNodeComposite pom) {

        if (root==null)
            return;
        for (ClassyNode c: deca) {
            if(c instanceof ClassyNodeComposite) {
                System.out.println(((ClassyNodeComposite) c.getParent()));
                delete(((ClassyNodeComposite) c).getChildren(), root, ((ClassyNodeComposite) c.getParent()));
            }else if (c instanceof ClassyNode) {

                pom.deleteChild(c);
            }
        }

    }

}
