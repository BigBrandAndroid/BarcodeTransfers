package com.example.elijah.barcodetransfers;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.os.FileObserver.DELETE;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
/**
 * Created by Elijah on 7/3/2017.
 */

public interface tireDataWarehouse {

    @GET("/wareHouseAndroidTransfers")
    public void getTire(Callback<List<tireData>> callback);


    //Get tire record base on ID
    @GET("/wareHouseAndroidTransfers/{id}")
    public void getTireById(@Path("id") Integer id,Callback<tireData> callback);


    //Delete tire record base on ID
    @DELETE("/wareHouseAndroidTransfers/{id}")
    public void deleteTireById(@Path("id") Integer id,Callback<tireData> callback);


    //PUT tire record and post content in HTTP request BODY
    @PUT("/wareHouseAndroidTransfers/{id}")
    public void updateTiretById(@Path("id") Integer id,@Body tireData _tireData,Callback<tireData> callback);


    //Add tire record and post content in HTTP request BODY

    @POST("/wareHouseAndroidTransfers")
    public void addTire(@Body tireData tires, Callback<tireData> callback);
}