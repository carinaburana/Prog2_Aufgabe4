package core;

import java.util.Random;

public class FlattenedBoard implements GUI.BoardView, EntityContext {
    Board board;
    Entity[][] actualBoard;
    Random r = new Random();

    public FlattenedBoard(Board board, Entity[][] actualBoard){
        this.board = board;
        this.actualBoard = actualBoard;
    }


    @Override
    public String toString() {
        StringBuilder returnMe = new StringBuilder();
        for (int i = 0; i < board.width; i++) {
            returnMe.append(i);
            returnMe.append("\t");
        }
        returnMe.append("\n");
        for (int i = 0; i < board.height; i++) {
            for (int j = 0; j < board.width; j++) {

                if ((actualBoard[j][i]) == null) {
                    returnMe.append(" ");
                } else if (actualBoard[j][i] instanceof Wall) {
                    returnMe.append("W");
                } else if (actualBoard[j][i] instanceof HandOperatedMasterSquirrel) {
                    returnMe.append("O");
                } else if (actualBoard[j][i] instanceof BadPlant) {
                    returnMe.append("p");
                }else if (actualBoard[j][i] instanceof GoodPlant) {
                    returnMe.append("P");
                }else if (actualBoard[j][i] instanceof BadBeast) {
                    returnMe.append("b");
                }else if (actualBoard[j][i] instanceof GoodBeast) {
                    returnMe.append("B");
                }else if (actualBoard[j][i] instanceof MasterSquirrel) {
                    returnMe.append("Ö");
                }else if (actualBoard[j][i] instanceof MiniSquirrel) {
                    returnMe.append("m");
                }else{
                    returnMe.append("X");
                }
                returnMe.append("\t");



            }
            returnMe.append("\n");
        }
        return returnMe.toString();
    }

    @Override
    public Class<? extends Entity> getEntityType(int x, int y) {
        return actualBoard[x][y].getClass();
    }

    @Override
    public XY getSize() {
        return null;
    }

    @Override
    public void tryMove(MasterSquirrel master, XY moveDirection) {

        Entity targetEntity = actualBoard[moveDirection.getX()][moveDirection.getY()];
        if (targetEntity == null){
            master.moveToLoc(moveDirection);
            return;
        }

        if (targetEntity.getClass() == GoodPlant.class || targetEntity.getClass() == BadPlant.class || targetEntity.getClass() == GoodBeast.class) {
            //delete(container[i]);
            System.out.println("The " + targetEntity.getClass().getName() + " on " + moveDirection + " has been consumed!");
            //i--;
            targetEntity.moveToLoc(new XY(r.nextInt(board.width), r.nextInt(board.height)));
            master.moveToLoc(moveDirection);



        } else if (targetEntity.getClass() == Wall.class) {
            ((Squirrel) master).setConcussionTimer();
            return;
        } else if (targetEntity instanceof MiniSquirrel) {
            if (!(((MiniSquirrel) targetEntity).getMaster().equals(master))){
                board.set.delete(targetEntity);
                master.updateEnergy(150);
                return;
            }

        }
    }

    @Override
    public void tryMove(GoodBeast goodBeast, XY moveDirection) {

        //relative Position!

        XY targetPos = goodBeast.getLoc().addSpecificVector(moveDirection);
        Entity targetEntity = actualBoard[targetPos.getX()][targetPos.getY()];
        //XY vector = targetEntity.getLoc().deconstructVector(moveDirection);


        if (targetEntity == null){
            System.out.println("A good beast has walked to " + targetPos);
            goodBeast.moveToLoc(targetPos);
            return;
        }


        if (targetEntity.getClass() == Wall.class){
            System.out.println("BONK, a good Beast has walked into a wall");
            if ((targetPos.getX() == 0 && targetPos.getY() == 0) || (targetPos.getX() == 0 && targetPos.getY() == actualBoard[0].length - 1) || (targetPos.getX() == actualBoard.length - 1 && targetPos.getY() == 0) || (targetPos.getX() == actualBoard.length - 1 && targetPos.getY() == actualBoard[0].length - 1)){
                return;
            } else if (targetPos.getX() == 0 || targetPos.getX() == actualBoard[0].length - 1){
                tryMove(goodBeast, new XY(0, moveDirection.getY()));
            } else if (targetPos.getY() == 0 || targetPos.getY() == actualBoard[0].length - 1){
                tryMove(goodBeast, new XY(moveDirection.getX(), 0));
            }
        }
        return;
    }

    @Override
    public void tryMove(BadBeast badBeast, XY moveDirection) {

        //relative Position!


        XY targetPos = badBeast.getLoc().addSpecificVector(moveDirection);
        Entity targetEntity = actualBoard[targetPos.getX()][targetPos.getY()];


        if (targetEntity == null){
            System.out.println("A bad beast has walked to " + moveDirection);
            badBeast.moveToLoc(targetPos);
            return;
        }

        if (targetEntity.getClass() == Wall.class){
            System.out.println("BONK, a bad Beast has walked into a wall");
            return;
        } else if (targetEntity instanceof MasterSquirrel){ //placeholder
            if (badBeast.bite()){
                board.set.delete(badBeast);
            }
            System.out.println(targetEntity + " got bitten by " + badBeast);
        } else if (targetEntity instanceof MiniSquirrel){
            System.out.println("A Bad Beast stares at a Mini Squirrel");
            return;
        }

    }

    @Override
    public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {

        Entity targetEntity = actualBoard[moveDirection.getX()][moveDirection.getY()];

        if (targetEntity == null){
            System.out.println("A Mini Squirrel has walked to " + moveDirection);
            miniSquirrel.moveToLoc(moveDirection);
            return;
        } else if(targetEntity instanceof BadBeast){
            targetEntity.updateEnergy(miniSquirrel.getEnergy());
            board.set.delete(miniSquirrel);
            return;
        } else if(targetEntity instanceof MiniSquirrel){
            if (!((MiniSquirrel) targetEntity).getMaster().equals(miniSquirrel.getMaster())){
                board.set.delete(targetEntity);
                board.set.delete(miniSquirrel);
            }
        } else if(targetEntity instanceof MasterSquirrel){
            if (miniSquirrel.getMaster().equals(targetEntity)){
                targetEntity.updateEnergy(miniSquirrel.getEnergy());
                board.set.delete(miniSquirrel);
            } else {
                board.set.delete(miniSquirrel);
            }
        }

    }

    @Override
    public Squirrel nearestPlayerEntity(XY pos) {
        float currentDistance = Float.MAX_VALUE;
        EntitySet squirrels = board.set.getSquirrels();
        Squirrel returnMe = null;
        while(!(squirrels.isEmpty())){
            Squirrel squirrel = (Squirrel) squirrels.popRandom();

            //System.err.println(squirrel);

            int distanceX = Math.abs(pos.getX() - squirrel.getLoc().getX());
            int distanceY = Math.abs(pos.getY() - squirrel.getLoc().getY());
            float distanceTotal = (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY);
            if(distanceTotal < currentDistance){
                currentDistance = distanceTotal;
                returnMe = squirrel;
            }
        }
        assert returnMe != null;
        //System.out.println("The closest Squirrel is on " + returnMe.getLoc());
        return returnMe;
    }

    @Override
    public void kill(Entity entity) {

    }

    @Override
    public void killAndReplace(Entity entity) {

    }

    @Override
    public Class<? extends Entity> getEntityType(XY xy) {
        return null;
    }
}
