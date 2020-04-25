package com.piercirocaliandro.cocktailpassion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CocktailActivity extends AppCompatActivity {
  private AppCocktailDatabase db;
  private Bitmap ctImage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cocktail);
    createDB();
    Holder h = new Holder();
    Bundle b = getIntent().getExtras();
    if(b.getString("name") != null){
      h.model.searchCocktailsByName(b.getString("name"));
    }
    else if(b.getString("ingr") != null){
      h.model.searchCocktailsByIngredient(b.getString("ingr"));
    }
  }

  private void createDB() {
    db = Room.databaseBuilder(getApplicationContext(),
        AppCocktailDatabase.class,
        "cocktail.db")
        .allowMainThreadQueries()
        .build();
  }

  abstract class VolleyCocktail implements Response.ErrorListener, Response.Listener<String> {
    abstract void fill(List<Cocktail> cnt);

    private static final String APIKEY = "1";
    void searchCocktailById(String id) {
      String url = "https://www.thecocktaildb.com/api/json/v1/%s/lookup.php?i=%s";
      url = String.format(url, APIKEY, id);
      apiCall(url);
    }

    void searchCocktailsByName(String s) {
      String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=%s";
      url = String.format(url, s);
      apiCall(url);
      url = url + "#prettyPhoto";
    }

    void searchCocktailsByIngredient(String i) {
      String url = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=%s";
      url = String.format(url, i);
      apiCall(url);
    }


    private void apiCall(String url) {
      RequestQueue requestQueue;
      requestQueue = Volley.newRequestQueue(CocktailActivity.this);
      StringRequest stringRequest = new StringRequest(Request.Method.GET,
          url,
          this,
          this);
      requestQueue.add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
      Log.w("QueryFail", error.getMessage());
    }

    @Override
    public void onResponse(String response) {
      Gson gson = new Gson();
      String drinks;
      try {
        JSONObject jsonObject = new JSONObject(response);
        drinks = jsonObject.getJSONArray("drinks").toString();
        Type listType = new TypeToken<List<Cocktail>>() {
        }.getType();
        List<Cocktail> cnt = gson.fromJson(drinks, listType);
        if (cnt != null && cnt.size() > 0) {
          Log.w("CA", "" + cnt.size());
          db.cocktailDAO().insertAll(cnt);    // NON OBBLIGATORIO
          fill(cnt);
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
  }

  class Holder {
    final RecyclerView rvCocktails;
    final VolleyCocktail model;

    Holder() {
      rvCocktails = findViewById(R.id.rvCocktails);
      this.model = new VolleyCocktail() {
        @Override
        void fill(List<Cocktail> cnt) {
          System.out.println(cnt.size());
          Log.w("CA", "fill");
          fillList(cnt);
        }

        private void fillList(List<Cocktail> cnt) {
          RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CocktailActivity.this);
          rvCocktails.setLayoutManager(layoutManager);
          CocktailAdapter mAdapter = new CocktailAdapter(cnt);
          rvCocktails.setAdapter(mAdapter);
        }
      };
    }
  }

  private class CocktailAdapter extends RecyclerView.Adapter<CocktailAdapter.Holder> implements View.OnClickListener {
    private final List<Cocktail> cocktails;

    CocktailAdapter(List<Cocktail> all) {
      cocktails = new ArrayList<>();
      cocktails.addAll(all);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      ConstraintLayout cl;
      cl = (ConstraintLayout) LayoutInflater
          .from(parent.getContext())
          .inflate(R.layout.layout_cocktail, parent, false);
      cl.setOnClickListener(this);
      return new Holder(cl);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
      holder.tvDrink.setText(cocktails.get(position).strDrink);
      if(cocktails.get(position).strAlcoholic != null) {
        if (cocktails.get(position).strAlcoholic.compareTo("Alcoholic") == 0)
          holder.ivAlcoholic.setImageResource(R.drawable.ic_alcolico);
        else
          holder.ivAlcoholic.setImageResource(R.drawable.ic_noalcolico);
      }
    }

    @Override
    public int getItemCount() {
      return cocktails.size();
    }

    @Override
    public void onClick(View v) {
      int position = ((RecyclerView) v.getParent()).getChildAdapterPosition(v);
      Cocktail cocktail = cocktails.get(position);
      Intent intent = new Intent(CocktailActivity.this, CocktailDetailActivity.class);
      intent.putExtra("cocktail", cocktail);
      intent.putExtra("ctImage", ctImage);
      CocktailActivity.this.startActivity(intent);
    }

    class Holder extends RecyclerView.ViewHolder {
      final TextView tvDrink;
      final ImageView ivAlcoholic;

      Holder(@NonNull View itemView) {
        super(itemView);
        tvDrink = itemView.findViewById(R.id.tvDrink);
        ivAlcoholic = itemView.findViewById(R.id.ivAlcoholic);
      }
    }
  }
}
