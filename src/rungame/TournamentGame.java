package rungame;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import Joueur.TournamentPlayer;
import common.enums.StateEnum;
import grille.Coordinates;
import grille.Grille;
import tournoi.GomokolClient.Game;
import tournoi.GomokolClient.GamesManager;
import tournoi.GomokolClient.interfaces.Board;
import tournoi.GomokolClient.interfaces.Group;
public class TournamentGame {

    public static int BOARD_SIZE = 15;
    public static int ORDER = 2;
    public static int EMPTY = -1;

    //Plateau de jeu
    public Grille grid = new Grille(BOARD_SIZE, BOARD_SIZE);

    StateEnum croix = StateEnum.CROIX;
    StateEnum rond = StateEnum.ROND;

    //On définit nos joueurs
    private TournamentPlayer player1 = new TournamentPlayer(this.grid, croix);
    private TournamentPlayer player2 = new TournamentPlayer(this.grid, rond);

    //Instance de board
    private Board board = new Board() {

        //Méthode appelée lors de la réception d'un coup joué
        @Override
        public void addStrokeToBoard(int player_id, int[] stroke) {
            if(player_id == player1.getId()) {
                grid.playCase(player1.getSign(), stroke[0], stroke[1]);
                grid.addMove(new Coordinates(stroke[0], stroke[1], player1.getSign()));
            }
            else {
                grid.playCase(player2.getSign(), stroke[0], stroke[1]);
                grid.addMove(new Coordinates(stroke[0], stroke[1], player2.getSign()));
            }


        }
    };

    //Instance de board
    private Group group = new Group(ORDER) {

        //Méthode appelée lors de la création d'une partie dans le groupe inscrit
        @Override
        public void autoGameSubscriber(int game_id) {
            try {
                //On (ré)initialise le plateau de jeu
                grid = new Grille(BOARD_SIZE, BOARD_SIZE);

                //On enregistre la partie
                Game game = GamesManager.MANAGER.registerNewGame(game_id, this.getOrder());

                //On enregistre les fonctions de réception des coups joués
                game.registerNewBoard(board);

                //On enregistre les joueurs
                game.registerNewPlayer(player1);
                game.registerNewPlayer(player2);

            } catch (Exception e) {
                //Gestion des exceptions
                e.printStackTrace();
            }
        }
    };

    public TournamentGame() throws UnknownHostException, IOException, Exception {
        //On définit l'adresse du serveur
        String address = "127.0.0.1";

        //On se connecte au serveur
        GamesManager.MANAGER.connect(address, 8080);

        //Récupération de l'id du groupe
        System.out.print("Entrez l'id du groupe : ");
        int group_id;
        Scanner sc = new Scanner(System.in);
        group_id = sc.nextInt();
        sc.close();

        //On s'inscrit au groupe
        GamesManager.MANAGER.subscribeGroup(group_id, group);
    }


    public static void main(String[] args) {
        try {
            new TournamentGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
