package com.example.locktrust.network;


import com.example.locktrust.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mayur on 15-12-2017.
 */

public class RetrofitClient {

    private static Retrofit retrofit = null;
    public static OkHttpClient getHttpClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //TODO : remove logging interceptors as it is to be used for development purpose
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS).
                        addInterceptor(logging).
                        build();

        return client;
    }
    public static Retrofit getClient() {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://beta.acetute.com/api/")
                    .client(getHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
