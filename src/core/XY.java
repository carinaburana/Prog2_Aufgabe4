package core;

import java.util.Random;

public class XY {

    private int x;
    private int y;
    private static Random randy = new Random();

    public XY(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "core.XY{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public XY deconstructVector(XY target){
        return (new XY(target.x - x, target.y - y));
    }

    public XY addRandomVector(){
        XY vector = new XY(randy.nextInt(3) - 1, randy.nextInt(3) - 1);
        return addSpecificVector(vector);
    }

    public XY addSpecificVector(XY vector){
        return new XY(this.x + vector.x, this.y + vector.y);
    }

    public float calcDistance(XY loc){
        int distanceX = Math.abs(loc.getX() - getX());
        int distanceY = Math.abs(loc.getY() - getY());
        float distanceTotal = (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        return distanceTotal;
    }

    public XY calcCourse(XY targetLoc, boolean aggressive){
        int xVec, yVec;
        if (x == targetLoc.getX()){
            xVec = 0;
        } else {
            xVec = (targetLoc.x > x)? 1 : - 1;
        }

        if (y == targetLoc.y){
            yVec = 0;
        } else {
            yVec = (targetLoc.y > y)? 1 : - 1;
        }


        if (!(aggressive)){
            xVec = - xVec;
            yVec = - yVec;
        }

        return new XY(xVec, yVec);

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XY xy = (XY) o;
        return x == xy.x &&
                y == xy.y;
    }

}
