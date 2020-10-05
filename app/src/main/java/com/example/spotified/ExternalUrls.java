package com.example.spotified;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExternalUrls implements Parcelable {

    @SerializedName("spotify")
    @Expose
    private String spotify;

    public String getSpotify() {
        return spotify;
    }

    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.spotify);
    }

    public ExternalUrls() {
    }

    protected ExternalUrls(Parcel in) {
        this.spotify = in.readString();
    }

    public static final Parcelable.Creator<ExternalUrls> CREATOR = new Parcelable.Creator<ExternalUrls>() {
        @Override
        public ExternalUrls createFromParcel(Parcel source) {
            return new ExternalUrls(source);
        }

        @Override
        public ExternalUrls[] newArray(int size) {
            return new ExternalUrls[size];
        }
    };
}