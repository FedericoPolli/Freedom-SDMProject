package dssc.project.freedom;

import java.util.Scanner;

/**
 * Class that controls the execution of the game.
 */
public class Main {

    /**
     * Main of the project.
     */
    public static void main(String[] args) {
        System.out.println("Game Start:");
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the board size (minimum 4):");
        int boardSize = getBoardSize(in);
        char player1 = getTypeOfPlayer(in);
        String name1;
        if (player1 == 'h')
            name1 = getHumanPlayerName(in);
        else
            name1 = "Computer Player1";
        char player2 = getTypeOfPlayer(in);
        String name2;
        if (player2 == 'h')
            name2 = getHumanPlayerName(in);
        else
            name2 = "Computer Player2";
        CommandLineGame clGame = new CommandLineGame(boardSize, player1, name1, player2, name2);
        clGame.play();
        in.close();
    }

    private static int getBoardSize(Scanner in) {
        int boardSize;
        boolean flag;
        do {
            flag = false;
            boardSize = Utility.getInteger(in);
            if (boardSize < 4) {
                flag = true;
                System.out.println("The size of the board must be an integer >= 4");
            }
        } while (flag);
        return boardSize;
    }

    private static char getTypeOfPlayer(Scanner in){
        System.out.println("Choose the player: h for a Human Player, r for a Random Player or g for a Greedy Player");
        boolean flag;
        String player;
        do{
            player = in.next();
            in.nextLine(); //throw away the \n not consumed by nextInt()
            flag = false;
            switch (player){
                case "h" : return 'h';
                case "r" : return 'r';
                case "g" : return  'g';
                default : flag = true;
            }
        } while(flag);
        return 'h';
    }

    private static String getHumanPlayerName(Scanner in){
        System.out.println("Enter the name of Human Player:");
        return in.nextLine();
    }
}
