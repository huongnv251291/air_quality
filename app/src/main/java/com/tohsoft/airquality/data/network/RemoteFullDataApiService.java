package com.tohsoft.airquality.data.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

public interface RemoteFullDataApiService {
    // có 2 v là v10.json data cơ bản và v11.json full data
    //    http://mapidroid.aqicn.org/aqicn/json/android/_C8tMLclLzNX3SMzLz9QPDXbNTUosLq4EAA/
    //    v11.json?
    //    cityID=Vietnam%2FHanoi%2FUSEmbassy
    //    &lang=vi
    //    &package=Asia
    //    &appv=1
    //    &appn=1.0
    //    &tz=25200000
    //    &metrics=1080,2049,3.0
//    &devid=f2f3ebf92e4e56d4
//    @GET("aqicn/json/android/{keyCity}/{versionApi}")
//    Observable<ResponseBody> fullData(@Path("keyCity") String tokenKeyCity,
//                                      @Path("versionApi") String pathValue,
//                                      @Query("cityID") String cityID,
//                                      @Query("lang") String lang,
//                                      @Query("package") String packageData,
//                                      @Query("appv") String appv,
//                                      @Query("appn") String appn,
//                                      @Query("tz") String tz,
//                                      @Query("metrics") String metrics,
//                                      @Query("devid") String devid);

    @GET("aqicn/json/android/{keyCity}/{versionApi}")
    Observable<ResponseBody> fullData(@Path(value = "keyCity", encoded = false) String tokenKeyCity,
                                      @Path(value = "versionApi", encoded = false) String pathValue,
                                      @Query(value = "cityID", encoded = false) String cityID,
                                      @Query(value = "lang", encoded = false) String lang,
                                      @Query(value = "package", encoded = false) String packageData,
                                      @Query(value = "appv", encoded = false) String appv,
                                      @Query(value = "appn", encoded = false) String appn,
                                      @Query(value = "tz", encoded = false) String tz,
                                      @Query(value = "metrics", encoded = false) String metrics);

    class Creator {
        private static final String UA = "aqicn-android-app-132-85685-Asia";

        public static String getUserAgent() {
            try {
                return (UA + "-" + Locale.getDefault().getLanguage()) + " " + System.getProperty("http.agent");
            } catch (Exception e) {
                return UA;
            }
        }

        private static final String ENDPOINT = "http://mapidroid.aqicn.org/";

        public static Retrofit newRetrofitInstance() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(new Interceptor() {
                        @NotNull
                        @Override
                        public Response intercept(@NotNull Chain chain) throws IOException {
                            Request original = chain.request();
                            Request request = original.newBuilder()
                                    .header("http.useragent", getUserAgent())
                                    .method(original.method(), original.body())
                                    .build();
                            return chain.proceed(request);
                        }
                    })
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
