package com.smitdesai16.androidrestapicallsretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    private static final String baseURL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();

    public static <S> S buildService(Class<S> serviceType) {
        return retrofit.create(serviceType);
    }
}
