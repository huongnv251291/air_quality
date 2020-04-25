package com.tohsoft.airquality.data.models.aqicn;

import com.google.gson.Gson;

import org.json.JSONObject;

public class AqiOfCity {
    private final static String[] STRINGS = new String[]{"co", "dew", "h", "no2", "o3", "p", "pm25", "so2", "t", "w"};
    private String aqi="aqi";
    private City city;
    private String idx="idx";
    private String mCo, mDew, mHumidity, mNo2, mO3, mPressure, mPm25, mSo2, mTemp, mWind;

    public AqiOfCity() {
    }

    public void convert(JSONObject jsonObject) {
        jsonObject=jsonObject.optJSONObject("data");
        aqi = jsonObject.optString(aqi);
        idx = jsonObject.optString(idx);
        JSONObject cityJson = jsonObject.optJSONObject("city");
        if (cityJson != null) {
            city = new Gson().fromJson(cityJson.toString(), City.class);
        }
        JSONObject iaqi = jsonObject.optJSONObject("iaqi");
        if (iaqi != null) {
            mCo = getValueApi(iaqi, "co");
            mDew = getValueApi(iaqi, "dew");
            mHumidity = getValueApi(iaqi, "h");
            mNo2 = getValueApi(iaqi, "no2");
            mO3 = getValueApi(iaqi, "o3");
            mPressure = getValueApi(iaqi, "p");
            mPm25 = getValueApi(iaqi, "pm25");
            mSo2 = getValueApi(iaqi, "so2");
            mTemp = getValueApi(iaqi, "t");
            mWind = getValueApi(iaqi, "w");
        }
    }

    private String getValueApi(JSONObject iaqi, String key) {
        JSONObject jsonObject = iaqi.optJSONObject(key);
        if (jsonObject != null) {
            return jsonObject.optString("v");
        }
        return "";
    }

    @Override
    public String toString() {
        return "aqi : " + aqi + ".\n" +
                " city : " + city.getName() + ".\n" +
                " idx : " + idx + ".\n" +
                "Co : " + mCo + ".\n" +
                "Dew : " + mDew + ".\n" +
                "Humidity : " + mHumidity + ".\n" +
                "No2 : " + mNo2 + ".\n" +
                "O3 : " + mO3 + ".\n" +
                "Pressure : " + mPressure + ".\n" +
                "Pm25 : " + mPm25 + ".\n" +
                "So2 : " + mSo2 + ".\n" +
                "Temp : " + mTemp + ".\n" +
                "Wind : " + mWind + ".\n";
    }

}
