package raf.dsw.classycraft.app.controller;

import com.sun.javafx.tools.packager.Main;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DodavanjeVezaButton extends AbstractClassyAction{
    public DodavanjeVezaButton(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/link.png"));
        putValue(NAME, "Add connection");
        putValue(SHORT_DESCRIPTION, "Add connection");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = " ";
        MainFrame.getInstance().getPackageView().startDodavanjeVeze(s);
    }
}
