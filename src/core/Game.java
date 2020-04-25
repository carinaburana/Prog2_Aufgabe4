package core;


import GUI.ConsoleUI;
import GUI.UI;

import static core.GameCommandType.HELP;
import static core.GameCommandType.MASTER_ENERGY;


public abstract class Game {
    private int ticks = 0;
    private Command processedInput;
    private State state;
    private UI gui;

    public Game(State state, UI gui){
        this.state = state;
        this.gui = gui;
    }


    public void run() {
        while (true) {
            render();
            processInput();
            update();
            ticks++;
        }
    }


    private void render(){
        FlattenedBoard fb = state.getBoard().flatten();
        gui.render(state.getBoard().flatten());

    }

    private void processInput(){
        processedInput = gui.getCommand();
    }

    private void update(){
        if (processedInput == null) {
            return;
        }
            state.update(processedInput);
        }
    }

