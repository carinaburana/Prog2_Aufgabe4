package core;

public class BadPlant extends Entity {

    public final static int BASE_ENERGY = -100;

    public BadPlant(int iD, XY loc){
        super(iD, BASE_ENERGY, loc);
    }



    public void nextStep(EntityContext context) {

    }

    @Override
    public String toString() {
        return super.toString() + " of type core.BadPlant ";
    }
}
