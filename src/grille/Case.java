package grille;

import common.enums.ANSIColor;
import common.enums.StateEnum;

public class Case {

    private boolean _jouable = true;
    private int _valeur;
    private StateEnum _state;

    public int x;
    public int y;

    public Case(int x, int y) {
        this._state = StateEnum.VIDE;
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
    public void setState(StateEnum state) {
        this._state = state;
        this._jouable = this._state == StateEnum.VIDE;
    }

    /**
     * @Returns: State : the value state
     */
    public StateEnum getState() {
        return this._state;
    }

    @Override
    public String toString() {
        switch (this._state) {
            case ROND:
                return ANSIColor.ANSI_CYAN + "O" + ANSIColor.ANSI_RESET;
            case CROIX:
                return ANSIColor.ANSI_RED + "X" + ANSIColor.ANSI_RESET;
            case VIDE:
                return " ";
            default:
                throw new IllegalArgumentException();
        }
    }
}
