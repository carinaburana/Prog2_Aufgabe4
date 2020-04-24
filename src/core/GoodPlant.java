package core;

public class GoodPlant extends Entity {

    public final static int BASE_ENERGY = 100;

    public GoodPlant(int iD, XY loc){
        super(iD, BASE_ENERGY, loc);
    }


    public void nextStep(EntityContext context) {

    }


    @Override
    public String toString() {
        return super.toString() + " of type core.GoodPlant ";
    }
}
