package no.me.eliasbrattli.ovinger.tictactoe.animation;

/**
 * Created by EliasBrattli on 23/11/2016.
 */
public class Line {
    private float startX;
    private float startY;
    private float stopX;
    private float stopY;
    private Circle circle;
    public Line(float startX, float startY, float stopX, float stopY){
        this.startX = startX;
        this.startY = startY;
        this.stopX = stopX;
        this.stopY = stopY;
        circle = null;
    }
    // Convenient for storing circle in same list
    public Line(Circle circle){
        this.circle = circle;
    }
    public float getStartX() {
        return startX;
    }
    public float getStartY() {
        return startY;
    }
    public float getStopX() {
        return stopX;
    }
    public float getStopY() {
        return stopY;
    }

    public Circle getCircle() {
        return circle;
    }
}
