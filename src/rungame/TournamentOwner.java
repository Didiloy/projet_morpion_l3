package rungame;
import Joueur.Owner;
import tournoi.GomokolClient.Game;
import tournoi.GomokolClient.GamesManager;
import tournoi.GomokolClient.interfaces.Group;

import java.util.Scanner;

public class TournamentOwner {

    private static int WIDTH = 15, HEIGHT = 15, NB_PLAYER = 2, NB_GAMES=10, ORDER=2;

    //Déclaration du groupe
    private static Group group = new Group(ORDER) {

        //Méthode appelée lors de la création d'une partie liée au groupe
        @Override
        public void autoGameSubscriber(int game_id) {
            try {
                //On enregistre notre partie localement
                Game game = GamesManager.MANAGER.registerNewGame(game_id, ORDER);

                //On instancie l'owner
                Owner owner = new Owner(WIDTH, HEIGHT);

                //On lie l'owner et la partie
                GamesManager.MANAGER.linkOwnerWithGame(owner, game);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public static void main(String[] args) {
        try {
            //Connexion au serveur
            GamesManager.MANAGER.connect("127.0.0.1", 8080);
            System.out.println("connecté au serveur");

            //Création d'un groupe de jeu
            int group_id = GamesManager.MANAGER.initGroup(group, NB_PLAYER, NB_GAMES);
            System.out.println("groupe id= " + group_id);

            //On affiche l'id du group pour le transmettre aux joueurs
            System.out.println("Le groupe est créé avec l'id : " + group_id);

            //On bloque notre programme pour laisser les joueurs se connecter
            System.out.println("Appuyez sur Entrée pour lancer les parties.");
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
            sc.close();

            //On créer notre première partie
            Owner owner = new Owner(WIDTH, HEIGHT);
            GamesManager.MANAGER.initNewGame(owner, group_id, ORDER);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
