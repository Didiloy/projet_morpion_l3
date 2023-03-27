import common.helper.ReadWriteCsv;
import rungame.PlayGame;
import rungame.SaveAndLoad;
import rungame.TournamentGame;
import rungame.TournamentOwner;
import tournoi.GomokolServer.Server.Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0) {
            switch (args[0]) {
                case "serveur":
                    Server server = null;
                    try {
                        server = new Server();
                        server.open();
                    } catch (IOException e) {
                        System.out.println("Problème avec le serveur");
                        throw new RuntimeException(e);
                    }
                    break;
                case "client":
                    try {
                        new TournamentGame();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "owner":
                    System.out.println("owner");
                    new TournamentOwner();
                    TournamentOwner.main(new String[]{});
                    break;
                default:
                    System.out.println("Argument invalide");
                    break;
            }
        } else {

            ReadWriteCsv r = new ReadWriteCsv("./src/res/drawcomputerplayer.csv");
            try {
                r.readFile();
            } catch (FileNotFoundException e) {
                System.out.println("Impossible de trouver le fichier d'enregistrement des résultats");
                System.exit(0);
            }
            Scanner input = new Scanner(System.in);
            System.out.println("Do you want to load a game ? (y or n)");
            String answer = input.nextLine();
            while (!answer.equals("y") && !answer.equals("n")) {
                System.out.println("You can must answer by y or n");
                answer = input.nextLine();
            }
            PlayGame partie;
            if (answer.equals("y")) {
                System.out.println("Saisssez le path de la partie");
                answer = input.nextLine();
                File file = new File("./saved_game/" + answer);
                input = new Scanner(file);
                partie = SaveAndLoad.load(input);
                input.close();
            } else {
                partie = new PlayGame();
            }

            while ((!partie.checkWin()) && (!partie.isSave())) {
                partie.nextRound();
            }

        /*switch (partie.winner) {
            case 0 -> r.draw += 1;
            case 1 -> r.computer += 1;
            case 2 -> r.player += 1;
        }
        r.writeFile();
        */

        }
    }
}
