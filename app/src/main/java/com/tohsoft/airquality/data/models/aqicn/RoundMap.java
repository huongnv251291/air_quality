
package com.tohsoft.airquality.data.models.aqicn;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RoundMap {

    @SerializedName("data")
    private List<Datum> mData;
    @SerializedName("status")
    private String mStatus;

    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> data) {
        mData = data;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    @Override
    public String toString() {
        return "RoundMap{" +
                "mData=" + mData +
                ", mStatus='" + mStatus + '\'' +
                '}';
    }
}
