package core;


import java.util.Random;

public class EntitySet {

    private Entity[] container;
    private int h;
    Random r = new Random();
    FlattenedBoard fb;

    public EntitySet(int slots){
        h = 0;
        container = new Entity[slots];
    }

    public EntitySet(Board board){
        this(board.width * board.height);
    }



    @Deprecated
    public int tryToMove(Entity x, XY targetLoc){
        Board board = null;

        for (int i = 0; i < h; i++) {
            if(container[i].getLoc().equals(targetLoc)) {
                if (x instanceof Squirrel){                                                                             //Squirrel


                    if (container[i].getClass() == GoodPlant.class || container[i].getClass() == BadPlant.class || container[i].getClass() == GoodBeast.class) {
                        //delete(container[i]);
                        System.out.println("The " + container[i].getClass().getName() + " on " + targetLoc + " has been consumed!");
                        //i--;
                        container[i].moveToLoc(new XY(r.nextInt(board.width), r.nextInt(board.height)));
                        x.moveToLoc(targetLoc);

                        if (container[i].getClass() == GoodPlant.class){
                            return GoodPlant.BASE_ENERGY;
                        } else if (container[i].getClass() == BadPlant.class){
                            return BadPlant.BASE_ENERGY;
                        } else if (container[i].getClass() == GoodBeast.class){
                            return GoodBeast.BASE_ENERGY;
                        }






                    } else if (container[i].getClass() == Wall.class) {
                        ((Squirrel) x).setConcussionTimer();
                        return 0;
                    }







                } else if (x instanceof GoodBeast){                                                                      //Good Beast
                    if (container[i].getClass() == Wall.class){
                        System.out.println("BONK, a good Beast has walked into a wall");
                        return 0;
                    } else {
                        System.out.println("A good beast has walked to " + targetLoc);
                        x.moveToLoc(targetLoc);
                        return 0;
                    }






                }  else if (x instanceof BadBeast){
                    if (container[i].getClass() == Wall.class){
                        System.out.println("BONK, a bad Beast has walked into a wall");
                        return 0;
                    } else {
                        System.out.println("A bad beast has walked to " + targetLoc);
                        x.moveToLoc(targetLoc);
                        return 0;
                    }
                }

            }
        }



        x.moveToLoc(targetLoc);
        return 0;
    }


    public int getH(){
        return h;
    }


    public void push(Entity e){

        for (int i = 0; i < h; i++) {
            if (e.equals(container[i])){
                return;
            }
        }

        if (h < container.length){
            container[h++] = e;
            e.setSet(this);
        }

    }

    public EntitySet getSquirrels(){
        int numOfSquirrels = 0;
        for (int i = 0; i < h; i++) {
            if (container[i] instanceof Squirrel){
                numOfSquirrels++;
            }
        }


        EntitySet returnMe = new EntitySet(numOfSquirrels);

            for (int i = 0; i < h; i++) {
                if (container[i] instanceof Squirrel){
                   returnMe.push(container[i]);
                   container[i].setSet(this); //temporary fix
                }
            }

            return returnMe;

    }

    public boolean isEmpty(){
        return h == 0;
    }

    public Entity popRandom(){
        return container[--h];
    }

    public void delete(Entity e){
        int i = 0;
        for (; i < h; i++) {
            if (container[i].equals(e)){
                e.setSet(null);
                System.out.println(e + " has been removed from the set!");
                break;
            }
        }
        for (; i < h - 1; i++) {
            container[i] = container[i + 1];
        }
        h--;
    }


    /**
     * @param operator
     * @returns current ID
     */

    public int update(Command operator){
        for (int i = 0; i < h; i++) {
            if (container[i] instanceof Character) {
                if (container[i] instanceof HandOperatedMasterSquirrel) {
                    ((HandOperatedMasterSquirrel) container[i]).nextStep(operator, fb);
                } else ((Character)container[i]).nextStep(fb);
            }
        }
        if ((GameCommandType)operator.getCommandType() == GameCommandType.ALL){
            System.out.println(this);
        }

        return h;

    }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();

        for (int i = 0; i < h; i++) {
            content.append(container[i].toString());
            content.append("\n");
        }
        return content.toString();
    }

    public FlattenedBoard shift(int x, int y, Board board){
        Entity[][] returnMe = new Entity[x][y];
        for (int i = 0; i < h; i++) {
            try {
                returnMe[container[i].getLoc().getX()][container[i].getLoc().getY()] = container[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                System.out.println(i);
                System.out.println(container[i]);
                System.out.println(container[i].getLoc().getX());
                System.out.println(container[i].getLoc().getY());
                System.exit(2);
            }
        }
        fb = new FlattenedBoard(board, returnMe);
        return fb;
    }



}
