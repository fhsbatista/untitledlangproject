package com.untitledlangproject.dao;

import android.os.Parcel;
import android.os.Parcelable;

public class FlashCard extends WordItem implements Parcelable {

    private String translation;

    public FlashCard(String text, int timesOfUsage, String translation) {
        super(text, timesOfUsage);
        this.translation = translation;
    }

    public FlashCard(Parcel in){
        super(in.readString(), in.readInt());
        this.translation = in.readString();
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.translation);
    }

    public static final Parcelable.Creator<FlashCard> CREATOR = new Parcelable.Creator<FlashCard>()
    {
        public FlashCard createFromParcel(Parcel in)
        {
            return new FlashCard(in);
        }
        public FlashCard[] newArray(int size)
        {
            return new FlashCard[size];
        }
    };

}
