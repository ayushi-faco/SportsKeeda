package com.kayushi07.sportskeeda;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    private static RetroClient instance = null;
    private Api myApi;

    private RetroClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(Api.class);
    }

    public static synchronized RetroClient getInstance() {
        if (instance == null) {
            instance = new RetroClient();
        }
        return instance;
    }

    public Api getMyApi() {
        return myApi;
    }
}
