
package core;
@Deprecated
public class GameState {

    public static void main(String[] args) {
        System.out.println("\n###\nControl with WASD, only the first char of your input will be counted, the rest will be deleted\n###\n\n");
        EntitySet container = new EntitySet(15);
        container.push(new Wall(0, new XY(3,3)));
        container.push(new GoodBeast(1, new XY(4,3)));
        container.push(new GoodPlant(2, new XY(3,3)));
        container.push(new BadPlant(3, new XY(2,7)));
        container.push(new BadBeast(3, new XY(7,2)));
        container.push(new MasterSquirrel(2, new XY(3,4)));
        container.push(new HandOperatedMasterSquirrel(2, new XY(3,4)));


        for (int i = 0; i < 20; i++) {
            System.out.println(container.toString());
            //container.update();
        }


        System.out.println(container.toString());



    }
}
