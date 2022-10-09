package Grille;

import java.util.ArrayList;

public class Quintuplet {
    private int _valeur;

    public enum EtatQuintuplet { OUVERT, FERME, VIDE}

    private EtatQuintuplet _etat;

    private ArrayList<Case> _lCases;

    public Quintuplet(ArrayList<Case> l) {
        this._lCases = l;
        this._lCases.forEach(c -> this._valeur += c.getValeur());
        this._etat = EtatQuintuplet.VIDE;
    }

    public ArrayList<Case> get_lCases() {
        return this._lCases;
    }

    public int getValeur() {
        return _valeur;
    }

    public void updateValeur() {
        this._valeur = 0;
        this._lCases.forEach(c -> this._valeur += c.getValeur());
    }

    public EtatQuintuplet getEtat(){
        return this._etat;
    }

    public void updateEtat(){
        if(this._lCases.stream().allMatch(c -> c.getState() == Case.State.VIDE)){
            this._etat = EtatQuintuplet.VIDE;
            return;
        }
        if(this._lCases.stream().allMatch(c -> c.getState() == Case.State.CROIX
                                            || c.getState() == Case.State.VIDE)){
            this._etat = EtatQuintuplet.OUVERT;
            return;
        }
        if(this._lCases.stream().allMatch(c -> c.getState() == Case.State.ROND
                || c.getState() == Case.State.VIDE)){
            this._etat = EtatQuintuplet.OUVERT;
        }
        else this._etat = EtatQuintuplet.FERME;
    }
}
