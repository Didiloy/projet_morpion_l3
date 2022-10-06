package Grille;

public class Case {
    public enum State {VIDE, CROIX, ROND};
    private State _state;
    public Case(){
        this._state = State.CROIX;
    }

    public void setState(State state){
        this._state = state;
    }

    public State getState(){
        return this._state;
    }

    @Override
    public String toString(){
        return switch (this._state){
            case ROND ->  "O";
            case CROIX -> "X";
            case VIDE -> " ";
        };
    }
}
