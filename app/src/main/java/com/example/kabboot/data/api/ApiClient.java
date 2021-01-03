package com.example.kabboot.data.api;



import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://barakatravel.net/api/";
    public static Retrofit retrofit = null;

    public static ApiServices getApiClient(){

        if(retrofit == null){
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
//                    .callTimeout(2, TimeUnit.MINUTES)
                    .connectTimeout(200, TimeUnit.SECONDS)
                    .readTimeout(150, TimeUnit.SECONDS)
                    .writeTimeout(150, TimeUnit.SECONDS);
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build());

             retrofit = builder.build();
        }
        return retrofit.create(ApiServices.class);
    }
}



//    Retrofit.Builder builder = new Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(SimpleXmlConverterFactory.create());



