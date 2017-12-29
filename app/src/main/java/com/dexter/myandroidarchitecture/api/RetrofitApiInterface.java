package com.dexter.myandroidarchitecture.api;

import java.util.HashMap;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Admin on 29-12-2017.
 */

public interface RetrofitApiInterface {

    //TODO ADD API METHODS HERE

    @GET("/")
    Flowable<ExampleResponse> getMovies(@QueryMap HashMap<String, String> map);
}
