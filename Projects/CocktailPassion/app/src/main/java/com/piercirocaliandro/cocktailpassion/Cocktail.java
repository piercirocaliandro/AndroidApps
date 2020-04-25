package com.piercirocaliandro.cocktailpassion;

// COSA SONO I PARCELABLE

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

@Entity
public class Cocktail implements Parcelable {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name="_id")
  public int idDrink;
  @ColumnInfo(name="strDrink")
  public String strDrink;
  @ColumnInfo(name="strDrinkAlternate")
  String strDrinkAlternate;
  @ColumnInfo(name="strTags")
  String strTags;
  @ColumnInfo(name="strVideo")
  String strVideo;
  @ColumnInfo(name="strCategory")
  String strCategory;
  @ColumnInfo(name="strIBA")
  String strIBA;
  @ColumnInfo(name="strAlcoholic")
  public String strAlcoholic;
  @ColumnInfo(name="strGlass")
  String strGlass;
  @ColumnInfo(name="strInstructions")
  String strInstructions;
  @ColumnInfo(name="strDrinkThumb")
  String strDrinkThumb;
  @ColumnInfo(name="strIngredient1")
  String strIngredient1;
  @ColumnInfo(name="strIngredient2")
  String strIngredient2;
  @ColumnInfo(name="strIngredient3")
  String strIngredient3;
  @ColumnInfo(name="strIngredient4")
  String strIngredient4;
  @ColumnInfo(name="strIngredient5")
  String strIngredient5;
  @ColumnInfo(name="strIngredient6")
  String strIngredient6;
  @ColumnInfo(name="strIngredient7")
  String strIngredient7;
  @ColumnInfo(name="strIngredient8")
  String strIngredient8;
  @ColumnInfo(name="strIngredient9")
  String strIngredient9;
  @ColumnInfo(name="strIngredient10")
  String strIngredient10;
  @ColumnInfo(name="strIngredient11")
  String strIngredient11;
  @ColumnInfo(name="strIngredient12")
  String strIngredient12;
  @ColumnInfo(name="strIngredient13")
  String strIngredient13;
  @ColumnInfo(name="strIngredient14")
  String strIngredient14;
  @ColumnInfo(name="strIngredient15")
  String strIngredient15;
  @ColumnInfo(name="strMeasure1")
  String strMeasure1;
  @ColumnInfo(name="strMeasure2")
  String strMeasure2;
  @ColumnInfo(name="strMeasure3")
  String strMeasure3;
  @ColumnInfo(name="strMeasure4")
  String strMeasure4;
  @ColumnInfo(name="strMeasure5")
  String strMeasure5;
  @ColumnInfo(name="strMeasure6")
  String strMeasure6;
  @ColumnInfo(name="strMeasure7")
  String strMeasure7;
  @ColumnInfo(name="strMeasure8")
  String strMeasure8;
  @ColumnInfo(name="strMeasure9")
  String strMeasure9;
  @ColumnInfo(name="strMeasure10")
  String strMeasure10;
  @ColumnInfo(name="strMeasure11")
  String strMeasure11;
  @ColumnInfo(name="strMeasure12")
  String strMeasure12;
  @ColumnInfo(name="strMeasure13")
  String strMeasure13;
  @ColumnInfo(name="strMeasure14")
  String strMeasure14;
  @ColumnInfo(name="strMeasure15")
  String strMeasure15;
  @ColumnInfo(name="strCreativeCommonsConfirmed")
  String strCreativeCommonsConfirmed;
  @ColumnInfo(name="dateModified")
  String dateModified;

  public static final Creator<Cocktail> CREATOR = new Creator<Cocktail>() {
    @Override
    public Cocktail createFromParcel(Parcel in) {
      return new Cocktail(in);
    }

    @Override
    public Cocktail[] newArray(int size) {
      return new Cocktail[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
      dest.writeInt(idDrink);
      dest.writeString(strDrink);
      dest.writeString(strDrinkAlternate);
      dest.writeString(strTags);
      dest.writeString(strVideo);
      dest.writeString(strCategory);
      dest.writeString(strIBA);
      dest.writeString(strAlcoholic);
      dest.writeString(strGlass);
      dest.writeString(strInstructions);
      dest.writeString(strDrinkThumb);
      dest.writeString(strIngredient1);
      dest.writeString(strIngredient2);
      dest.writeString(strIngredient3);
      dest.writeString(strIngredient4);
      dest.writeString(strIngredient5);
      dest.writeString(strIngredient6);
      dest.writeString(strIngredient7);
      dest.writeString(strIngredient8);
      dest.writeString(strIngredient9);
      dest.writeString(strIngredient10);
      dest.writeString(strIngredient11);
      dest.writeString(strIngredient12);
      dest.writeString(strIngredient13);
      dest.writeString(strIngredient14);
      dest.writeString(strIngredient15);
      dest.writeString(strMeasure1);
      dest.writeString(strMeasure2);
      dest.writeString(strMeasure3);
      dest.writeString(strMeasure4);
      dest.writeString(strMeasure5);
      dest.writeString(strMeasure6);
      dest.writeString(strMeasure7);
      dest.writeString(strMeasure8);
      dest.writeString(strMeasure9);
      dest.writeString(strMeasure10);
      dest.writeString(strMeasure11);
      dest.writeString(strMeasure12);
      dest.writeString(strMeasure13);
      dest.writeString(strMeasure14);
      dest.writeString(strMeasure15);
      dest.writeString(strCreativeCommonsConfirmed);
      dest.writeString(dateModified);
  }

  public Cocktail() {

  }

  private Cocktail(Parcel in) {
    idDrink = in.readInt();
    strDrink = in.readString();
    strDrinkAlternate = in.readString();
    strTags = in.readString();
    strVideo = in.readString();
    strCategory = in.readString();
    strIBA = in.readString();
    strAlcoholic = in.readString();
    strGlass = in.readString();
    strInstructions = in.readString();
    strDrinkThumb = in.readString();
    strIngredient1 = in.readString();
    strIngredient2 = in.readString();
    strIngredient3 = in.readString();
    strIngredient4 = in.readString();
    strIngredient5 = in.readString();
    strIngredient6 = in.readString();
    strIngredient7 = in.readString();
    strIngredient8 = in.readString();
    strIngredient9 = in.readString();
    strIngredient10 = in.readString();
    strIngredient11 = in.readString();
    strIngredient12 = in.readString();
    strIngredient13 = in.readString();
    strIngredient14 = in.readString();
    strIngredient15 = in.readString();
    strMeasure1 = in.readString();
    strMeasure2 = in.readString();
    strMeasure3 = in.readString();
    strMeasure4 = in.readString();
    strMeasure5 = in.readString();
    strMeasure6 = in.readString();
    strMeasure7 = in.readString();
    strMeasure8 = in.readString();
    strMeasure9 = in.readString();
    strMeasure10 = in.readString();
    strMeasure11 = in.readString();
    strMeasure12 = in.readString();
    strMeasure13 = in.readString();
    strMeasure14 = in.readString();
    strMeasure15 = in.readString();
    strCreativeCommonsConfirmed = in.readString();
    dateModified = in.readString();
  }
}
