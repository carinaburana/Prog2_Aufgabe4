package core;

public class MasterSquirrel extends Squirrel {

        public final static int BASE_ENERGY = 1000;
    public MasterSquirrel(int iD, XY loc){
        super(iD, BASE_ENERGY, loc);

        //TESTING PURPOSES, REMOVE BEFORE FINALIZING
    }




    public MasterSquirrel(int iD, XY loc, int specificEnergy){      //my only job is creating MiniSquirrels 
        super(iD, specificEnergy, loc);
    }
    @Override
    public void nextStep(EntityContext context) {

    }


    public MiniSquirrel createMiniSquirrel(int iD){
        if (getSet() == null){
            System.err.println(this + " not currently part of a set!");
        }
        return new MiniSquirrel(iD,getLoc().addSpecificVector(new XY(1,1)),this, 10);
    }


    public boolean isMyServant(Entity e){
        if (e instanceof MiniSquirrel){
            if(((MiniSquirrel) e).getMaster() == this){
                return true;
            }
        }
        return false;
    }

   /* @Override
    public String toString() {
        return super.toString() + " of type core.MasterSquirrel ";
    }*/


    @Override
    public String toString() {
        return super.toString() + " of type " + this.getClass();
    }
}
