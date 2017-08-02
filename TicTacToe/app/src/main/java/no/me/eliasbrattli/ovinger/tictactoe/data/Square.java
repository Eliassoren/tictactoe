package no.me.eliasbrattli.ovinger.tictactoe.data;

import android.graphics.Rect;

import no.me.eliasbrattli.ovinger.tictactoe.animation.Circle;
import no.me.eliasbrattli.ovinger.tictactoe.animation.Cross;
import no.me.eliasbrattli.ovinger.tictactoe.util.Check;

/**
 * Created by EliasBrattli on 10/04/2017.
 * Represents each square in the 3*3 board.
 */
public class Square {
    private Check check;
    private Rect rect;
    private Circle circle;
    private Cross cross;
    private int id;
    private int[] position;
    public Square(Check check,Rect rect,Circle circle,Cross cross,int id,int[]position) {
        this.check = check;
        this.rect = rect;
        this.circle = circle;
        this.cross = cross;
        this.id = id;
        this.position = position;
    }

    public Check getCheck(){
        return check;
    }

    public boolean hasRect(Rect rect){
        return this.rect.equals(rect);
    }
    public Rect getRect(){
       return rect;
    }
    public void setCheck(Check c){
        check = c;
    }
    public int[]getPosition(){
        return position;
    }
    public int getXPosition(){
        return position[0];
    }
    public int getYPosition(){
        return position[1];
    }
    public int getId(){
        return id;
    }
    public boolean contains(int x, int y){
        return rect.contains(x,y);
    }
    public Circle getCircle() {
        return circle;
    }
    public Cross getCross() {
        return cross;
    }
}
