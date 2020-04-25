package com.piercirocaliandro.a3buttonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private Button btnOk;
    private EditText etInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle bund = getIntent().getExtras();
        btnOk = findViewById(R.id.btnOk);
        etInput = findViewById(R.id.etInput);
        int color = bund.getInt("colore");
        etInput.setBackgroundColor(color);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onBackPressed(){
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        /* cosa torno all'activity chiamante*/
        Intent data = new Intent();
        data.putExtra("text", etInput.getText().toString());
        setResult(RESULT_OK, data);
        finish(); //termina activity
    }
}
