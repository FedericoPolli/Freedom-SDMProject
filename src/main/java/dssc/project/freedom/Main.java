package dssc.project.freedom;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the board size:");
        int boardSize = in.nextInt();
        Game game = new Game(boardSize);
        System.out.println("Enter the position of the stone:");
        int x = in.nextInt();
        int y = in.nextInt();
        Position current = Position.createAt(x, y);
        if (game.isMoveValid(current)) {
            game.play(current, Colour.WHITE);
        }
    }
}
