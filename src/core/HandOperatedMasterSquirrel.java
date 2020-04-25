package core;

import java.io.IOException;

import static core.GameCommandType.EXIT;

public class HandOperatedMasterSquirrel extends MasterSquirrel {




    public HandOperatedMasterSquirrel(int iD, XY loc){
        super(iD, loc);
    }



    public void nextStep(Command operator, EntityContext context) throws NotEnoughEnergyException{
        {

            if (concussionTimer != 0){
                concussionTimer--;
                System.out.println("Squirrel will be unable to move for " + concussionTimer + " more ticks");
                return;
            }

            if(getSet() == null){
                System.err.println("core.Entity not currently part of a set");
                return;
            }


            XY targetLoc;

            switch((GameCommandType)operator.getCommandType()) {
                case UP:
                    targetLoc = getLoc().addSpecificVector(new XY(0, -1));
                    break;
                case LEFT:
                    targetLoc = getLoc().addSpecificVector(new XY(-1, 0));
                    break;
                case DOWN:
                    targetLoc = getLoc().addSpecificVector(new XY(0, 1));
                    break;
                case RIGHT:
                    targetLoc = getLoc().addSpecificVector(new XY(1, 0));
                    break;
                case SPAWN_MINI:
                    if ((Integer) operator.getParams()[0] >= getEnergy()){
                        throw new NotEnoughEnergyException();
                }
                    getSet().push(new MiniSquirrel(getSet().getH(), getLoc().addRandomVector(), this, (Integer) operator.getParams()[0]));
                case MASTER_ENERGY:
                    System.out.println(getEnergy());
                default:
                    return;
            }

            //this.updateEnergy(this.getSet().tryToMove(this, targetLoc));

            context.tryMove(this, targetLoc);

            return;

        }
    }
}
