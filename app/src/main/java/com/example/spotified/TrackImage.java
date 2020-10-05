package com.example.spotified;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackImage implements Parcelable {

    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("width")
    @Expose
    private Integer width;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.height);
        dest.writeString(this.url);
        dest.writeValue(this.width);
    }

    public TrackImage() {
    }

    protected TrackImage(Parcel in) {
        this.height = (Integer) in.readValue(Integer.class.getClassLoader());
        this.url = in.readString();
        this.width = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<TrackImage> CREATOR = new Parcelable.Creator<TrackImage>() {
        @Override
        public TrackImage createFromParcel(Parcel source) {
            return new TrackImage(source);
        }

        @Override
        public TrackImage[] newArray(int size) {
            return new TrackImage[size];
        }
    };
}