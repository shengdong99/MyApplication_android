package com.shengdong.recyclerviewtest.utils.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ShoeItem implements Parcelable {

    private String key, shoeName, shoeBrandName, category, pid, date, time, image;
    private int shoeImage;
    private double shoePrice;

    public ShoeItem() {
    }

    public ShoeItem(String shoeName, String shoeBrandName, int shoeImage, double shoePrice, String image) {
        this.image = image;
        this.shoeName = shoeName;
        this.shoeBrandName = shoeBrandName;
        this.shoeImage = shoeImage;
        this.shoePrice = shoePrice;
    }

    protected ShoeItem(Parcel in) {

        shoeName = in.readString();
        shoeBrandName = in.readString();
        shoeImage = in.readInt();
        shoePrice = in.readDouble();
    }

    public static final Creator<ShoeItem> CREATOR = new Creator<ShoeItem>() {
        @Override
        public ShoeItem createFromParcel(Parcel in) {
            return new ShoeItem(in);
        }

        @Override
        public ShoeItem[] newArray(int size) {
            return new ShoeItem[size];
        }
    };



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }

    public String getShoeName() {
        return shoeName;
    }

    public void setShoeName(String shoeName) {
        this.shoeName = shoeName;
    }

    public String getShoeBrandName() {
        return shoeBrandName;
    }

    public void setShoeBrandName(String shoeBrandName) {
        this.shoeBrandName = shoeBrandName;
    }

    public int getShoeImage() {
        return shoeImage;
    }

    public void setShoeImage(int shoeImage) {
        this.shoeImage = shoeImage;
    }

    public double getShoePrice() {
        return shoePrice;
    }

    public void setShoePrice(double shoePrice) {
        this.shoePrice = shoePrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(shoeName);
        parcel.writeString(shoeBrandName);
        parcel.writeInt(shoeImage);
        parcel.writeDouble(shoePrice);
    }
}
