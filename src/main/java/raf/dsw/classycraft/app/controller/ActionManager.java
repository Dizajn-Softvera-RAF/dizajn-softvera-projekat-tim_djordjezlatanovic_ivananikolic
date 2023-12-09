package raf.dsw.classycraft.app.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager{
    private Move move;
    private NewProjectAction newProjectAction;
    private ExitAction exitAction;
    private  AboutUs aboutUs;
    private DeleteNode deleteNode;
    private BrisanjeButton brisanjeButton;
    private DodavanjeSadrzajaKlaseButton dodavanjeSadrzajaKlaseButton;
    private DodavanjeInterclassObjektaButton dodavanjeInterclassObjektaButton;
    private DodavanjeVezaButton dodavanjeVezaButton;
    private SelekcijaButton selekcijaButton;

    public ActionManager() {
        move = new Move();
        newProjectAction = new NewProjectAction();
        exitAction = new ExitAction();
        aboutUs = new AboutUs();
        deleteNode = new DeleteNode();
        selekcijaButton = new SelekcijaButton();
        dodavanjeSadrzajaKlaseButton = new DodavanjeSadrzajaKlaseButton();
        dodavanjeVezaButton = new DodavanjeVezaButton();
        dodavanjeInterclassObjektaButton = new DodavanjeInterclassObjektaButton();
        brisanjeButton = new BrisanjeButton();
    }


}
