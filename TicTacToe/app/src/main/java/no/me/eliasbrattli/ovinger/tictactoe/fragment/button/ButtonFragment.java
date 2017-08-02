package no.me.eliasbrattli.ovinger.tictactoe.fragment.button;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import no.me.eliasbrattli.ovinger.tictactoe.MenuActivity;
import no.me.eliasbrattli.ovinger.tictactoe.R;
import no.me.eliasbrattli.ovinger.tictactoe.fragment.board.BoardFragment;

/**
 * Created by EliasBrattli on 21/11/2016.
 * Fragment on screen contains button with options
 */
public class ButtonFragment extends Fragment implements View.OnClickListener{
    private static final String TAG ="ButtonFragment";
    private BoardFragment.OnFragmentInteractionListener listener;
    private Button homeButton;
    private Button nextRoundButton;
    private boolean eng;
    private int[] score;
    private View view;
    public ButtonFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);
        view = inflater.inflate(R.layout.button,container,false);
        findAllViewsById(view);
        enableAllViews();
        setAllOnClickListeners();

        return view;
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            listener = (BoardFragment.OnFragmentInteractionListener) context;
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }
    @Override
    public void onDetach(){
        super.onDetach();
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            default:break;
            case R.id.nextRoundButton:
                resetNextRoundButtonColor();
                score = listener.getScore();
                if(listener.getRounds() < listener.getRoundsToWin()) {
                    listener.nextRound();
                }else{
                    Log.i(TAG,"Finishing..");
                    alertOnFinish();
                }
                break;
            case R.id.homeButton:
                home();
                break;
        }
    }
    private void alertOnFinish(){
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            LinearLayout group = (LinearLayout) view.findViewById(R.id.gameSummary);
            View inflated = LayoutInflater.from(getContext()).inflate(R.layout.game_summary, group, false);
            TextView winnerName = (TextView)inflated.findViewById(R.id.winnerName);
            TextView amtWinsPlayer1 = (TextView)inflated.findViewById(R.id.amtWinsPlayer1);
            TextView amtWinsPlayer2 = (TextView)inflated.findViewById(R.id.amtWinsPlayer2);
            TextView movesPerRound = (TextView)inflated.findViewById(R.id.movesPerWin);
            String movesPerRoundConcat = "";
            int[] movesPerRoundArr = listener.getMovesPerRound();

            for (int i = 0; i < movesPerRoundArr.length; i++) {
                movesPerRoundConcat += movesPerRoundArr[i]+", ";
            }
            if(listener.getScore()[0] != listener.getScore()[1]) {
                winnerName.setText(getString((eng ? R.string.winner_eng : R.string.winner_nor), listener.getWinner()));
            }else{
                winnerName.setText(eng?R.string.draw_eng:R.string.draw_nor);
            }
            amtWinsPlayer1.setText(getString(R.string.colon,listener.getPlayer1Name(),listener.getScore()[0]));
            amtWinsPlayer2.setText(getString(R.string.colon,listener.getPlayer2Name(),listener.getScore()[1]));
            movesPerRound.setText(getString((eng?R.string.moves_per_win_eng:R.string.moves_per_win_nor),movesPerRoundConcat));
            builder.setView(inflated);
            builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    home();
                }
            });
            builder.show();
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }
    private void findAllViewsById(View view){
        homeButton = (Button)view.findViewById(R.id.homeButton);
        nextRoundButton = (Button)view.findViewById(R.id.nextRoundButton);
    }
    private void enableAllViews(){
        homeButton.setEnabled(true);
        homeButton.setBackgroundResource(android.R.drawable.btn_default);
        nextRoundButton.setEnabled(true);
        nextRoundButton.setBackgroundResource(android.R.drawable.btn_default);
    }
    private void setAllOnClickListeners(){
        homeButton.setOnClickListener(this);
        nextRoundButton.setOnClickListener(this);
    }
    public void resetNextRoundButtonColor(){
        nextRoundButton.setBackgroundResource(android.R.drawable.btn_default);
    }
    public void highlightNextRoundButton(int color){
        nextRoundButton.setBackgroundColor(color);
    }
    private void setButtonText(boolean eng){
        homeButton.setText(eng?R.string.home_eng:R.string.home_nor);
        nextRoundButton.setText(eng? R.string.next_round_eng: R.string.next_round_nor);
    }
    public void setLanguage(boolean eng){
        this.eng = eng;
        setButtonText(eng);
    }

    public void home(){
        Intent i=new Intent(getContext(), MenuActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        //finish()
    }
}
