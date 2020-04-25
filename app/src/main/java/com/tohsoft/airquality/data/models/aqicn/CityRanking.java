
package com.tohsoft.airquality.data.models.aqicn;

import com.google.gson.annotations.SerializedName;

public class CityRanking {

    @SerializedName("city")
    private String mCity;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("station")
    private StationRanking mStation;

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public StationRanking getStation() {
        return mStation;
    }

    public void setStation(StationRanking station) {
        mStation = station;
    }

    @Override
    public String toString() {
        return " City:" + mCity + "\n" +
                "Country:" + mCountry + "\n" +
                "Station:" + mStation.toString() ;
    }
}
