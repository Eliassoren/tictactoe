package no.me.eliasbrattli.ovinger.tictactoe.animation;

import android.graphics.Paint;

/**
 * Created by EliasBrattli on 10/04/2017.
 */
public class Cross {
    private Line diagPos;
    private Line diagNeg;
    private Paint paint;
    public Cross(Line diagPos, Line diagNeg, Paint paint){
        this.diagPos = diagPos;
        this.diagNeg = diagNeg;
        this.paint = paint;
    }
    public Line getDiagPos() {
        return diagPos;
    }

    public Line getDiagNeg() {
        return diagNeg;
    }

    public Paint getPaint(){
        return paint;
    }

    public void setDiagPos(Line diagPos) {
        this.diagPos = diagPos;
    }

    public void setDiagNeg(Line diagNeg) {
        this.diagNeg = diagNeg;
    }
}
