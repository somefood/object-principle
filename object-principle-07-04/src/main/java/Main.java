import console.Console;
import game.Game;

public class Main {

    public static void main(String[] args) {
        Game game = new Game(new Console());
        game.run();
    }
}
