package no.me.eliasbrattli.ovinger.tictactoe.util;

import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import no.me.eliasbrattli.ovinger.tictactoe.animation.Circle;
import no.me.eliasbrattli.ovinger.tictactoe.animation.Cross;
import no.me.eliasbrattli.ovinger.tictactoe.animation.Line;
import no.me.eliasbrattli.ovinger.tictactoe.data.Square;

/**
 * Created by EliasBrattli on 19/04/2017
 * Utility class for TicTacToe game logic
 */
public final class GameUtil {
    private static final String TAG = "GameUtil";

    /**
     * Private constructor to avoid instances of class
     */
    private GameUtil(){

    }
    public static Square[][] initDrawComponents(int boardSide, int squareSize, Paint squarePaint, Paint checkPaint){
        Square[][] squareMatrix = new Square[boardSide][boardSide];
        int id = 0;
        for (int i = 0; i < boardSide; i++) {
            for (int j = 0; j < boardSide; j++) {
                int left = i*(squareSize+5);
                int top = j*(squareSize+5);
                int right = left+squareSize;
                int bottom = top+squareSize;
                Circle innerCircle = new Circle(left+squareSize/2,top+squareSize/2,squareSize/2-8,squarePaint);
                Circle circle = new Circle(left+squareSize/2,top+squareSize/2,squareSize/2,checkPaint,innerCircle);
                Cross cross = new Cross(new Line(left,bottom,right,top),new Line(left,top,right,bottom),checkPaint);
                Rect rect = new Rect(left,top,right,bottom);
                Square square = new Square(Check.e,rect,circle,cross,id++,new int[]{i,j});
                squareMatrix[i][j] = square;
            }
        }
        return squareMatrix;
    }

    public static Check gameResult(Square[][] squareMatrix,int max) throws Exception{
        int len = squareMatrix.length;
        Square[] row = new Square[len];
        Square[] col = new Square[len];
        Square[] diagPos = new Square[len];
        Square[] diagNeg = new Square[len];
        if(max > len)max = len;
        int itr = 0;
        int checkedSquares = 0;
        for (int i = 0; i < len; i++) {
            int rowX = 0,colX = 0,diagPosX = 0,diagNegX = 0, rowO = 0, colO = 0, diagPosO = 0, diagNegO = 0;
            for (int j = 0; j < squareMatrix[i].length; j++) {
                row[j] = squareMatrix[j][i];
                col[j] = squareMatrix[i][j];
                diagNeg[j] = squareMatrix[j][j];
                diagPos[j] = squareMatrix[j][squareMatrix[j].length-1-j];
                if(squareMatrix[i][j].getCheck() != Check.e)checkedSquares++;
                itr++;
            }
            for (int k = 0; k < len; k++) {
                if(row[k].getCheck() == Check.X){rowX++;}else {rowX = 0;}
                if(col[k].getCheck() == Check.X){colX++;}else {colX = 0;}
                if(diagPos[k].getCheck() == Check.X){diagPosX++;}else {diagPosX = 0;}
                if(diagNeg[k].getCheck() == Check.X){diagNegX++;}else {diagNegX = 0;}
                if(row[k].getCheck() == Check.O){rowO++;}else {rowO = 0;}
                if(col[k].getCheck() == Check.O){colO++;}else {colO = 0;}
                if(diagPos[k].getCheck() == Check.O){diagPosO++;}else {diagPosO = 0;}
                if(diagNeg[k].getCheck() == Check.O){diagNegO++;}else {diagNegO = 0;}
                itr++;
            }
            Log.i(TAG,"Diagposx "+diagPosX);
            Log.i(TAG,"Diagnegx "+diagNegX);
            Log.i(TAG,"colX "+colX);
            Log.i(TAG,"Rowx "+rowX);
            if(rowX == max || colX == max || diagNegX == max || diagPosX == max){
                return Check.X;
            }else if(rowO == max || colO == max || diagNegO == max || diagPosO == max){
                return Check.O;
            }
        }
        Log.i(TAG,"Iterations: "+itr);
        if(checkedSquares == len*len)return Check.e;
        return null;
    }

    public static void resetBoard(Square[][]squareMatrix){
        for (Square[] squares:squareMatrix) {
            for(Square s: squares){
                s.setCheck(Check.e);
            }
        }
    }
}
