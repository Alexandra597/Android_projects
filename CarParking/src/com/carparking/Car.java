package com.carparking;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

public class Car implements Parcelable {
    private String numberplate;
    private String model;
    private String colorName;
    private int colorCode;
    private long dbID;

    private static final String[] MODEL_LIST = {"Kia","Tesla","Hyundai","Mazda","Mitsubishi",
                                        "Dodge", "BMW", "Mercedes", "Lamborghini", "Toyota"};
    private static final String[] COLOR_LIST = {"red", "blue", "green", "white", "gray",
            "cyan", "magenta", "yellow", "lightgray", "darkgray", "grey", "lightgrey",
            "darkgrey", "aqua", "fuschia", "lime", "maroon", "navy", "olive", "purple",
            "silver", "teal"};
    private static final String ALPHABET = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";

    public Car() {
        int i = (int) Math.floor(Math.random()*9);
        model = MODEL_LIST[i];
        int j = (int) Math.floor(Math.random()*21);
        colorName = COLOR_LIST[j];
        colorCode = Color.parseColor(colorName);
        int l1 = (int) Math.floor(Math.random()*25);
        int l2 = (int) Math.floor(Math.random()*25);
        int num = (int) Math.floor(Math.random()*8999 + 1000);
        numberplate = Character.toString(ALPHABET.charAt(l1)) +
                      Character.toString(ALPHABET.charAt(l2)) +
                      num;
    }

    public Car(String numberpl, String model_name, String color, String color_code) {
        numberplate = numberpl;
        model = model_name;
        colorName = color;
        colorCode = Color.parseColor(color_code);
    }

    public Car(String numberpl, String model_name, String color, int color_code) {
        numberplate = numberpl;
        model = model_name;
        colorName = color;
        colorCode = color_code;
    }

    public String getNumberplate() {
        return numberplate;
    }

    public String getModel() {
        return model;
    }

    public String getColorName() {
        return colorName;
    }

    public int getColorCode() {
        return colorCode;
    }

    public void setDbID(long id) {
        this.dbID = id;
    }

    public long getDbID() {
        return dbID;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(numberplate);
        parcel.writeString(model);
        parcel.writeString(colorName);
        parcel.writeInt(colorCode);
        parcel.writeLong(dbID);
    }

    public static final Parcelable.Creator<Car> CREATOR = new Parcelable.Creator<Car>() {
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    private Car(Parcel parcel) {
        numberplate = parcel.readString();
        model = parcel.readString();
        colorName = parcel.readString();
        colorCode = parcel.readInt();
        dbID = parcel.readLong();
    }
}
