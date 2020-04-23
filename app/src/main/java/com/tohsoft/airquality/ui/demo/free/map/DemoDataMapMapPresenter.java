package com.tohsoft.airquality.ui.demo.free.map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Location;
import android.location.LocationManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tohsoft.airquality.R;
import com.tohsoft.airquality.data.ApplicationModules;
import com.tohsoft.airquality.data.models.Datum;
import com.tohsoft.airquality.data.models.RoundMap;
import com.tohsoft.airquality.data.network.DataManager;
import com.tohsoft.airquality.ui.base.BasePresenter;

import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DemoDataMapMapPresenter<V extends DemoDataMapMvpView> extends BasePresenter<V> implements DemoDataMapMvpPresenter<V> {
    DataManager dataManager;
    protected GoogleMap mMap;

    public DemoDataMapMapPresenter(Context context) {
        super(context);
        dataManager = ApplicationModules.getInstant().getDataManager();
    }

    @Override
    public void loadData() {


//        20.9968,105.8408,41.771312,118.285797
        Location a = new Location(LocationManager.GPS_PROVIDER);
        a.setLatitude(20.9968);
        a.setLongitude(105.8408);
        updateLocation(new LatLng(20.9968,105.8408));
        Location b = new Location(LocationManager.GPS_PROVIDER);
        b.setLatitude(41.771312);
        b.setLongitude(118.285797);
        mCompositeDisposable.add(dataManager.getMapRound(a,b).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<RoundMap>() {
            @Override
            public void accept(RoundMap responseBody) throws Exception {
                for (Datum datum : responseBody.getData()) {
                    MarkerOptions markerOptions = new MarkerOptions();
                    LatLng latLng = new LatLng(datum.getLat(), datum.getLon());
                    markerOptions.position(latLng);
                    markerOptions.title(datum.getAqi());
                    mMap.addMarker(markerOptions);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        }));
    }
    protected void updateLocation(LatLng toLatLng) {
        CameraPosition camPos = new CameraPosition.Builder()
                .target(toLatLng)
                .zoom(10)
                .build();
        CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
        mMap.moveCamera(camUpd3);
        mMap.animateCamera(camUpd3);
    }
    public static Bitmap createMaker(Context context, String _name) {
        View marker =
                ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
                        R.layout.custom_marker_layout, null);
        AppCompatTextView appCompatTextView = marker.findViewById(R.id.num_aqi);
        appCompatTextView.setText(_name);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        marker.setLayoutParams(new ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT));
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        marker.draw(canvas);

        return bitmap;
    }

    @Override
    public void setMap(GoogleMap googleMap) {
        mMap = googleMap;
    }

    private boolean requestFail(JSONObject jsonObject) {
        return TextUtils.equals(jsonObject.optString("data", ""), "error");
    }
}
