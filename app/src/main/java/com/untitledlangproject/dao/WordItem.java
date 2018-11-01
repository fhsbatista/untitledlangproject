package com.untitledlangproject.dao;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class WordItem extends Word implements Comparable, Parcelable {

    private int numberOfUsage;

    public WordItem(String text, int timesOfUsage) {
        super(text);
        this.numberOfUsage = timesOfUsage;
    }

    public WordItem(Parcel in){
        super(in.readString());
        this.numberOfUsage = in.readInt();
    }

    public int getNumberOfUsage() {
        return numberOfUsage;
    }


    public void setNumberOfUsage(int numberOfUsage) {
        this.numberOfUsage = numberOfUsage;
    }


    @Override
    public int compareTo(@NonNull Object o) {

//        return this.getNumberOfUsage() < ((WordItem) o).getNumberOfUsage() ? 1 : -1;
        if(this.getNumberOfUsage() < ((WordItem)o).getNumberOfUsage()){
            return 1;
        } else if(this.getNumberOfUsage() > ((WordItem)o).getNumberOfUsage()){
            return -1;
        } else{
            return 0;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.getText());
        dest.writeInt(this.numberOfUsage);
    }

    public static final Parcelable.Creator<WordItem> CREATOR = new Parcelable.Creator<WordItem>()
    {
        public WordItem createFromParcel(Parcel in)
        {
            return new WordItem(in);
        }
        public WordItem[] newArray(int size)
        {
            return new WordItem[size];
        }
    };

    @Override
    public String toString() {
        return getText();
    }
}
