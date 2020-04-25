package com.piercirocaliandro.cocktailpassion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Holder hold = new Holder();
    }

    public class Holder implements View.OnClickListener {
        private Button btnSearchIngr;
        private Button btnSearchName;

        public Holder() {
            btnSearchIngr = findViewById(R.id.btnSrchName);
            btnSearchName = findViewById(R.id.btnSrchIngr);
            btnSearchIngr.setOnClickListener(this);
            btnSearchName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnSrchName){
                change(1);
            }
            else if(v.getId() == R.id.btnSrchIngr){
                change(2);
            }
        }
    }

    public void change(int code){
        Intent i = null;
        if(code == 1) {
            i = new Intent(this, CocktailByName.class);
        }
        else if(code == 2){
            i = new Intent(this, CocktailByIngredient.class);
        }
        startActivity(i);
    }
}
