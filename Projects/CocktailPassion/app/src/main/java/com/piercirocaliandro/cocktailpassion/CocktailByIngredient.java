package com.piercirocaliandro.cocktailpassion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CocktailByIngredient extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_ingredient);
        new Holder();
    }

    class Holder implements View.OnClickListener{
        private Button btnSearchIngr;
        private EditText etSearch;

        public Holder(){
            btnSearchIngr = findViewById(R.id.btnSrchName);
            etSearch = findViewById(R.id.etSearchIngr);
            btnSearchIngr.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(!etSearch.getText().toString().equals("")){
                change(etSearch.getText().toString());
            }
        }
    }

    public void change(String ingr){
        Intent i = new Intent(this, CocktailActivity.class);
        Bundle b = new Bundle();
        b.putString("ingr", ingr);
        i.putExtras(b);
        startActivity(i);
    }

}
