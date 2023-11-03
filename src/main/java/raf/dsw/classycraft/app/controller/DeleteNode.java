package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.composite.implementation.Package;
import raf.dsw.classycraft.app.composite.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.message.MessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;

public class DeleteNode extends AbstractClassyAction{
    public DeleteNode() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F9, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "Delete node");
        putValue(SHORT_DESCRIPTION, "Delete node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getCLassyTree().getSelectedNode();
        ClassyNodeComposite node = null;
        if(selected == null){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Morate selektovati cvor u stablu koji zelite da obrisete", MessageType.WARNING, LocalDateTime.now());
        }
        else if(selected != null){
            node = (ClassyNodeComposite) selected.getClassyNode();
            if (node instanceof ProjectExplorer) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Project Explorer ne moze da se obrise", MessageType.WARNING, LocalDateTime.now());
            }
            else if(selected instanceof ClassyTreeItem){

                MainFrame.getInstance().getCLassyTree().deleteChild(selected);

            }
        }

    }

    }

