import common.helper.ReadWriteCsv;
import rungame.PlayGame;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

//        for (int i = 0; i < 2; i++) {
        ReadWriteCsv r = new ReadWriteCsv("./src/res/drawcomputerplayer.csv");
        try {
            r.readFile();
        } catch (FileNotFoundException e) {
            System.out.println("Impossible de trouver le fichier d'enregistrement des résultats");
            System.exit(0);
        }

        PlayGame partie = new PlayGame();

        while (!partie.checkWin()) {
            partie.nextRound();
        }

        switch (partie.winner) {
            case 0:
                r.draw += 1;
                break;
            case 1:
                r.computer += 1;
                break;
            case 2:
                r.player += 1;
                break;
        }
        r.writeFile();
//        }

    }
}
