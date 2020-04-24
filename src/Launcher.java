import GUI.ConsoleUI;
import core.Game;
import core.GameImpl;
import core.State;

public class Launcher {
    public static void main(String[] args) {
        Game game = new GameImpl(new State(), new ConsoleUI());
        game.run();
    }
}
