package no.me.eliasbrattli.ovinger.tictactoe.animation;

import android.graphics.Paint;

/**
 * Created by EliasBrattli on 23/11/2016.
 */
public class Circle {
    private float cx;
    private float cy;
    private float rad;
    private Circle maskCircle;
    private Paint paint;
    public Circle(float cx, float cy, float rad,Paint paint, Circle maskCircle){
        this.cx = cx;
        this.cy = cy;
        this.rad = rad;
        this.paint = paint;
        this.maskCircle = maskCircle;
    }
    public Circle(float cx, float cy, float rad,Paint paint){
        this.cx = cx;
        this.cy = cy;
        this.rad = rad;
        this.paint = paint;
    }
    public float getCx() {
        return cx;
    }

    public float getCy() {
        return cy;
    }

    public float getRad() {
        return rad;
    }
    public Paint getPaint(){
        return paint;
    }
    public Circle getMaskCircle(){
        return maskCircle;
    }
}
