package rungame;

import common.enums.StateEnum;
import grille.Coordinates;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Iterator;

public class SaveAndLoad {
    public static void save(PlayGame game, String saveName) throws FileNotFoundException {
        File save = new File(saveName);
        PrintStream out = new PrintStream(save);

        //Save game between computer
        if(game.getComputerVsComputer()){

            out.println("1"); //"1" for true, the game is ComputerVsComputer

            out.println(game.getSkipSleep() ? "1" : "0"); //Skip The sleep time before play ? "1" Yes, "0" No.

            out.println(game.getWhoFirst()); //The number generate to select the first player

            out.println(game.getGrid().getX()); //Number of case for X
            out.println(game.getGrid().getY()); //Number of case for Y

            Iterator iteMove = game.getGrid().movesList.iterator();

            while(iteMove.hasNext()){
                Coordinates actualMove = (Coordinates) iteMove.next();
                out.print(actualMove.getX() + " " + actualMove.getY() + " "); //Print X and Y coordinate
                out.print(actualMove.getSign() == StateEnum.ROND ? "0" : "1"); // Print "0" for State ROND and "1" for State CROIX
                out.println();
            }
        }else{
            out.println("0");//"0" for false, the game isn't ComputerVsComputer

            out.println((game.getRealPlayer().getSign() == StateEnum.ROND ? "0" : "1") +  " " + (game.getRealPlayer().isSuperUser() ? "1" : "0")); //Print the sign of the human player, and "0" if is not superUser, or "1" if he is.

            out.println(game.getWhoFirst()); //The number generate to select the first player

            out.println(game.getGrid().getX()); //Number of case for X
            out.println(game.getGrid().getY()); //Number of case for Y

            Iterator iteMove = game.getGrid().movesList.iterator();

            while(iteMove.hasNext()){
                Coordinates actualMove = (Coordinates) iteMove.next();
                out.print(actualMove.getX() + " " + actualMove.getY() + " "); //Print X and Y coordinate
                out.print(actualMove.getSign() == StateEnum.ROND ? "0" : "1"); // Print "0" for State ROND and "1" for State CROIX
                out.println();
            }
        }

    }
}
