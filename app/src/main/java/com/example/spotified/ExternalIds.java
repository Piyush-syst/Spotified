package com.example.spotified;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExternalIds implements Parcelable {

    @SerializedName("isrc")
    @Expose
    private String isrc;

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.isrc);
    }

    public ExternalIds() {
    }

    protected ExternalIds(Parcel in) {
        this.isrc = in.readString();
    }

    public static final Parcelable.Creator<ExternalIds> CREATOR = new Parcelable.Creator<ExternalIds>() {
        @Override
        public ExternalIds createFromParcel(Parcel source) {
            return new ExternalIds(source);
        }

        @Override
        public ExternalIds[] newArray(int size) {
            return new ExternalIds[size];
        }
    };
}