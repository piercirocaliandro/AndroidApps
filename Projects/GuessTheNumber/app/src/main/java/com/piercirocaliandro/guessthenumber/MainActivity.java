package com.piercirocaliandro.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private int guessValue;
    private int numberOfGuess = 6;
    private Random rand;
    private int upperBound = 101;
    private int insValue = 0;
    private EditText etGetNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rand = new Random();
        guessValue = rand.nextInt(upperBound);
        Holder holder = new Holder();
    }

    class Holder implements View.OnClickListener{
        private ImageView iwFace;
        private TextView tvInsertNumber;
        private TextView tvMax;
        private TextView tvMin;
        private Button btnNewGame;

        public Holder(){
            iwFace = findViewById(R.id.iwFace);
            tvInsertNumber = findViewById(R.id.tvInsertNumber);
            tvMax = findViewById(R.id.tvMax);
            tvMin = findViewById(R.id.tvMin);
            etGetNumber = findViewById(R.id.etGetNumber);
            etGetNumber.setOnEditorActionListener(new ConfButtonListener());
            btnNewGame = findViewById(R.id.btnNewGame);
            btnNewGame.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == btnNewGame.getId()){
                startNewGame();
            }
        }



        public void setWin(){
            iwFace.setImageResource(R.drawable.win);
            tvInsertNumber.setText("You win !");
            etGetNumber.setText("");
        }

        public void setNotGood(int inserted){
            iwFace.setImageResource(R.drawable.tryagain);
            if(inserted > guessValue && inserted < Integer.parseInt(tvMax.getText().toString())){
                tvMax.setText(String.valueOf(inserted));
                tvInsertNumber.setText("Number too big ! Try again");
                etGetNumber.setText("");
            }
            else if(inserted < guessValue && inserted > Integer.parseInt(tvMin.getText().toString())){
                tvInsertNumber.setText("Number too small! Try again");
                tvMin.setText(String.valueOf(inserted));
                etGetNumber.setText("");
            }
            else{
                tvInsertNumber.setText("Number out of range !");
                etGetNumber.setText("");
                numberOfGuess += 1;
            }
        }

        public void setLost(){
            iwFace.setImageResource(R.drawable.youlost);
            tvInsertNumber.setText("You lose !");
            etGetNumber.setText("");
        }

        public void startNewGame(){
            iwFace.setImageResource(R.drawable.welcome);
            tvInsertNumber.setText("Insert your number !");
            tvMin.setText("0");
            tvMax.setText("100");
            etGetNumber.setText("");
            guessValue = rand.nextInt(upperBound);
            numberOfGuess = 6;
        }

        public class ConfButtonListener  implements TextView.OnEditorActionListener {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    insValue = Integer.parseInt(v.getText().toString());
                    checkValue();
                    hideSoftKeyboard(MainActivity.this);
                    return true;
                }
                else {
                    return false;
                }
            }
        }

        public void hideSoftKeyboard(Activity activity){
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }

        public void checkValue(){
            numberOfGuess -= 1;
            if (insValue == guessValue && numberOfGuess >= 0) {
                setWin();
            }
            else if (numberOfGuess == 0) {
                setLost();
            }
            else if (insValue != guessValue && numberOfGuess >= 0) {
                setNotGood(insValue);
            }
        }
    }
}
