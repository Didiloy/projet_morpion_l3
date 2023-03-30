package rungame;

import common.enums.StateEnum;
import grille.Coordinates;

import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class SaveAndLoad {
    public static void save(PlayGame game, String saveName) throws FileNotFoundException {
        File save = new File( "./saved_game/" + saveName);
        PrintStream out = new PrintStream(save);

        //Save game between computer
        if(game.getComputerVsComputer()){

            out.println("1"); //"1" for true, the game is ComputerVsComputer

            out.println(game.getSkipSleep() ? "1" : "0"); //Skip The sleep time before play ? "1" Yes, "0" No.

        }else{
            out.println("0");//"0" for false, the game isn't ComputerVsComputer

            out.println((game.getRealPlayer().getSign() == StateEnum.ROND ? "0" : "1") +  " " + (game.getRealPlayer().isSuperUser() ? "1" : "0")); //Print the sign of the human player, and "0" if is not superUser, or "1" if he is.
        }

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

    public static void saveBinary(PlayGame game, String saveName) throws IOException {
        FileOutputStream fout=new FileOutputStream("./saved_game/" + saveName);
        DataOutputStream dout=new DataOutputStream(fout);

        //Save game between computer
        if(game.getComputerVsComputer()){

            dout.writeInt(1); //"1" for true, the game is ComputerVsComputer

            dout.writeInt(game.getSkipSleep() ? 1 : 0); //Skip The sleep time before play ? "1" Yes, "0" No.

        }else{
            dout.writeInt(0);//"0" for false, the game isn't ComputerVsComputer

            dout.writeInt((game.getRealPlayer().getSign() == StateEnum.ROND ? 0 : 1) );
            dout.writeInt(game.getRealPlayer().isSuperUser() ? 1 : 0); //Print the sign of the human player, and "0" if is not superUser, or "1" if he is.
        }

        dout.writeInt(game.getWhoFirst()); //The number generate to select the first player

        dout.writeInt(game.getGrid().getX()); //Number of case for X
        dout.writeInt(game.getGrid().getY()); //Number of case for Y

        Iterator iteMove = game.getGrid().movesList.iterator();

        while(iteMove.hasNext()){
            Coordinates actualMove = (Coordinates) iteMove.next();
            dout.writeInt(actualMove.getX());
            dout.writeInt( actualMove.getY()); //Print X and Y coordinate
            dout.writeInt(actualMove.getSign() == StateEnum.ROND ? 0 : 1); // Print "0" for State ROND and "1" for State CROIX
        }
    }

    public static PlayGame load(String nom) throws FileNotFoundException {
        File file = new File("./saved_game/" + nom);
        Scanner output = new Scanner(file);
        int pcVsPc = output.nextInt();
        int signPlayer = 1;
        int superUser = 0;
        int skipTime = 0;
        if (pcVsPc == 1){
            skipTime = output.nextInt();
        }else {
            signPlayer = output.nextInt();
            superUser = output.nextInt();
        }

        int firstPlayer = output.nextInt();
        int lengthX = output.nextInt();
        int lengthY = output.nextInt();

        PlayGame  game = new PlayGame(lengthX, lengthY, firstPlayer, signPlayer, skipTime, pcVsPc);
            while (output.hasNextInt()) {
                int x  = output.nextInt();
                int y = output.nextInt();
                int sign = output.nextInt();

                game.getGrid().playCase(sign == 1 ? StateEnum.CROIX : StateEnum.ROND, x, y);
                game.getGrid().addMove( new Coordinates(x, y, sign == 1 ? StateEnum.CROIX : StateEnum.ROND));
            }
        game.getGrid().print();
        return game;
    }
}
