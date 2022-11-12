package grille;

import common.enums.StateEnum;

import java.util.ArrayList;


public class Quintuplet {
    private int _valeur;

    public enum EtatQuintuplet {
        OUVERT, FERME, VIDE, COMPLET
    }

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

    /**
     * Calcul the value of the quintuplet.
     * The state of the quintuplet must be updated before calling this function
     * 
     * @param s determine which pawn weighs the most.
     */
    public void updateValeur(StateEnum s) {
        if (s == StateEnum.VIDE)
            return;
        if (this._etat == EtatQuintuplet.FERME)
            return;
        this._valeur = 20;
        // this._lCases.forEach(c -> this._valeur += c.getValeur());
        this._lCases.forEach(c -> {
            if (c.getState() == s)
                this._valeur += 10;
            else
                this._valeur += 9;
        });
    }

    public EtatQuintuplet getEtat() {
        return this._etat;
    }

    public void updateEtat() {
        if (this._lCases.stream().allMatch(c -> c.getState() == StateEnum.VIDE)) {
            this._etat = EtatQuintuplet.VIDE;
            return;
        }
        if (this._lCases.stream().allMatch(c -> c.getState() == StateEnum.CROIX
                || c.getState() == StateEnum.VIDE)) {
            this._etat = EtatQuintuplet.OUVERT;
            return;
        }
        if (this._lCases.stream().allMatch(c -> c.getState() == StateEnum.ROND
                || c.getState() == StateEnum.VIDE)) {
            this._etat = EtatQuintuplet.OUVERT;
            return;
        }
        if (this._lCases.stream().allMatch(c -> c.getState() == StateEnum.ROND)) {
            this._etat = EtatQuintuplet.COMPLET;
            return;
        }
        if (this._lCases.stream().allMatch(c -> c.getState() == StateEnum.CROIX)) {
            this._etat = EtatQuintuplet.COMPLET;
        } else
            this._etat = EtatQuintuplet.FERME;
    }
}
