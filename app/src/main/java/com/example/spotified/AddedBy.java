package com.example.spotified;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddedBy {

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

}