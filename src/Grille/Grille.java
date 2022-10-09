package Grille;

import java.util.ArrayList;
import java.util.List;

public class Grille {
    private final int X_MINIMUM = 5;
    private final int Y_MINIMUM = 5;
    private final int X_DEFAULT = 10;
    private final int Y_DEFAULT = 10;
    private final int X_MAXIMUM = 100;
    private final int Y_MAXIMUM = 100;

    private int x;
    private int y;

    private Case[][] _tabCases;

    private List<Quintuplet> _lQuint = new ArrayList<>();

    public Grille(int x, int y){
        this.x = x;
        this.y = y;
        _tabCases = new Case[x < X_MINIMUM  || x > X_MAXIMUM ? X_DEFAULT : x + 10]
                            [y < Y_MINIMUM  || y > Y_MAXIMUM ? Y_DEFAULT : y + 10];
        initTab();
        initQuint();
    }

    /**
     * Initialise the array of Case by creating the case and setting the extern one to false.
     * @Returns: void
     */
    private void initTab(){
        for (int i = 0; i < _tabCases.length; i++) {
            for (int j = 0; j < _tabCases[i].length; j++) {
                _tabCases[i][j] = new Case(i, j);
                if(i < 5 || j < 5 || i > this.x + 4|| j > this.y + 4 ) _tabCases[i][j].setJouable(false);
            }
        }
    }

    /**
     * Initialise the Quintuplet arraylist by creating all the quintuplet of the grid
     */
    private void initQuint(){
        //TODO ajouter les diagonales
        for (int i = 5; i < _tabCases.length - 5; i++) {
            for (int j = 5; j < _tabCases[i].length - 5; j++) {
                //Si on peut aligner 5 case horizontalement sans sortir de la grille on ajoute le quintuplet
                if(j + 4 < _tabCases[i].length - 5){
                    ArrayList<Case> l = new ArrayList<>();
                    for (int k = j; k < j + 5; k++) {
                        l.add(_tabCases[i][k]);
                    }
                    this._lQuint.add(new Quintuplet(l));
                }
                //Si on peut aligner 5 case verticalement sans sortir de la grille on ajoute le quintuplet
                if(i + 4 < _tabCases.length - 5){
                    ArrayList<Case> l = new ArrayList<>();
                    for (int k = i; k < i + 5; k++) {
                        l.add(_tabCases[k][j]);
                    }
                    this._lQuint.add(new Quintuplet(l));
                }

            }
        }
    }

    /**
     * Print the grid with only the playable grid visible.
     * @Returns: void
     */
    public void print(){
        for (int i = 0; i < _tabCases.length * 2 + 1; i++) {
            if(i < 5 || i > this.x + 4) continue;
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < _tabCases.length; i++) {
            if(i < 5 || i > this.x + 4) continue;
            System.out.print("|");
            for (int j = 0; j < _tabCases[i].length; j++) {
                if(j < 5 || j > this.y + 4 ) continue;
                System.out.print(_tabCases[i][j] + "|");
            }
            System.out.println();
        }
        for (int i = 0; i < _tabCases.length * 2 + 1; i++) {
            if(i < 5 || i > this.x + 4 ) continue;
            System.out.print("-");
        }
    }

    /**
     * Print the list of Quintuplet
     */
    public void printQuint(){
        this._lQuint.forEach(l -> {
            System.out.print("[");
            l.get_lCases().forEach(c -> System.out.print("(" + c.x + "," + c.y +")"));
            System.out.println("]");
        });
    }

    /**
     * Print the grid with all the cases visibles, even the non-playable one.
     * @Returns: void
     */
    public void printAll(){
        for (int i = 0; i < _tabCases.length * 2 + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < _tabCases.length; i++) {
            System.out.print("|");
            for (int j = 0; j < _tabCases[i].length; j++) {
                System.out.print(_tabCases[i][j].getValeur() + "|");
            }
            System.out.println();
        }
        for (int i = 0; i < _tabCases.length * 2 + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    //TODO
    //Ajouter un pion
    //supprimer un pion
    // calculer les valeurs des cases
    // liste des quintuplés ouverts ou fermés
}
