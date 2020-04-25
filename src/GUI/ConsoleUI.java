package GUI;

import core.Command;
import core.GameCommandType;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static core.GameCommandType.EXIT;
import static core.GameCommandType.HELP;


public class ConsoleUI implements UI{

    //private final PrintStream outputStream = System.out;
    private final BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    private final CommandScanner commandScanner = new CommandScanner(GameCommandType.values(), inputReader);

    @Override
    public Command getCommand() {




        Command command;

        try {
            command = commandScanner.next();
        } catch (ScanException e) {
            e.printStackTrace();
            return null;
        }

        if (command.getCommandType() == HELP){
            System.out.println("help");
            return null;
        } else if (command.getCommandType() == EXIT){
            System.exit(1);
        }



        return command;
    }

    @Override
    public void render(BoardView board) {
        System.out.println(board);
    }

    @Override
    public void message(String msg) {

    }
}
