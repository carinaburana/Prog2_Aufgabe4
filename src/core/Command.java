package core;

public class Command {

    private CommandTypeInfo commandType;
    private Object[] params;


    public Object[] getParams() {
        return params;
    }

    public CommandTypeInfo getCommandType() {
        return commandType;
    }

    public Command(CommandTypeInfo commandType, Object[] params){
        this.commandType = commandType;
        this.params = params;
    }
}
