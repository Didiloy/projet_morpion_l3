import rungame.PlayGame;

public class Main {
    public static void main(String[] args) {

        PlayGame partie = new PlayGame();

        while (!partie.checkWin()){
            partie.nextRound();
        }

    }
}
