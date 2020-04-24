package core;

public class BadBeast extends Character {

    public final static int BASE_ENERGY = -150;
    private int HP = 8;

    public BadBeast(int iD, XY loc){
        super(iD, BASE_ENERGY, loc);
    }

    public boolean bite(){
        return --HP == 0;
    }


    public void nextStep(EntityContext context) {
        Squirrel nearest = context.nearestPlayerEntity(getLoc());
        XY nearestPos = nearest.getLoc();
        if (getLoc().calcDistance(nearestPos) > 8.5){
            context.tryMove(this, getLoc().addRandomVector());
            return;
        }

        context.tryMove(this, getLoc().calcCourse(nearestPos, true));





        //
    }

    @Override
    public String toString() {
        return super.toString() + " of type core.BadBeast ";
    }
}


