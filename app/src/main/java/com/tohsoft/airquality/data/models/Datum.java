
package com.tohsoft.airquality.data.models;

import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("aqi")
    private String mAqi;
    @SerializedName("lat")
    private Double mLat;
    @SerializedName("lon")
    private Double mLon;
    @SerializedName("station")
    private Station mStation;
    @SerializedName("uid")
    private Long mUid;

    public String getAqi() {
        return mAqi;
    }

    public void setAqi(String aqi) {
        mAqi = aqi;
    }

    public Double getLat() {
        return mLat;
    }

    public void setLat(Double lat) {
        mLat = lat;
    }

    public Double getLon() {
        return mLon;
    }

    public void setLon(Double lon) {
        mLon = lon;
    }

    public Station getStation() {
        return mStation;
    }

    public void setStation(Station station) {
        mStation = station;
    }

    public Long getUid() {
        return mUid;
    }

    public void setUid(Long uid) {
        mUid = uid;
    }

    @Override
    public String toString() {
        return "Datum{" +
                "mAqi='" + mAqi + '\'' +
                ", mLat=" + mLat +
                ", mLon=" + mLon +
                ", mStation=" + mStation +
                ", mUid=" + mUid +
                '}';
    }
}
