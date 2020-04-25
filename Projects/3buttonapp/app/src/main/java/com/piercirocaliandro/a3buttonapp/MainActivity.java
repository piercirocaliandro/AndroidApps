package com.piercirocaliandro.a3buttonapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int ACTIVITY_2 = 501;
    private Button btnRed;
    private Button btnBlue;
    private Button btnGreen;
    private TextView tvOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRed = findViewById(R.id.btnRed);
        btnBlue = findViewById(R.id.btnBlue);
        btnGreen = findViewById(R.id.btnGreen);
        tvOutput = findViewById(R.id.tvOutput);
        btnBlue.setOnClickListener(this);
        btnGreen.setOnClickListener(this);
        btnRed.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ACTIVITY_2){
            if(resultCode == RESULT_OK){
                String temp = data.getStringExtra("text");
                tvOutput.setText(temp);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Bundle b = new Bundle();
        Intent i = new Intent(this, Main2Activity.class);
        if(v.getId() == R.id.btnGreen){
            b.putInt("colore", Color.GREEN);
        }
        else if(v.getId() == R.id.btnRed){
            b.putInt("colore", Color.RED);
        }
        else{
            b.putInt("colore", Color.BLUE);
        }
        i.putExtras(b);
        startActivityForResult(i, ACTIVITY_2);
    }
}
