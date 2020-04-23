package com.tohsoft.airquality.data;

public class Key {
    private static int locationKey = 0;
    public static final String[] breezometer_keys = new String[]{"65ab93b477664c19835cd0ed8516fbcb", "6e3e1b3f1bad4300bbde260b8c9ca533", "56e076df579e4f2585af13c16320ae94"};
    public static final String breezometer_key = "65ab93b477664c19835cd0ed8516fbcb";
    public static final String iqair_key = "623413dd-95d6-4fd0-821e-6423400b4bbc";


    public static String getKeyBreezometer(boolean changeKey) {
        if (changeKey) {
            locationKey++;
            if (locationKey >= breezometer_keys.length) {
                locationKey = 0;
            }
        }

        return breezometer_keys[locationKey];
    }

    public static String getKeyBreezometer() {
        return getKeyBreezometer(false);
    }
}
