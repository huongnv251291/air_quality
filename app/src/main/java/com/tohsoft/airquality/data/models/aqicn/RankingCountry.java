
package com.tohsoft.airquality.data.models.aqicn;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RankingCountry {

    @SerializedName("cities")
    private List<CityRanking> mCities;
    @SerializedName("time")
    private String mTime;
    @SerializedName("version")
    private Long mVersion;

    public List<CityRanking> getCities() {
        return mCities;
    }

    public void setCities(List<CityRanking> cities) {
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

    @Override
    public String toString() {
        return "RankingCountry{" +
                "mCities=" + mCities +
                ", mTime='" + mTime + '\'' +
                ", mVersion=" + mVersion +
                '}';
    }
}
