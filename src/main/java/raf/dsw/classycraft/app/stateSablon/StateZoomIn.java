package raf.dsw.classycraft.app.stateSablon;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

public class StateZoomIn implements State{
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        double newScaling = diagramView.getScale() * 1.08;
        if(newScaling >= 5) newScaling = 5;
        diagramView.setTr(newScaling);
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {

    }
}
