package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.message.MessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;

public class BrisanjeButton extends AbstractClassyAction{
    public BrisanjeButton(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "Delete element");
        putValue(SHORT_DESCRIPTION, "Delete element");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog.setDefaultLookAndFeelDecorated(true);
        Object[] selectionValues = {"Element", "Diagram element"};
        String basicSelection = "Element";
        Object selection = JOptionPane.showInputDialog(null, "Koji cvor zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
        while(selection == null){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati neku od ponudjenih opcija", MessageType.COMPONENT_NOT_SELECTED, LocalDateTime.now());
            selection = JOptionPane.showInputDialog(null, "Koji cvor zelite da izaberete?", "Pitanje", JOptionPane.QUESTION_MESSAGE, null, selectionValues, basicSelection);
        }
        String s = selection.toString();

        MainFrame.getInstance().getPackageView().startBrisanje(s);
    }
}
