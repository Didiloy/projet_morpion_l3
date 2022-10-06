package Grille;

public class Grille {
    private final int X_MINIMUM = 5;
    private final int Y_MINIMUM = 5;
    private final int X_DEFAULT = 10;
    private final int Y_DEFAULT = 10;
    private final int X_MAXIMUM = 100;
    private final int Y_MAXIMUM = 100;

    private Case[][] _tabCases;

    public Grille(int x, int y){
        _tabCases = new Case[x < X_MINIMUM  || x > X_MAXIMUM ? X_DEFAULT : x]
                            [y < Y_MINIMUM  || y > Y_MAXIMUM ? Y_DEFAULT : y];
        initTab();
    }

    private void initTab(){
        for (int i = 0; i < _tabCases.length; i++) {
            for (int j = 0; j < _tabCases[i].length; j++) {
                _tabCases[i][j] = new Case();
            }
        }
    }

    public void print(){
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
    }

    //TODO
    //Ajouter un pion
    //supprimer un pion
    // calculer les valeurs des cases
    // liste des quintuplés ouverts ou fermés
}
