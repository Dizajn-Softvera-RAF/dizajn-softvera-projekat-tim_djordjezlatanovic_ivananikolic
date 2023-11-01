package raf.dsw.classycraft.app.composite.factory;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.implementation.Project;
import raf.dsw.classycraft.app.core.ApplicationFramework;

public class DiagramFactory extends ClassyNodeFactory{
    private static int cnt = 1;
    @Override
    ClassyNode createChild(String ime) {
       /* ClassyNode classyNode = null;
        classyNode = new Project(ApplicationFramework.getInstance().getClassyRepository().getRoot(), "Project" + String.valueOf(cnt), "Djordje", "f.txt");
        cnt++;*/
        return null;
    }


}
