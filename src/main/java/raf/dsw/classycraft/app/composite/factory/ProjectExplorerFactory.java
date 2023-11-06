package raf.dsw.classycraft.app.composite.factory;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.implementation.Project;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.observer.message.MessageType;

import javax.swing.*;
import java.time.LocalDateTime;

public class ProjectExplorerFactory extends ClassyNodeFactory{
    private static int cnt = 1;

    @Override
    ClassyNode createChild(String ime) {
        ClassyNode classyNode = null;
        JFrame f = new JFrame();
        String name = JOptionPane.showInputDialog("Unesite ime autora");
        while(name == null || name.isEmpty()){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate uneti ime autora", MessageType.WARNING, LocalDateTime.now());
            name = JOptionPane.showInputDialog("Unesite ime autora");
        }
        classyNode = new Project(ApplicationFramework.getInstance().getClassyRepository().getRoot(), "Project" + String.valueOf(cnt), name, "f.txt");
        cnt++;
        return classyNode;
    }
}
