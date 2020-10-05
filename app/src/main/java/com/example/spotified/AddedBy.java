package com.example.spotified;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddedBy implements Parcelable {

    @SerializedName("external_urls")
    @Expose
    private TrackExternalUrls externalUrls;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("uri")
    @Expose
    private String uri;

    public TrackExternalUrls getExternalUrls() {
        return externalUrls;
    }

    public void setExternalUrls(TrackExternalUrls externalUrls) {
        this.externalUrls = externalUrls;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.externalUrls, flags);
        dest.writeString(this.href);
        dest.writeString(this.id);
        dest.writeString(this.type);
        dest.writeString(this.uri);
    }

    public AddedBy() {
    }

    protected AddedBy(Parcel in) {
        this.externalUrls = in.readParcelable(TrackExternalUrls.class.getClassLoader());
        this.href = in.readString();
        this.id = in.readString();
        this.type = in.readString();
        this.uri = in.readString();
    }

    public static final Parcelable.Creator<AddedBy> CREATOR = new Parcelable.Creator<AddedBy>() {
        @Override
        public AddedBy createFromParcel(Parcel source) {
            return new AddedBy(source);
        }

        @Override
        public AddedBy[] newArray(int size) {
            return new AddedBy[size];
        }
    };
}