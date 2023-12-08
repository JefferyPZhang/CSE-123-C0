import java.util.*;

// This class is the client for Chomp, enabling the playing of the game.
public class Client {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        AbstractStrategyGame game = new Chomp();

        System.out.println(game.instructions() + "\n");

        while (!game.isGameOver()) {
            System.out.println(game);
            System.out.printf("Player %d's turn.\n", game.getNextPlayer());
            game.makeMove(console);
        }
        System.out.println(game);
        int winner = game.getWinner();
        System.out.printf("Player %d wins!\n", winner);
    }
}
