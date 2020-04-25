package com.piercirocaliandro.cocktailpassion;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CocktailDAO {
  @Query("SELECT * FROM cocktail")
  List<Cocktail> getAll();

  @Query("SELECT * FROM cocktail WHERE _id IN (:ids)")
  List<Cocktail> loadAllByIds(int[] ids);

  @Query("SELECT count(*) FROM cocktail")
  int size();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertAll(Cocktail... cocktails);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertAll(List<Cocktail> cocktails);

  @Delete
  void delete(Cocktail cocktail);
}
