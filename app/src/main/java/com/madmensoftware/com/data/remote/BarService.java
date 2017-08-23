package com.madmensoftware.com.data.remote;

import com.madmensoftware.com.data.model.response.Bar;

import io.reactivex.Observable;

public interface BarService {

//    @GET("pokemon")
//    Single<BarListResponse> getBarList(@Query("limit") int limit);
//
//    @GET("pokemon/{name}")
//    Single<Bar> getBar(@Path("name") String name);

    Observable<Bar> getBarList(int limit);

    Observable<Bar> getBar(String name);

//
//    Bar getBar(String objectId);

//    @GET("classes/Bar")
//    Single<List<Bar>> getBarList(
//            @Header("X-Parse-Application-Id") String appId,
//            @Header("X-Parse-REST-API-Key") String apiKey,
//            @Header("Content-Type") String contentType
//    );
//
//    @GET("classes/Bar/{objectId}")
//    Single<Bar> getBar(
//            @Path("objectId") String objectId,
//            @Header("X-Parse-Application-Id") String appId,
//            @Header("X-Parse-REST-API-Key") String apiKey,
//            @Header("Content-Type") String contentType
//    );

}
