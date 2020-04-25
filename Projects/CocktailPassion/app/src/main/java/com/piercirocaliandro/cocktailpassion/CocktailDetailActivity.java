package com.piercirocaliandro.cocktailpassion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class CocktailDetailActivity extends AppCompatActivity {
  ImageView ivCocktailPhoto;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent data = getIntent();
    setContentView(R.layout.activity_cocktail_detail);
    Holder hold = new Holder();
    hold.setDatas(data);
  }

  public class VolleyCt {
    private void imgCall(String url) {
      RequestQueue requestQueue;
      requestQueue = Volley.newRequestQueue(CocktailDetailActivity.this);
      ImageRequest stringRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
        @Override
        public void onResponse(Bitmap response) {
          ivCocktailPhoto.setImageBitmap(response);
        }
        }, 0, 0,
            ImageView.ScaleType.CENTER_CROP,
            Bitmap.Config.RGB_565,
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                Log.w("ImgFail", error.getMessage());
              }
            });
      requestQueue.add(stringRequest);
    }
  }
  class Holder {
    private TextView tvInstructions;
    private VolleyCt vc;
    private TextView tvCtname;
    private RecyclerView rvIngredients;
    public Holder(){
      tvInstructions = findViewById(R.id.tvDescription);
      ivCocktailPhoto = findViewById(R.id.ivCocktailImg);
      tvCtname = findViewById(R.id.tvCtname);
      rvIngredients = findViewById(R.id.rvIngredients);
      vc = new VolleyCt();
    }

    public void setDatas(Intent i){
      Cocktail ct = i.getParcelableExtra("cocktail") ;
      tvInstructions.setText(ct.strInstructions);
      tvCtname.setText(ct.strDrink);
      vc.imgCall(ct.strDrinkThumb);
    }
  }
}
