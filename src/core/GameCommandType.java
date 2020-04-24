package core;

public enum GameCommandType implements CommandTypeInfo{
    HELP("help","", null),
    EXIT("exit","", null),
    ALL("all","", new Class<?>[]{Integer.class, Integer.class}),
    LEFT("left","",  null),
    UP("up","",  null),
    DOWN("down","",  null),
    RIGHT("right","",  null),
    MASTER_ENERGY("masterenergy","", null),
    SPAWN_MINI("spawnmini","", new Class<?>[]{java.lang.Integer.class}),
    ;
    private final String input;
    private final String description;
    private final Class<?>[] paramTypes;

    GameCommandType(String input, String description, Class<?>[] paramTypes){
        this.description = description;
        this.input = input;
        this.paramTypes = paramTypes;
    }

    @Override
    public String getName() {
        return input;
    }

    @Override
    public String getHelpText() {
        return description;
    }

    @Override
    public Class<?>[] getParamTypes() {
        return paramTypes;
    }
}
