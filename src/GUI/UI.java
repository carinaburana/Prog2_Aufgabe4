package GUI;

import core.Command;

public interface UI {
    public Command getCommand();
    public void render(BoardView board);
    public void message(String msg);
}
