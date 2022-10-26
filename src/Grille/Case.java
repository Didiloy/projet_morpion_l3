package Grille;

public class Case {
    public enum State {
        VIDE, CROIX, ROND
    }

    private boolean _jouable = true;
    private int _valeur;
    private State _state;

    public int x;
    public int y;

    public Case(int x, int y) {
        this._state = State.VIDE;
        this._valeur = 0;
        this.x = x;
        this.y = y;
    }

    /**
     * Set the value of the jouable attribut.
     * Set the value of the attribut valeur to -1 if the parameter is false
     * 
     * @param b boolean
     * @Returns: void
     */
    public void setJouable(boolean b) {
        if (!b)
            this._valeur = -1;
        this._jouable = b;
    }

    /**
     * @Returns: boolean : the value of the jouable attribut
     */
    public boolean getJouable() {
        return this._jouable;
    }

    /**
     * @Returns: int : the value of the valeur attribut
     */
    public int getValeur() {
        return _valeur;
    }

    /**
     * Set the value of the valeur attribut
     * 
     * @param valeur int
     * @Returns: void
     */
    public void setValeur(int valeur) {
        this._valeur = valeur;
    }

    /**
     * Set the value of the valeur state
     * 
     * @param state int
     * @Returns: void
     */
    public void setState(State state) {
        this._state = state;
        if (this._state != State.VIDE)
            this._jouable = false;
    }

    /**
     * @Returns: State : the value state
     */
    public State getState() {
        return this._state;
    }

    @Override
    public String toString() {
        return switch (this._state) {
            case ROND -> "O";
            case CROIX -> "X";
            case VIDE -> " ";
        };
    }
}
