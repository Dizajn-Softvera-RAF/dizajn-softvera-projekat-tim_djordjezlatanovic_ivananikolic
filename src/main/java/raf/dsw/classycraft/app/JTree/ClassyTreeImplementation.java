package raf.dsw.classycraft.app.JTree;

import raf.dsw.classycraft.app.JTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.JTree.view.ClassyTreeView;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNodeComposite;
import raf.dsw.classycraft.app.composite.factory.ClassyNodeFactory;
import raf.dsw.classycraft.app.composite.factory.FactoryUtil;
import raf.dsw.classycraft.app.composite.factory.ProjectExplorerFactory;
import raf.dsw.classycraft.app.composite.factory.ProjectFactory;
import raf.dsw.classycraft.app.composite.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class ClassyTreeImplementation implements CLassyTree{
    private ClassyTreeView classyTreeView;
    private DefaultTreeModel treeModel;
    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        ClassyTreeItem root = new ClassyTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        classyTreeView = new ClassyTreeView(treeModel);
        return classyTreeView;
    }

    @Override
    public void addChild(ClassyTreeItem parent, String ime) {
        if(!(parent.getClassyNode() instanceof ClassyNodeComposite))
            return;
        ClassyNode child = createChild(parent.getClassyNode(), ime);
        parent.add(new ClassyTreeItem(child));
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);//proveriti metodu addchild u modelima nodova
        classyTreeView.expandPath(classyTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(classyTreeView);

    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) classyTreeView.getLastSelectedPathComponent();
    }

    public ClassyNode createChild(ClassyNode parent, String naziv){
        ClassyNodeFactory factory = FactoryUtil.createFactory(parent);
        return factory.getChild(naziv);

    }


}
