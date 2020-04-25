package com.tohsoft.airquality.aqicnultis;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;

import static android.location.LocationManager.GPS_PROVIDER;
import static android.location.LocationManager.NETWORK_PROVIDER;

public class LocationHelper {
    public static String getProvider(Context ctx, LocationManager mLocMgr) {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Util.getGeoAccuracy(ctx));
        String mLocProvider = mLocMgr.getBestProvider(criteria, true);
        if (mLocProvider == null) {
            return null;
        }
        if (mLocProvider.compareToIgnoreCase(NETWORK_PROVIDER) == 0 && !mLocMgr.isProviderEnabled(NETWORK_PROVIDER)) {
            return null;
        } else if (mLocProvider.compareToIgnoreCase(GPS_PROVIDER) != 0 || mLocMgr.isProviderEnabled(GPS_PROVIDER)) {

            return mLocProvider;
        } else {
            return null;
        }
    }
}