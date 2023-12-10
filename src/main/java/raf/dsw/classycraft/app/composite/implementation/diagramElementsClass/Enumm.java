package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass;

import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.ClassContent;
import raf.dsw.classycraft.app.observer.Publisher;
import raf.dsw.classycraft.app.observer.Subscriber;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Enumm extends Interclass implements Publisher {
    private java.util.List<ClassContent> atributsList = new ArrayList<>();

    private List<Subscriber> subscribers = new ArrayList<>();
    private static int broj = 20;

    public Enumm(ClassyNode parent, String name, int x, int y, int width, int height,Paint paint) {
        super(parent, name, x, y, width, height,paint);
    }

    @Override
    public void addSubscriber(Subscriber var1) {
        if(var1 != null){
            if(!this.subscribers.contains(var1)){
                this.subscribers.add(var1);
            }
        }
    }

    @Override
    public void removeSubscriber(Subscriber var1) {
        if(var1 != null && this.subscribers!= null && this.subscribers.contains(var1))
            this.subscribers.remove(var1);
    }

    @Override
    public void notifySubscriber(Object var1,String tekst) {
        if(var1 == null || this.subscribers == null || this.subscribers.isEmpty())
            return;
        for (Subscriber s : this.subscribers)
            s.update(var1,tekst);
    }
}
