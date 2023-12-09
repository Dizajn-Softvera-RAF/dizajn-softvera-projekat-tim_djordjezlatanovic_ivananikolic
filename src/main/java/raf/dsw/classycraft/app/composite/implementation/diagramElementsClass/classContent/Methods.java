package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent;

public class Methods extends ClassContent{
    private String vidljivost;
    private boolean isStatic;
    private boolean isAbstract;
    public Methods(String name, String vidljivost, boolean isStatic, boolean isAbstract) {
        super(name);
        this.vidljivost = vidljivost;
        this.isStatic = isStatic;
        this.isAbstract = isAbstract;
    }
}
