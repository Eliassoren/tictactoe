package no.me.eliasbrattli.ovinger.tictactoe.fragment.score;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import no.me.eliasbrattli.ovinger.tictactoe.R;
import no.me.eliasbrattli.ovinger.tictactoe.fragment.board.BoardFragment;

/**
 * Created by EliasBrattli on 10/04/2017.
 */
public class ScoreFragment extends Fragment{
    private final static String TAG = "ScoreFragment";
    private BoardFragment.OnFragmentInteractionListener listener;
    private boolean eng;
    private int[] score;
    private int roundsToWin;
    private int rounds;
    private TextView player1Name;
    private TextView player1Score;
    private TextView player2Name;
    private TextView player2Score;
    private final String SCORE_PLACEHOLDER = "-1";
    private final String NAME_PLACEHOLDER = "Player";
    public ScoreFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.score,container,false);
        findAllViewsById(view);
        enableAllViews();
        score = new int[2];
        return view;
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            listener = (BoardFragment.OnFragmentInteractionListener) context;
            roundsToWin = listener.getRoundsToWin();
            highlightTurn(listener.getPlayer1Name());
            //Log.i(TAG,listener.isEnglish()+"Verdi");

        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }
    @Override
    public void onDetach(){
        super.onDetach();
    }
    private void findAllViewsById(View view){
        player1Name = (TextView)view.findViewById(R.id.player1Name);
        player1Score = (TextView)view.findViewById(R.id.player1Score);
        player2Name = (TextView)view.findViewById(R.id.player2Name);
        player2Score = (TextView)view.findViewById(R.id.player2Score);
    }
    private void enableAllViews(){
        player1Name.setEnabled(true);
        player1Name.setText(NAME_PLACEHOLDER);
        player1Score.setEnabled(true);
        player1Score.setText(SCORE_PLACEHOLDER);
        player2Name.setEnabled(true);
        player2Name.setText(NAME_PLACEHOLDER);
        player2Score.setEnabled(true);
        player2Score.setText(SCORE_PLACEHOLDER);
    }
    public void setPlayer1Name(String n){
        Log.i(TAG,"Setting player 1 Name");
        player1Name.setText(getString(R.string.player1_fill,n));
    }
    public void setPlayer2Name(String n){
        player2Name.setText(getString(R.string.player2_fill,n));
    }
    public void setPlayer1Score(String score){
        player1Score.setText(score);
    }
    public void setPlayer2Score(String score){
        Log.i(TAG,"Setting player 2 score");
        player2Score.setText(score);
    }
    public void incrementPlayer1Score(){
        player1Score.setText(getString(R.string.player_score,++score[0]));
    }
    public void incrementPlayer2Score(){
        player2Score.setText(getString(R.string.player_score,++score[1]));
    }
    public void highlightTurn(String player){
        Log.i(TAG,"Highlight");
        if(player.equals(listener.getPlayer1Name())){
            player1Name.setTextColor(Color.RED);
            player1Score.setTextColor(Color.RED);
            player2Name.setTextColor(Color.BLACK);
            player2Score.setTextColor(Color.BLACK);
        }else{
            player1Name.setTextColor(Color.BLACK);
            player1Score.setTextColor(Color.BLACK);
            player2Name.setTextColor(Color.RED);
            player2Score.setTextColor(Color.RED);
        }
    }
    public int[] getScore() {
        return score;
    }
    public void setLanguage(boolean eng){
        this.eng = eng;
    }
    public void incrementRounds(){
        rounds++;
    }
    public int getRounds(){
        return rounds;
    }
}
