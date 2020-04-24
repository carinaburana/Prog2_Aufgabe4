package core;

public class GoodBeast extends Character {

    private int cooldown = 0;
    public final static int BASE_ENERGY = 200;

    public GoodBeast(int iD, XY loc){
        super(iD, BASE_ENERGY, loc);
    }



    public void nextStep(EntityContext context) {
        //context.nearestPlayerEntity(this.getLoc());
        if (cooldown > 0){
            System.out.println("Good Beast tried to move; cooldown: " + cooldown );
            cooldown--;
        } else {
            Squirrel nearest = context.nearestPlayerEntity(getLoc());
            XY nearestPos = nearest.getLoc();
            if (getLoc().calcDistance(nearestPos) > 8.5){
                context.tryMove(this, getLoc().addRandomVector());
                return;
            }

            context.tryMove(this, getLoc().calcCourse(nearestPos, false));
            cooldown = 3;
        }

    }

    @Override
    public String toString() {
        return super.toString() + " of type core.GoodBeast ";
    }
}
