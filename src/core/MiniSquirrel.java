package core;

public class MiniSquirrel extends Squirrel {


    private MasterSquirrel master;

    public MiniSquirrel(int iD, XY loc, MasterSquirrel master, int energy){
        super(iD,  energy, loc);
        this.master = master;
    }

    public MasterSquirrel getMaster() {
        return master;
    }


    @Override
    public void nextStep(EntityContext context) {
        updateEnergy(-1);
    }
}
