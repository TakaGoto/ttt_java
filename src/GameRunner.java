import com.tictactoe.Game;
import com.tictactoe.UserInterface.ConsoleUi;

import java.io.InputStreamReader;
import java.io.Reader;

public class GameRunner{

    public static void main(String[] args) {
        Reader reader = new InputStreamReader(System.in);
        ConsoleUi ui = new ConsoleUi(reader);
        Game game = new Game(ui);
        game.playGame();
    }
}
