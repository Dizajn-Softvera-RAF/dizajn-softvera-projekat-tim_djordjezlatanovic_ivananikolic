package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.abstraction.ClassyRepository;
import raf.dsw.classycraft.app.composite.implementation.ClassyRepositoryImplementation;
import raf.dsw.classycraft.app.composite.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

public class ApplicationFramework {
    private static ApplicationFramework instance;
    private ClassyRepository classyRepository;
    private ApplicationFramework(){
    }
    public void initialize(){
        MainFrame.getInstance().setVisible(true);
         classyRepository = new ClassyRepositoryImplementation();
         //classyRepository.getRoot();
    }
    public static ApplicationFramework getInstance(){
        if(instance == null){
            instance = new ApplicationFramework();
        }
        return instance;
    }
}
