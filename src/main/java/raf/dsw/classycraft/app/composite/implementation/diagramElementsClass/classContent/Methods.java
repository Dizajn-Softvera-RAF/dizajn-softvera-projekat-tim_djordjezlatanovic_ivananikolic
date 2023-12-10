package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent;

import java.awt.*;

public class Methods extends ClassContent{
    private String vidljivost;
    private boolean isStatic;
    private boolean isAbstract;
    public Methods(Paint paint,String name, String vidljivost, boolean isStatic, boolean isAbstract) {
        super(paint, name);
        this.vidljivost = vidljivost;
        this.isStatic = isStatic;
        this.isAbstract = isAbstract;
    }
}
