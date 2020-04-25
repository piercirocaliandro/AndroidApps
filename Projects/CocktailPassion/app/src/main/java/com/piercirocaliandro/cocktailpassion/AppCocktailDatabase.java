package com.piercirocaliandro.cocktailpassion;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Cocktail.class}, version = 1)
public abstract class AppCocktailDatabase extends RoomDatabase {
  public abstract CocktailDAO cocktailDAO();
}
