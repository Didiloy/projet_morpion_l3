package grille;

import common.enums.StateEnum;

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

    public Grille(int x, int y) {
        this.x = x;
        this.y = y;
        _tabCases = new Case[x < X_MINIMUM || x > X_MAXIMUM ? X_DEFAULT
                : x + 10][y < Y_MINIMUM || y > Y_MAXIMUM ? Y_DEFAULT : y + 10];
        initTab();
        initQuint();
        setInitialValue();
    }

    /**
     * Initialise the array of Case by creating the case and setting the extern one
     * to false.
     *
     * @Returns: void
     */
    private void initTab() {
        for (int i = 0; i < _tabCases.length; i++) {
            for (int j = 0; j < _tabCases[i].length; j++) {
                _tabCases[i][j] = new Case(i, j);
                if (i < 5 || j < 5 || i > this.x + 4 || j > this.y + 4)
                    _tabCases[i][j].setJouable(false);
            }
        }
    }

    /**
     * Initialise the Quintuplet arraylist by creating all the quintuplet of the
     * grid
     */
    private void initQuint() {
        for (int i = 0; i < _tabCases.length; i++) {
            for (int j = 0; j < _tabCases[i].length; j++) {
                // Si on peut aligner 5 case horizontalement sans sortir de la grille on ajoute
                // le quintuplet
                if (j + 4 < _tabCases[i].length) {
                    ArrayList<Case> l = new ArrayList<>();
                    for (int k = j; k < j + 5; k++) {
                        l.add(_tabCases[i][k]);
                    }
                    this._lQuint.add(new Quintuplet(l));
                }
                // Si on peut aligner 5 case verticalement sans sortir de la grille on ajoute le
                // quintuplet
                if (i + 4 < _tabCases.length) {
                    ArrayList<Case> l = new ArrayList<>();
                    for (int k = i; k < i + 5; k++) {
                        l.add(_tabCases[k][j]);
                    }
                    this._lQuint.add(new Quintuplet(l));
                }
                // Si on peut aligner 5 cases dans la diagonale descendante vers la droite
                if ((j + 4 < _tabCases[i].length) && (i + 4 < _tabCases.length)) {
                    ArrayList<Case> l = new ArrayList<>();
                    for (int k = 0; k < 5; k++) {
                        l.add(_tabCases[i + k][j + k]);
                    }
                    this._lQuint.add(new Quintuplet(l));
                }

                // Si on peut aligner 5 cases dans la diagonale montante vers la droite
                if ((j + 4 < _tabCases[i].length) && (i > 4)) { // i doit etre plus grand que 8 ne pas qu'on remonte
                    // plus haut que la case 5 qui est le début des cases
                    // visibles
                    ArrayList<Case> l = new ArrayList<>();
                    for (int k = 0; k < 5; k++) {
                        l.add(_tabCases[i - k][j + k]);
                    }
                    this._lQuint.add(new Quintuplet(l));
                }
            }
        }

    }

    /**
     * Update the value of each Case in the grid.
     *
     * @param s determine which pawn weighs the most.
     */
    public void updateValue(StateEnum s) {
        // update les valeurs de tout les quintuplets
        this._lQuint.forEach(Quintuplet::updateEtat);
        this._lQuint.forEach(q -> q.updateValeur(s));

        // assigner a chaque case la somme des valeurs des quintuplets qui la traverse
        this._lQuint.forEach(q -> q.get_lCases().forEach(c -> {
            if (c.getJouable())
                c.setValeur(c.getValeur() + q.getValeur());
        }));
    }

    /**
     * Play a pawn in a Case. (change the state of the Case).
     * Make sure that the x and y take into account the non-playable cells
     *
     * @param s the state to play
     * @param x the x coordinate of the cell to play
     * @param y the y coordinate of the cell to play
     */
    public void playCase(StateEnum s, int x, int y) {
        if (s == StateEnum.VIDE)
            return;
        if (this._tabCases[x][y].getState() != StateEnum.VIDE)
            return;
        this._tabCases[x][y].setState(s);
    }


    /**
     * Set the iniatial value of each Case of the grid
     */
    public void setInitialValue() {
        // Ajouter la valeur de base des carreaux dans la grille vide.
        // la valeur d'un carreau est augmentée de 1 pour chaque quintuplet dans lequel
        // il est compris.
        this._lQuint.forEach(q -> q.get_lCases().forEach(c -> {
            if (c.getJouable())
                c.setValeur(c.getValeur() + 1);
        }));
    }

    /**
     * Print the grid with only the playable grid visible.
     *
     * @Returns: void
     */
    public void print() {
        //Print les absisses
        System.out.print("  ");
        for (int i = 0; i < _tabCases.length * 2 + 1; i++) {
            if (i < 5 || i > this.x + 4)
                continue;
            System.out.print(" " + (i - 4) );
        }
        System.out.println();
        System.out.print("  "); //3 espaces
        for (int i = 0; i < _tabCases.length * 2 + 1; i++) {
            if (i < 5 || i > this.x + 4)
                continue;
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < _tabCases.length; i++) {
            if (i < 5 || i > this.x + 4)
                continue;
            System.out.print((i - 4) + " ");
            System.out.print("|");
            for (int j = 0; j < _tabCases[i].length; j++) {
                if (j < 5 || j > this.y + 4)
                    continue;
                System.out.print(_tabCases[i][j] + "|");
            }
            System.out.println();
        }

        System.out.print("  "); // 3 espaces
        for (int i = 0; i < _tabCases.length * 2 + 1; i++) {
            if (i < 5 || i > this.x + 4)
                continue;
            System.out.print("-");
        }
    }

    /**
     * Print the grid with only the playable grid visible and with the values of the
     * case instead of the state.
     *
     * @Returns: void
     */
    public void printCasesValues() {
        //print le séparateur du haut de la grille
        for (int i = 0; i < _tabCases.length * 2 + 1; i++) {
            if (i < 5 || i > this.x + 4)
                continue;
            System.out.print("-");
        }
        System.out.println();

        for (int i = 0; i < _tabCases.length; i++) {
            if (i < 5 || i > this.x + 4)
                continue;
            System.out.print("|");
            for (int j = 0; j < _tabCases[i].length; j++) {
                if (j < 5 || j > this.y + 4)
                    continue;
                System.out.print(_tabCases[i][j].getValeur() + "|");
            }
            System.out.println();
        }
        for (int i = 0; i < _tabCases.length * 2 + 1; i++) {
            if (i < 5 || i > this.x + 4)
                continue;
            System.out.print("-");
        }
    }

    /**
     * Print the list of Quintuplet
     */
    public void printQuint() {
        this._lQuint.forEach(l -> {
            System.out.print("[");
            l.get_lCases().forEach(c -> System.out.print("(" + c.x + "," + c.y + ")"));
            System.out.println("]");
        });
    }

    /**
     * Print the grid with all the cases visibles, even the non-playable one.
     *
     * @Returns: void
     */
    public void printAll() {
        for (int i = 0; i < _tabCases.length * 2 + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < _tabCases.length; i++) {
            System.out.print("|");
            for (int j = 0; j < _tabCases[i].length; j++) {
                System.out.print(_tabCases[i][j] + "|");
            }
            System.out.println();
        }
        for (int i = 0; i < _tabCases.length * 2 + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    // TODO
    // supprimer un pion

    // Retourne le tableau de case pour que les Computer sachent ou jouer.
    public Case[][] getTabCases() {
        return this._tabCases;
    }

    /**
     *
     * @return an array containing in this order the x and y coordinates of the maximum value
     */
    public int[] getMaxValue(){
        int xMax = 0;
        int yMax = 0;
        int maxValue = 0;
        for (int x = 0; x < _tabCases.length; x++) {
            for (int y = 0; y < _tabCases[x].length; y++) {
                if (_tabCases[x][y].getValeur() >= maxValue && _tabCases[x][y].getJouable()) {
                    xMax = x;
                    yMax = y;
                    maxValue = _tabCases[x][y].getValeur();
                }
            }
        }
        return new int []{xMax, yMax};
    }
}
