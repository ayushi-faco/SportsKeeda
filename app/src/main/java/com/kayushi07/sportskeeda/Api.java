package com.kayushi07.sportskeeda;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://s3.ap-southeast-1.amazonaws.com/";
    @GET("data.sportskeeda.com/app-sample/v1.json")
    Call<JsonElement> getsuperCards();
//    Call<List<Cards>> getsuperHeroes();
}
