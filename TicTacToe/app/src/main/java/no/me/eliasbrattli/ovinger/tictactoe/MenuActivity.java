package no.me.eliasbrattli.ovinger.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;


/**
 * Created by EliasBrattli on 24/11/2016.
 */
public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "MenuActivity";
    private RadioButton norwegianRB;
    private RadioButton englishRB;
    boolean eng = false;
    private Button gameButton;
    private Button howToPlayButton;
    private Button exitButton;
    private String ENG_HOWTO_TITLE;
    private String NOR_HOWTO_TITLE;
    private String ENG_DIALOG_MSG;
    private String NOR_DIALOG_MSG;
    private String ENG_REGISTER_TITLE;
    private String NOR_REGISTER_TITLE;
    private final int DEFAULT_ROUNDS_TO_WIN = 3;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ENG_HOWTO_TITLE = getText(R.string.dialog_title_eng).toString();
        NOR_HOWTO_TITLE = getText(R.string.dialog_title_nor).toString();
        ENG_DIALOG_MSG = getText(R.string.dialog_message_eng).toString();
        NOR_DIALOG_MSG = getText(R.string.dialog_message_nor).toString();
        ENG_REGISTER_TITLE = getText(R.string.register_title_eng).toString();
        NOR_REGISTER_TITLE = getText(R.string.register_title_nor).toString();
        findAllViewsById();
        enableAllViews();
        setOnClickListeners();
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            default:break;
            case R.id.gameButton:
                Log.i(TAG,"Gamebutton clicked");
                gameButton.setEnabled(false);
                registerPlayers(eng);
                break;
            case R.id.howToPlayButton:
                Log.i(TAG,"Howtoplaybutton clicked");
                howToPlayButton.setEnabled(false);
                howToPlay(eng);
                break;
            case R.id.exitButton:
                Log.i(TAG,"Exitbutton clicked");
                exitButton.setEnabled(false);
                exitAlert();
                break;
        }
    }
    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton)view).isChecked();
        switch (view.getId()){
            default:break;
            case R.id.norwegianRB:
                if(checked){
                    eng = false;
                    gameButton.setText(R.string.btn_game_nor);
                    howToPlayButton.setText(R.string.btn_howto_nor);
                    exitButton.setText(R.string.btn_exit_nor);
                }
                break;
            case R.id.englishRB:
                if(checked){
                    eng = true;
                    gameButton.setText(R.string.btn_game_eng);
                    howToPlayButton.setText(R.string.btn_howto_eng);
                    exitButton.setText(R.string.btn_exit_eng);
                }
                break;
        }
    }
    void findAllViewsById(){
        norwegianRB = (RadioButton)findViewById(R.id.norwegianRB);
        englishRB = (RadioButton)findViewById(R.id.englishRB);
        gameButton = (Button)findViewById(R.id.gameButton);
        howToPlayButton = (Button)findViewById(R.id.howToPlayButton);
        exitButton = (Button)findViewById(R.id.exitButton);
    }
    void setOnClickListeners(){
        gameButton.setOnClickListener(this);
        howToPlayButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
    }
    void enableAllViews(){
        norwegianRB.setEnabled(true);
        englishRB.setEnabled(true);
        norwegianRB.setChecked(true);
        gameButton.setEnabled(true);
        howToPlayButton.setEnabled(true);
        exitButton.setEnabled(true);
    }

    void howToPlay(boolean eng){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Log.i(TAG,""+eng);
        builder.setTitle(eng? ENG_HOWTO_TITLE : NOR_HOWTO_TITLE);
        builder.setMessage(eng?ENG_DIALOG_MSG:NOR_DIALOG_MSG);
        builder.setNegativeButton(R.string.dialog_ok,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                howToPlayButton.setEnabled(true);
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    void exitAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alert_title);
        builder.setMessage(eng?R.string.finish_alert_eng:R.string.finish_alert_nor);
        builder.setPositiveButton(R.string.dialog_ok,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface,int i){
                finish();
            }
        });
        builder.setNegativeButton(eng?R.string.dialogcancel_eng:R.string.dialogcancel_nor,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface,int i){
                exitButton.setEnabled(true);
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    void registerPlayers(boolean eng){
        Log.i(TAG,""+eng);
        try {
            AlertDialog.Builder builder;
            final EditText playerName1;
            final EditText playerName2;
            final EditText roundsToWinInput;
            LinearLayout group;
            final boolean ENGLISH = eng;
            final View inflated;
            final Intent intent = new Intent(getBaseContext(), GameActivity.class);
            final Bundle extras;

            builder = new AlertDialog.Builder(this);
            group = (LinearLayout)findViewById(R.id.inputGroup);
            inflated = LayoutInflater.from(this).inflate(R.layout.register_players,group,false);
            playerName1 = (EditText)inflated.findViewById(R.id.playerNameInput1);
            playerName2 = (EditText)inflated.findViewById(R.id.playerNameInput2);
            roundsToWinInput = (EditText)inflated.findViewById(R.id.roundsToWin);
            extras = new Bundle();

            builder.setView(inflated);
            builder.setTitle(eng ? ENG_REGISTER_TITLE : NOR_REGISTER_TITLE);
            playerName1.setHint((eng?R.string.player_placeholder_eng:R.string.player_placeholder_nor));
            playerName2.setHint((eng?R.string.player_placeholder_eng:R.string.player_placeholder_nor));
            roundsToWinInput.setHint((eng?R.string.num_rounds_hint_eng:R.string.num_rounds_hint_nor));
            builder.setPositiveButton(eng ? R.string.dialogplay_eng : R.string.dialogplay_nor, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String player1 = playerName1.getText().toString();
                    String player2 = playerName2.getText().toString();
                    String roundsToWinParse = roundsToWinInput.getText().toString().trim();
                    Log.i(TAG,"Roundstowinparse: "+roundsToWinParse);
                    int roundsToWin = DEFAULT_ROUNDS_TO_WIN;
                    if(TextUtils.isEmpty(player1))
                        player1 = (ENGLISH? "Player 1":"Spiller 1");
                    if(TextUtils.isEmpty(player2))
                        player2 = (ENGLISH? "Player 2":"Spiller 2");
                    if(!TextUtils.isEmpty(roundsToWinParse) && !roundsToWinParse.equals("-")){
                        roundsToWin = Math.abs(Integer.parseInt(roundsToWinParse));
                        if(roundsToWin < 1)roundsToWin = 1;
                    }

                    extras.putBoolean("Language",ENGLISH);
                    extras.putString("Player1", player1);
                    extras.putString("Player2", player2);
                    extras.putInt("RoundsToWin",roundsToWin);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton(eng ? R.string.dialogcancel_eng : R.string.dialogcancel_nor, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    gameButton.setEnabled(true);
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }
}
