package raf.dsw.classycraft.app.stateSablon;

public class StateManager {
    private State currentState;
    private Brisanje brisanje;
    private DodavanjeInterclassObjekata dodavanjeInterclassObjekata;
    private DodavanjeSadzrajaKlase dodavanjeSadzrajaKlase;
    private DodavanjeVeza dodavanjeVeza;
    private Selekcija selekcija;
    private MoveState moveState;

    public StateManager(String s) {
        inita(s);
    }

    private void inita(String s) {
        brisanje = new Brisanje(s);
        dodavanjeInterclassObjekata = new DodavanjeInterclassObjekata(s);
        dodavanjeSadzrajaKlase = new DodavanjeSadzrajaKlase(s);
        dodavanjeVeza = new DodavanjeVeza(s);
        selekcija = new Selekcija();
        moveState = new MoveState();
    }

    public void setBrisanje(String s) {
        currentState = this.brisanje;
    }

    public void setDodavanjeInterclassObjekata(String s) {
        currentState = this.dodavanjeInterclassObjekata;
    }

    public void setDodavanjeSadzrajaKlase() {
        currentState = this.dodavanjeSadzrajaKlase;
    }

    public void setDodavanjeVeza() {
        currentState = this.dodavanjeVeza;
    }

    public void setSelekcija() {
        currentState = this.selekcija;
    }
    public void setMoveState(){currentState = this.moveState;}

    public State getCurrentState() {
        return currentState;
    }
}
