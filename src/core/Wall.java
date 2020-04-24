package core;

public class Wall extends Entity {

    public final static int BASE_ENERGY = -10;


    public Wall(int iD, XY loc){
        super(iD, BASE_ENERGY, loc);
    }


    public void nextStep(EntityContext context) {

    }

    @Override
    public String toString() {
        return super.toString() + " of type core.Wall ";
    }

    /* @Override
    public boolean equals(Object obj) {
        if (obj instanceof core.Wall && ((core.Wall) obj).getiD() == this.getiD()){
            return true;
        }
        return false;
    }*/
}
