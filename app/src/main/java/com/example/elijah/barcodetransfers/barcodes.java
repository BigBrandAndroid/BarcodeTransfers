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
 * Created by Elijah on 7/3/2017.
 */

public interface barcodes {
    @GET("/Barcodes")
    public void getBarcodes(Callback<List<barcode>> callback);


    //Get tire record base on ID
    @GET("/Barcodes/{id}")
    public void getBarcodesById(@Path("id") Integer id, Callback<barcode> callback);


    //Delete tire record base on ID
    @DELETE("/Barcodes/{id}")
    public void deleteBarcodesId(@Path("id") Integer id,Callback<barcode> callback);


    //PUT tire record and post content in HTTP request BODY
    @PUT("/Barcodes/{id}")
    public void updateBarcodesById(@Path("id") Integer id, @Body barcode _tireData, Callback<barcode> callback);


    //Add tire record and post content in HTTP request BODY
    @POST("/Barcodes")
    public void addBarcodes(@Body barcode _tireData,Callback<barcode> callback);
}
