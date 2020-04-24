package core;

public abstract class Character extends Entity {
    public Character(int iD, int energy, XY loc) {
        super(iD, energy, loc);
    }

    public abstract void nextStep(EntityContext context);

}
