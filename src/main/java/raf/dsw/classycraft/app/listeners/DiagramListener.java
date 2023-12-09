package raf.dsw.classycraft.app.listeners;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DiagramListener implements MouseListener, MouseMotionListener {
    private DiagramView diagramView;

    public DiagramListener(DiagramView diagramView) {
        this.diagramView = diagramView;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pressed");
        MainFrame.getInstance().getPackageView().misKliknut(e.getX(), e.getY(), this.diagramView);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("released");
        MainFrame.getInstance().getPackageView().misOtpusten(e.getX(), e.getY(), this.diagramView);

    }
    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("aa");
        MainFrame.getInstance().getPackageView().misPovucen(e.getX(), e.getY(), this.diagramView);

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }



    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
