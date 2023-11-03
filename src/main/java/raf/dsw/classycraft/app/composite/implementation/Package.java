package raf.dsw.classycraft.app.composite.implementation;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeComposite;

import java.util.List;

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

//    private void delete(List<ClassyNode> deca,ClassyNodeComposite root,ClassyNodeComposite pom) {
//
//        if (root==null)
//            return;
//        for (ClassyNode c: deca) {
//            if(c instanceof ClassyNodeComposite) {
//                System.out.println(((ClassyNodeComposite) c.getParent()));
//                delete(((ClassyNodeComposite) c).getChildren(), root, ((ClassyNodeComposite) c.getParent()));
//            }else if (c instanceof ClassyNode) {
//
//                pom.deleteChild(c);
//            }
//        }
//
//    }




}
