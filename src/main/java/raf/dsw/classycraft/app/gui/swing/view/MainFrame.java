package raf.dsw.classycraft.app.gui.swing.view;


import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.JTree.CLassyTree;
import raf.dsw.classycraft.app.JTree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.controller.ActionManager;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.observer.message.Logger;
import raf.dsw.classycraft.app.observer.message.Message;
import raf.dsw.classycraft.app.observer.message.MessageType;

import javax.swing.*;
import java.awt.*;
@Getter
@Setter
public class MainFrame extends JFrame implements Logger {

    private static MainFrame insance;
    private ActionManager actionManager;
    private CLassyTree cLassyTree;
    //buduca polja za sve komponente view-a na glavnom prozoru
    private MainFrame(){
        actionManager = new ActionManager();
        ApplicationFramework.getInstance().getMessageGenerator().addLogger(this);
        cLassyTree = new ClassyTreeImplementation();

    }

    private void initialize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");



        MyMenuBar menu = new MyMenuBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);



        JTree jTree = cLassyTree.generateTree(ApplicationFramework.getInstance().getClassyRepository().getRoot());
        JPanel panel = new JPanel(); // leva strana ekrana

        JScrollPane jScrollPane = new JScrollPane(jTree);
        jScrollPane.setMinimumSize(new Dimension(200, 150));
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollPane, panel);
        getContentPane().add(split, BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);



    }
    public static MainFrame getInstance(){
        if(insance == null){
            insance = new MainFrame();
            insance.initialize();
        }
        return insance;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    @Override
    public void update(Message message) {
        JOptionPane jOptionPane = new JOptionPane();
        JFrame f = new JFrame();
        if(message.getType()== MessageType.ERROR){
            jOptionPane.showMessageDialog(f,message.toString(),String.valueOf(MessageType.ERROR),jOptionPane.ERROR_MESSAGE);

        }
        if(message.getType() == MessageType.WARNING){
            jOptionPane.showMessageDialog(f,message.toString(),String.valueOf(MessageType.WARNING),jOptionPane.WARNING_MESSAGE);
        }
        if(message.getType() == MessageType.NOTIFICATION){
            jOptionPane.showMessageDialog(f,message.toString(),"INFORMATION",jOptionPane.INFORMATION_MESSAGE);
        }
    }

}
