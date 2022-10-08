package Grille;

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

    public Grille(int x, int y){
        this.x = x;
        this.y = y;
        _tabCases = new Case[x < X_MINIMUM  || x > X_MAXIMUM ? X_DEFAULT : x + 10]
                            [y < Y_MINIMUM  || y > Y_MAXIMUM ? Y_DEFAULT : y + 10];
        initTab();
    }

    /**
     * Initialise the array of Case by creating the case and setting the extern one to false.
     * @Returns: void
     */
    private void initTab(){
        for (int i = 0; i < _tabCases.length; i++) {
            for (int j = 0; j < _tabCases[i].length; j++) {
                _tabCases[i][j] = new Case();
                if(i < 5 || j < 5 || i > this.x + 4|| j > this.y + 4 ) _tabCases[i][j].setJouable(false);
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
     * Print the grid with all the cases visibles, even the non playable one.
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
    }

    //TODO
    //Ajouter un pion
    //supprimer un pion
    // calculer les valeurs des cases
    // liste des quintuplés ouverts ou fermés
}
