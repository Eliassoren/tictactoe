package no.me.eliasbrattli.ovinger.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import no.me.eliasbrattli.ovinger.tictactoe.fragment.board.BoardFragment;
import no.me.eliasbrattli.ovinger.tictactoe.fragment.button.ButtonFragment;
import no.me.eliasbrattli.ovinger.tictactoe.fragment.score.ScoreFragment;

public class GameActivity extends AppCompatActivity implements BoardFragment.OnFragmentInteractionListener{
    private final static String TAG = "GameActivity";
    private Intent intent;
    private boolean eng;
    private String player1;
    private String player2;
    private ButtonFragment buttonFragment;
    private BoardFragment boardFragment;
    private ScoreFragment scoreFragment;
    private int roundsToWin;
    private int rounds;
    private int[] movesPerRound;

    String winner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        try{
            Log.i(TAG,"GameActivity is running...");
            intent = getIntent();
            Bundle extras = intent.getExtras();
            eng = extras.getBoolean("Language");
            player1 = extras.getString("Player1");
            player2 = extras.getString("Player2");
            roundsToWin = extras.getInt("RoundsToWin");
            movesPerRound = new int[roundsToWin];
            winner = eng?"none":"ingen";
            Log.i(TAG, Build.VERSION.RELEASE);
            initFragments();
            Toast.makeText(this,getString(eng?R.string.player_turn_eng:R.string.player_turn_nor,player1),Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }
    private void initFragments(){
        scoreFragment = (ScoreFragment)getFragmentManager().findFragmentById(R.id.scoreFragment);
        buttonFragment = (ButtonFragment)getFragmentManager().findFragmentById(R.id.buttonFragment);
        boardFragment = (BoardFragment)getFragmentManager().findFragmentById(R.id.boardFragment);
        scoreFragment.setLanguage(eng);
        buttonFragment.setLanguage(eng);
        boardFragment.draw();
        scoreFragment.setPlayer1Name(intent.getStringExtra("Player1")+"");
        scoreFragment.setPlayer2Name(intent.getStringExtra("Player2")+"");
        scoreFragment.setPlayer1Score("0");
        scoreFragment.setPlayer2Score("0");
        scoreFragment.highlightTurn(player1);
        boardFragment.setLanguage(eng);
    }

    @Override
    public void nextRound(){
        boardFragment.resetTurn();
    }
    @Override
    public void onVictory(String player, int moves){
        if(scoreFragment.getRounds()< roundsToWin) {
            Log.i(TAG,"Rounds: "+scoreFragment.getRounds()+" Roundstowin: "+roundsToWin);
            movesPerRound[scoreFragment.getRounds()] = moves;
            scoreFragment.incrementRounds();
        }
            Toast.makeText(this, getString(eng ? R.string.thewinner_was_eng : R.string.thewinner_was_nor, player), Toast.LENGTH_LONG).show();
            winner = player;
            buttonFragment.highlightNextRoundButton(Color.RED);
            if (player.equals(player1)) {
                scoreFragment.incrementPlayer1Score();
            } else {
                scoreFragment.incrementPlayer2Score();
            }
    }
    @Override
    public void onDraw(int moves){
        if(scoreFragment.getRounds() < roundsToWin) {
            movesPerRound[scoreFragment.getRounds()] = moves;
            scoreFragment.incrementRounds();
        }
            Toast.makeText(this,eng?R.string.draw_eng:R.string.draw_nor,Toast.LENGTH_LONG).show();
        buttonFragment.highlightNextRoundButton(Color.RED);
    }
    @Override
    public int[] getScore(){
        return scoreFragment.getScore();
    }

    @Override
    public String getPlayer1Name(){
        return player1;
    }

    @Override
    public String getWinner(){
        return winner;
    }
    @Override
    public int[] getMovesPerRound(){
        return movesPerRound;
    }
    @Override
    public String getPlayer2Name(){
        return player2;
    }
    @Override
    public void highlightTurn(String player){
        scoreFragment.highlightTurn(player);
    }
    @Override
    public int getRoundsToWin(){
        return roundsToWin;
    }
    @Override
    public int getRounds(){
        return scoreFragment.getRounds();
    }
}
