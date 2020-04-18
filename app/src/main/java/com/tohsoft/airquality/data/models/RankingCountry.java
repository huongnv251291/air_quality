
package com.tohsoft.airquality.data.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RankingCountry {

    @SerializedName("cities")
    private List<City> mCities;
    @SerializedName("time")
    private String mTime;
    @SerializedName("version")
    private Long mVersion;

    public List<City> getCities() {
        return mCities;
    }

    public void setCities(List<City> cities) {
        mCities = cities;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public Long getVersion() {
        return mVersion;
    }

    public void setVersion(Long version) {
        mVersion = version;
    }

}
