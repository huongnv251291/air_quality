
package com.tohsoft.airquality.data.models.aqicn;

import com.google.gson.annotations.SerializedName;

public class Station {

    @SerializedName("name")
    private String mName;
    @SerializedName("time")
    private String mTime;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

}
