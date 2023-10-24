package raf.dsw.classycraft.app.controller;

public class ActionManager{
    private ExitAction exitAction;
    private  AboutUs aboutUs;
    public ActionManager(){
        exitAction = new ExitAction();
        aboutUs = new AboutUs();
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public AboutUs getAboutUs() {
        return aboutUs;
    }
}
