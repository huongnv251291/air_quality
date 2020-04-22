
package com.tohsoft.airquality.data.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("geo")
    private List<Double> mGeo;
    @SerializedName("name")
    private String mName;
    @SerializedName("url")
    private String mUrl;

    public List<Double> getGeo() {
        return mGeo;
    }

    public void setGeo(List<Double> geo) {
        mGeo = geo;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    public String toString() {
        return "City{" +
                "mGeo=" + mGeo +
                ", mName='" + mName + '\'' +
                ", mUrl='" + mUrl + '\'' +
                '}';
    }
}
