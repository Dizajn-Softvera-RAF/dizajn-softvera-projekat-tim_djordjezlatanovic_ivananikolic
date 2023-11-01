package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.AboutUs;
import raf.dsw.classycraft.app.controller.ExitAction;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar(){
        JMenu fileMenu = new JMenu("File");
        JMenu help = new JMenu("Edit");
        JMenu add = new JMenu("New Project");
        add.setMnemonic(KeyEvent.VK_S);
        fileMenu.setMnemonic(KeyEvent.VK_F);
        help.setMnemonic(KeyEvent.VK_C);
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        help.add(MainFrame.getInstance().getActionManager().getAboutUs());
        add(fileMenu);
        add(help);
    }
}
