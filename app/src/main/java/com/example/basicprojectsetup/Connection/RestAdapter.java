package com.example.basicprojectsetup.Connection;

import com.example.basicprojectsetup.BuildConfig;
import com.example.basicprojectsetup.Utils.Constant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAdapter {

    public static API createAPI() {
        HttpLoggingInterceptor httpLogging = new HttpLoggingInterceptor();
        httpLogging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(1, TimeUnit.MINUTES);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(1, TimeUnit.MINUTES);

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLogging);
        }

        builder.cache(null);

        OkHttpClient okHttpClient = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WS.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(API.class);
    }

    public static API createAPIWithHeader() {

        HttpLoggingInterceptor httpLogging = new HttpLoggingInterceptor();
        httpLogging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder().addHeader(Constant.authorization, Constant.headerToken);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        builder.connectTimeout(1, TimeUnit.MINUTES);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(1, TimeUnit.MINUTES);

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLogging);
        }

        builder.cache(null);

        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WS.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(API.class);
    }


}
