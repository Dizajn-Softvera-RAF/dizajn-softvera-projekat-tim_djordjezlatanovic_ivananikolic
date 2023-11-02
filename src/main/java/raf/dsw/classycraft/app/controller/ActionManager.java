package raf.dsw.classycraft.app.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager{
    private NewProjectAction newProjectAction;
    private ExitAction exitAction;
    private  AboutUs aboutUs;
    private DeleteNode deleteNode;
    public ActionManager(){
        newProjectAction = new NewProjectAction();
        exitAction = new ExitAction();
        aboutUs = new AboutUs();
        deleteNode = new DeleteNode();
    }


}
