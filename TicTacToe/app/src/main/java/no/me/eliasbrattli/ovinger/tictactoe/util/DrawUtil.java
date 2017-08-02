package no.me.eliasbrattli.ovinger.tictactoe.util;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import no.me.eliasbrattli.ovinger.tictactoe.animation.Circle;
import no.me.eliasbrattli.ovinger.tictactoe.animation.Cross;
import no.me.eliasbrattli.ovinger.tictactoe.animation.Line;
import no.me.eliasbrattli.ovinger.tictactoe.data.Square;

/**
 * Created by EliasBrattli on 19/04/2017.
 */
public final class DrawUtil {
    private final static String TAG = "DrawUtil";
    private DrawUtil(){

    }
    public static void drawCross(Square s, Canvas c){
        Cross cross = s.getCross();
        Line line = cross.getDiagPos();
        c.drawLine(line.getStartX(),line.getStartY(), line.getStopX(),line.getStopY(),cross.getPaint());
        line = cross.getDiagNeg();
        c.drawLine(line.getStartX(),line.getStartY(), line.getStopX(),line.getStopY(),cross.getPaint());

    }
    public static void drawCircle(Square s, Canvas c){
        Circle circle = s.getCircle();
        Circle maskCircle = circle.getMaskCircle();
        c.drawCircle(circle.getCx(),circle.getCy(),circle.getRad(),circle.getPaint());
        c.drawCircle(maskCircle.getCx(),maskCircle.getCy(),maskCircle.getRad(),maskCircle.getPaint());
    }
    public static void drawBoard(Canvas canvas, Paint paint, Square[][]squareMatrix){
        try {
            for(Square[]squares:squareMatrix) {
                for (Square s : squares) {
                    canvas.drawRect(s.getRect(), paint);
                }
            }
        }catch(Exception e){
            Log.e(TAG,e.getMessage());
        }
    }
    public static void drawChecks(Canvas c,Square[][] squareMatrix){
        for(Square[]squares :squareMatrix) {
            for (Square s : squares) {
                if (s.getCheck() == Check.O) {
                    drawCircle(s, c);
                } else if (s.getCheck() == Check.X) {
                    drawCross(s, c);
                }
            }
        }

    }
}
