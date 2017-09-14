package com.example.elijah.barcodetransfers;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Elijah on 7/4/2017.
 */

public interface getTiresOnTruck {
    /**
     * Created by Elijah on 7/3/2017.
     */

        @GET("/GetTiresOnTruck")
        public void getTiresOnTruck(Callback<List<getTiresOnTrucks>> callback);


        //Get tire record base on ID
        @GET("/GetTiresOnTruck/{store}/{plate}")
        public void getTiresOnTruckById(@Path("store") String store,@Path("plate") String plate, Callback<List<getTiresOnTrucks>> callback);


        //Delete tire record base on ID
//        @DELETE("/GetTiresOnTruck/{id}")
//        public void deleteTiresOnTruckId(@Path("id") Integer id,Callback<getTiresOnTrucks> callback);
//
//
//        //PUT tire record and post content in HTTP request BODY
//        @PUT("/GetTiresOnTruck/{id}")
//        public void updateTiresOnTruckById(@Path("id") Integer id, @Body getTiresOnTrucks _tireData, Callback<getTiresOnTrucks> callback);
//
//
//        //Add tire record and post content in HTTP request BODY
//        @POST("/GetTiresOnTruck")
//        public void addTiresOnTruck(@Body getTiresOnTrucks _tireData,Callback<getTiresOnTrucks> callback);
    }


