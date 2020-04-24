package core;

public abstract class Entity {
    private int iD;
    private int energy;
    private XY loc;
    private EntitySet set = null;

    public void setSet(EntitySet set) {
        if (set == null){
            System.err.println(this + " has been removed from the set!");
        }
        this.set = set;
    }

    public EntitySet getSet() {
        return set;
    }

    public Entity(int iD, int energy, XY loc){
        this.iD = iD;
        this.energy = energy;
        this.loc = loc;
    }

    public Entity(int iD, int energy, int x, int y){
        this(iD, energy, new XY(x,y));
    }

    public void moveToLoc(XY loc){
        this.loc = loc;
    }



    public void updateEnergy(int deltaEnergy){
        this.energy += deltaEnergy;
    }

    public int getEnergy() {
        return energy;
    }

    public int getiD() {
        return iD;
    }

    public XY getLoc() {
        return loc;
    }

    @Override
    public String toString() {
        return "core.Entity{" +
                "iD=" + iD +
                ", energy=" + energy +
                ", loc=" + loc +
                '}';
    }

    //public abstract void nextStep(EntityContext context);

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Entity) {
            if (obj.getClass() == this.getClass() && ((Entity) obj).getiD() == this.getiD()) {
                return true;
            }
        }
        return false;
    }

}
