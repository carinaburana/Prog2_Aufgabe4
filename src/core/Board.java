package core;

import java.util.Random;

public class Board {
    final int width;
    final int height;
    final EntitySet set;
    final Random random = new Random();
    int id = 0;

    public Board(){
        width = BoardConfig.WIDTH;
        height = BoardConfig.HEIGHT;
        set = new EntitySet(this);
        for (int i = 0; i < width; i++) {
            set.push(new Wall(id++, new XY(i, 0)));
            set.push(new Wall(id++, new XY(i, height - 1)));
        }
        for (int i = 1; i < height - 1; i++) {
            set.push(new Wall(id++, new XY(0, i)));
            set.push(new Wall(id++, new XY(width - 1, i)));
        }

        for (int i = 0; i < BoardConfig.BAD_BEASTS; i++) {
            set.push(new BadBeast(id++, new XY(1 + random.nextInt(width - 2), 1 + random.nextInt(height - 2))));
        }

        for (int i = 0; i < BoardConfig.GOOD_BEASTS; i++) {
            set.push(new GoodBeast(id++, new XY(1 + random.nextInt(width - 2), 1 + random.nextInt(height - 2))));
        }

        for (int i = 0; i < BoardConfig.GOOD_PLANTS; i++) {
            set.push(new GoodPlant(id++,new XY(1 + random.nextInt(width - 2), 1 + random.nextInt(height - 2))));
        }

        for (int i = 0; i < BoardConfig.BAD_PLANTS; i++) {
            set.push(new BadPlant(id++, new XY(1 + random.nextInt(width - 2), 1 + random.nextInt(height - 2))));
        }

        for (int i = 0; i < BoardConfig.WALLS; i++) {
            set.push(new Wall(id++, new XY(1 + random.nextInt(width - 2), 1 + random.nextInt(height - 2))));
        }

        for (int i = 0; i < BoardConfig.MASTER_SQUIRRELS; i++) {
            set.push(new MasterSquirrel(id++, new XY(1 + random.nextInt(width - 2), 1 + random.nextInt(height - 2))));
        }

        set.push(new HandOperatedMasterSquirrel(id++, new XY(3,3)));

        /*
        MasterSquirrel tmp = new HandOperatedMasterSquirrel(id++, new XY(3,3));
        set.push(tmp);
        set.push(tmp.createMiniSquirrel(id++));
        set.push(new MiniSquirrel(id++, new XY(5,5),new MasterSquirrel(id++, new XY(0,0)), 15));
         */

    }

    public void update(Command operator){
        id = set.update(operator);
    }


    public FlattenedBoard flatten(){
        //return new FlattenedBoard(this, set.shift(width, height));
        return set.shift(width,height, this);
    }






}
