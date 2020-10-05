package com.example.spotified;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackExternalUrls implements Parcelable {

    @SerializedName("spotify")
    @Expose
    private String spotify;

    public String getSpotify() {
        return spotify;
    }

    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }

    public TrackExternalUrls() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.spotify);
    }

    protected TrackExternalUrls(Parcel in) {
        this.spotify = in.readString();
    }

    public static final Creator<TrackExternalUrls> CREATOR = new Creator<TrackExternalUrls>() {
        @Override
        public TrackExternalUrls createFromParcel(Parcel source) {
            return new TrackExternalUrls(source);
        }

        @Override
        public TrackExternalUrls[] newArray(int size) {
            return new TrackExternalUrls[size];
        }
    };
}
