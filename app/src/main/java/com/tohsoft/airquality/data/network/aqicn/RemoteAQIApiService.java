package com.tohsoft.airquality.data.network.aqicn;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tohsoft.airquality.data.models.aqicn.RoundMap;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Haku on 15/4/2020.
 */

public interface RemoteAQIApiService {
    //https://api.waqi.info/feed/HaNoi/?token=4ef577fe7f69a1706f1261ac805f54406dee219d
    //https://api.waqi.info/feed/geo:20.9968;105.8408/?token=4ef577fe7f69a1706f1261ac805f54406dee219d
    @GET("feed/{pathValue}/")
    Observable<ResponseBody> getFeed(@Path(value = "pathValue", encoded = false) String city, @Query(value = "token", encoded = false) String token);

    //    https://api.waqi.info/map/bounds/?token=4ef577fe7f69a1706f1261ac805f54406dee219d&latlng=20.9968,105.8408,23.301901,108.016402
    @GET("map/bounds/")
    Observable<RoundMap> getMapBound(@Query(value = "token", encoded = false) String token, @Query(value = "latlng", encoded = false) String latlngs);


    class Creator {
        private static final String ENDPOINT = "https://api.waqi.info/";

        public static Retrofit newRetrofitInstance() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(3, TimeUnit.SECONDS)
                    .readTimeout(3, TimeUnit.SECONDS);
            /**
             * Need if ENDPOINT is https.
             */
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1)
                        .cipherSuites(
                                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                                CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
                                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA)
                        .build();
                builder.connectionSpecs(Collections.singletonList(spec));
            }*/

            OkHttpClient client = builder.build();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:SSS'Z'")
                    .create();

            return new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();

        }

    }
}
