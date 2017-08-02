package no.me.eliasbrattli.ovinger.tictactoe.fragment.board;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.me.eliasbrattli.ovinger.tictactoe.R;
import no.me.eliasbrattli.ovinger.tictactoe.util.Check;
import no.me.eliasbrattli.ovinger.tictactoe.view.BoardView;

/**
 * Created by EliasBrattli on 10/04/2017.
 */
public class BoardFragment extends Fragment{
    private static final String TAG = "BoardFragment";
    private BoardView boardView;
    private OnFragmentInteractionListener listener;
    private boolean eng;
    public BoardFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.board,container,false);
        boardView = (BoardView) view.findViewById(R.id.boardView);
        boardView.setParentFragment(this);
        boardView.setEnabled(true);
        return view;
    }
    @Override
    public void onAttach(Context activity){
        super.onAttach(activity);
        Log.i(TAG,"Attached Board");
        listener  = (OnFragmentInteractionListener)activity;
    }
    @Override
    public void onDetach(){
        super.onDetach();
    }
    public void draw(){
        Log.i(TAG,"Drawboard called");
        boardView.invalidate();
    }
    public void highlightTurn(int turn){
        listener.highlightTurn(turn % 2 == 0?listener.getPlayer2Name():listener.getPlayer1Name());
    }
    public void resetTurn(){
        boardView.reset();
        highlightTurn(boardView.getTurn());
    }
    public void checkRes(){
        try {
            Log.i(TAG,"Checkres");
            Check check = boardView.gameResult();
            if(boardView.getTurn() >= 5 && check!= null) {
                if (check != Check.e) {
                    Log.i(TAG, "Check win: " + check);
                    listener.onVictory(check == Check.X ? listener.getPlayer1Name() : listener.getPlayer2Name(), boardView.getTurn());
                } else {
                    listener.onDraw(boardView.getTurn());
                }
            }
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }
    public void setLanguage(boolean eng){
        this.eng = eng;
    }
    public interface OnFragmentInteractionListener{
        String getWinner();
        void nextRound();
        int[] getMovesPerRound();
        void onVictory(String player,int moves);
        void onDraw(int moves);
        int[] getScore();
        String getPlayer1Name();
        String getPlayer2Name();
        int getRoundsToWin();
        int getRounds();
        void highlightTurn(String player);
    }
}
