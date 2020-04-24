package core;

public abstract class Squirrel extends Character {


    int concussionTimer = 0;

    public void setConcussionTimer(){
        concussionTimer += 3;
    }

    public Squirrel(int id, int energy, XY loc){
        super(id,energy,loc);
    }



}
