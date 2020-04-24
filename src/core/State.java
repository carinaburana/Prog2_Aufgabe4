package core;

import core.Board;

public class State {
    private Board board;
    private int HiScore = 0;

    public State(){
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public void update(Command operator){
        board.update(operator);
    }

}
