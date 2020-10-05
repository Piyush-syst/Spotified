package com.example.spotified;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item implements Parcelable {

    @SerializedName("collaborative")
    @Expose
    private Boolean collaborative;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("external_urls")
    @Expose
    private ExternalUrls externalUrls;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("primary_color")
    @Expose
    private Object primaryColor;
    @SerializedName("public")
    @Expose
    private Boolean _public;
    @SerializedName("snapshot_id")
    @Expose
    private String snapshotId;
    @SerializedName("tracks")
    @Expose
    private Tracks tracks;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("uri")
    @Expose
    private String uri;

    public Boolean getCollaborative() {
        return collaborative;
    }

    public void setCollaborative(Boolean collaborative) {
        this.collaborative = collaborative;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExternalUrls getExternalUrls() {
        return externalUrls;
    }

    public void setExternalUrls(ExternalUrls externalUrls) {
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Object getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(Object primaryColor) {
        this.primaryColor = primaryColor;
    }

    public Boolean getPublic() {
        return _public;
    }

    public void setPublic(Boolean _public) {
        this._public = _public;
    }

    public String getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
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
        dest.writeValue(this.collaborative);
        dest.writeString(this.description);
        dest.writeParcelable(this.externalUrls, flags);
        dest.writeString(this.href);
        dest.writeString(this.id);
        dest.writeTypedList(this.images);
        dest.writeString(this.name);
        dest.writeParcelable(this.owner, flags);
        dest.writeParcelable((Parcelable) this.primaryColor, flags);
        dest.writeValue(this._public);
        dest.writeString(this.snapshotId);
        dest.writeParcelable(this.tracks, flags);
        dest.writeString(this.type);
        dest.writeString(this.uri);
    }

    public Item() {
    }

    protected Item(Parcel in) {
        this.collaborative = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.description = in.readString();
        this.externalUrls = in.readParcelable(ExternalUrls.class.getClassLoader());
        this.href = in.readString();
        this.id = in.readString();
        this.images = in.createTypedArrayList(Image.CREATOR);
        this.name = in.readString();
        this.owner = in.readParcelable(Owner.class.getClassLoader());
        this.primaryColor = in.readParcelable(Object.class.getClassLoader());
        this._public = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.snapshotId = in.readString();
        this.tracks = in.readParcelable(Tracks.class.getClassLoader());
        this.type = in.readString();
        this.uri = in.readString();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}