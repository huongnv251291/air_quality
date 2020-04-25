package com.tohsoft.airquality.aqicnultis;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.RequiresPermission;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

public class TimeoutableLocationListener implements LocationListener {
    private LocationTimeoutListener mCallback = null;
    private LocationManager mLocMgr = null;
    private String mLocProvider = null;
    private Timer mTimeoutTimer = null;
    private String mUpdatedLocation = null;

    public interface LocationTimeoutListener {
        void onGeolocDone(GeoLocInfo geoLocInfo);
    }

    public static class GeoLocInfo {
        public static final int NOPROVIDER = 2;
        public static final int RESOLUTIONONGOING = 3;
        public static final int TIMEOUT = 1;
        private int mErroStringID = 0;
        private String mGeo = null;
        private Boolean mValid = false;

        private GeoLocInfo() {
        }

        public static GeoLocInfo createError(int errorStringID) {
            GeoLocInfo g = new GeoLocInfo();
            g.mErroStringID = errorStringID;
            return g;
        }

        public GeoLocInfo(String geo) {
            if (geo != null) {
                this.mValid = true;
                this.mGeo = geo;
            }
        }

        public boolean isValid() {
            return this.mValid.booleanValue();
        }

        public String getGeo() {
            return this.mGeo;
        }

        public int getErrorStringID() {
            return this.mErroStringID;
        }
    }

    @RequiresPermission(allOf = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    public boolean startGeoLoc(Context ctx, LocationTimeoutListener l) {
        this.mCallback = l;
        try {
            this.mLocMgr = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
            this.mLocProvider = LocationHelper.getProvider(ctx, this.mLocMgr);
            if (this.mLocProvider != null) {
                this.mLocMgr.requestLocationUpdates("network", 1000, 100.0f, this);
                this.mTimeoutTimer = new Timer();
                this.mTimeoutTimer.schedule(new TimerTask() {
                    public void run() {
                        TimeoutableLocationListener.this.stopLocationUpdateAndTimer();
                        GeoLocInfo info = GeoLocInfo.createError(1);
                        if (TimeoutableLocationListener.this.mUpdatedLocation != null) {
                            info = new GeoLocInfo(TimeoutableLocationListener.this.mUpdatedLocation);
                        }
                        TimeoutableLocationListener.this.mCallback.onGeolocDone(info);
                    }
                }, 10000);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void stopLocationUpdateAndTimer() {
        this.mLocMgr.removeUpdates(this);
        if (this.mTimeoutTimer != null) {
            this.mTimeoutTimer.cancel();
            this.mTimeoutTimer.purge();
            this.mTimeoutTimer = null;
        }
    }

    public static String getLocString(Location location, String mLocProvider) {
        String s = "1/" + location.getLatitude() + "/" + location.getLongitude() + "/" + location.getAccuracy() + "/";
        try {
            return s + URLEncoder.encode(mLocProvider, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        this.mUpdatedLocation = getLocString(location, mLocProvider);
        stopLocationUpdateAndTimer();
        this.mCallback.onGeolocDone(new GeoLocInfo(this.mUpdatedLocation));
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}