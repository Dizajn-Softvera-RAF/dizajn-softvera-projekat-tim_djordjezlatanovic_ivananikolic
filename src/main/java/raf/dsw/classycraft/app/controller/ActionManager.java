package raf.dsw.classycraft.app.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager{
    private ExitAction exitAction;
    private  AboutUs aboutUs;
    public ActionManager(){
        exitAction = new ExitAction();
        aboutUs = new AboutUs();
    }


}
