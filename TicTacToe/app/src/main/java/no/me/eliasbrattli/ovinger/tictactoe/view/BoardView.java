package no.me.eliasbrattli.ovinger.tictactoe.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import no.me.eliasbrattli.ovinger.tictactoe.data.Square;
import no.me.eliasbrattli.ovinger.tictactoe.util.DrawUtil;
import no.me.eliasbrattli.ovinger.tictactoe.fragment.board.BoardFragment;
import no.me.eliasbrattli.ovinger.tictactoe.util.Check;
import no.me.eliasbrattli.ovinger.tictactoe.util.GameUtil;

/**
 * Created by EliasBrattli on 10/04/2017.
 * Game view with canvas
 */
public class BoardView extends View {
    private static final String TAG = "BoardView";
    private final int BOARD_SIDE = 8;
    private final int MAX_SIDE = 5;
    private Square[][] squareMatrix;
    private final int DEFAULT_BOARD = 3;
    //private final int SQUARE_SIZE = 331;
    private int squareSize = 327;
    private final int SQUARE_SIZE = squareSize*DEFAULT_BOARD/BOARD_SIDE;
    private Paint squarePaint;
    private Paint checkPaint;
    private int turn;
    private BoardFragment parentFragment;

    public BoardView(Context context){
        super(context);
        init();
    }
    public BoardView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        init();
    }
    public void setParentFragment(BoardFragment fragment){
        this.parentFragment = fragment;
    }

    private void initSquarePaint(int color){
        squarePaint = new Paint();
        squarePaint.setColor(color);
        squarePaint.setStrokeWidth(10);
    }
    private void initCheckPaint(int color){
        checkPaint = new Paint();
        checkPaint.setColor(color);
        checkPaint.setStrokeWidth(7);
    }
    public void init(){
        initSquarePaint(Color.BLACK);
        initCheckPaint(Color.YELLOW);
        squareMatrix = GameUtil.initDrawComponents(BOARD_SIDE,SQUARE_SIZE,squarePaint,checkPaint);
        turn = 1;
    }
    public int getTurn(){
        return turn;
    }
    public void reset(){
        turn = BOARD_SIDE*BOARD_SIDE;
        Log.i(TAG,"Reset");
        GameUtil.resetBoard(squareMatrix);
        invalidate();
        turn = 1;
    }

    public Check gameResult()throws Exception{
        return GameUtil.gameResult(squareMatrix,MAX_SIDE);
    }

    @Override
    public void onDraw(Canvas canvas){
        Log.i(TAG,"Drawing");
        DrawUtil.drawBoard(canvas,squarePaint,squareMatrix);
        DrawUtil.drawChecks(canvas,squareMatrix);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            int x = (int) event.getX();
            int y = (int) event.getY();
            switch (event.getAction()) {
                default:
                    break;
                case MotionEvent.ACTION_DOWN:
                    Log.i(TAG, "Turn: " + turn);
                    if(GameUtil.gameResult(squareMatrix,MAX_SIDE) == null) {
                        for (Square[] squares : squareMatrix) {
                            for (Square s : squares) {
                                if (s.contains(x, y)) {
                                    if (s.getCheck() == Check.e && turn <= BOARD_SIDE*BOARD_SIDE) {
                                        //Check X for player 1, O for player2.
                                        s.setCheck(turn++ % 2 == 0 ? Check.O : Check.X);
                                        parentFragment.highlightTurn(turn);
                                    }
                                }
                            }
                        }
                        Log.i(TAG, "Turn: " + turn);
                        invalidate();
                        parentFragment.checkRes();
                    }
                    break;
            }
        }catch(Exception e){
            Log.e(TAG,e.getMessage());
        }
        return true;
    }


}
