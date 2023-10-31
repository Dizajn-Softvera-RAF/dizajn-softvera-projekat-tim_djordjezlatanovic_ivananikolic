package raf.dsw.classycraft.app.composite.abstraction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ClassyNode {
    private ClassyNode parent;

    public ClassyNode() {

    }

}
