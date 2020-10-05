package com.example.spotified;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackItem implements Parcelable {

    @SerializedName("added_at")
    @Expose
    private String addedAt;
    @SerializedName("added_by")
    @Expose
    private AddedBy addedBy;
    @SerializedName("is_local")
    @Expose
    private Boolean isLocal;
    @SerializedName("track")
    @Expose
    private Track track;

    public String getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(String addedAt) {
        this.addedAt = addedAt;
    }

    public AddedBy getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(AddedBy addedBy) {
        this.addedBy = addedBy;
    }

    public Boolean getIsLocal() {
        return isLocal;
    }

    public void setIsLocal(Boolean isLocal) {
        this.isLocal = isLocal;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.addedAt);
        dest.writeParcelable(this.addedBy, flags);
        dest.writeValue(this.isLocal);
        dest.writeParcelable(this.track, flags);
    }

    public TrackItem() {
    }

    protected TrackItem(Parcel in) {
        this.addedAt = in.readString();
        this.addedBy = in.readParcelable(AddedBy.class.getClassLoader());
        this.isLocal = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.track = in.readParcelable(Track.class.getClassLoader());
    }

    public static final Parcelable.Creator<TrackItem> CREATOR = new Parcelable.Creator<TrackItem>() {
        @Override
        public TrackItem createFromParcel(Parcel source) {
            return new TrackItem(source);
        }

        @Override
        public TrackItem[] newArray(int size) {
            return new TrackItem[size];
        }
    };
}
