package raf.dsw.classycraft.app.composite.implementation.diagramElementsClass;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.composite.abstraction.ClassyNode;
import raf.dsw.classycraft.app.composite.implementation.Diagram;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.Atributs;
import raf.dsw.classycraft.app.composite.implementation.diagramElementsClass.classContent.ClassContent;
import raf.dsw.classycraft.app.observer.Publisher;
import raf.dsw.classycraft.app.observer.Subscriber;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class Klasa extends Interclass implements Publisher{
    private List<ClassContent> atributsList = new ArrayList<>();

    private List<Subscriber> subscribers = new ArrayList<>();
    private static int broj = 20;

    public Klasa(ClassyNode parent, String name, int x, int y, int width, int height,Paint paint) {
        super(parent, name, x, y, width, height,paint);

    }

    public Klasa(ClassyNode parent, String name) {
        super(parent, name);
    }

    public boolean dodaj(ClassContent classContent){
        if(atributsList.isEmpty())
            this.broj = 20;
        if(!(atributsList.contains(classContent))){
            if(classContent instanceof Atributs) {
                ((Atributs) classContent).setBroj(broj);
                broj+=20;

                atributsList.add(classContent);
                ((Diagram)getParent()).notifySubscriber("","crtanje");
            }

            return true;
        }
        return false;
    }
    public void ukloniSadrzaj(ClassContent classContent){

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
