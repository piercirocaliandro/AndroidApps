package com.piercirocaliandro.cocktailpassion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CocktailByName extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_search_by_name);
        new Holder();
    }

    class Holder implements View.OnClickListener{
        private Button btnSearch;
        private EditText etSearch;

        public  Holder(){
            btnSearch = findViewById(R.id.btnSrchName);
            etSearch = findViewById(R.id.etSearchIngr);
            btnSearch.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(!etSearch.getText().toString().equals("")) {
                change(etSearch.getText().toString());
            }
        }
    }

    public void change(String ctName){
        Intent i = new Intent(this, CocktailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("name", ctName);
        i.putExtras(bundle);
        startActivity(i);
    }
}
