package com.example.elijah.barcodetransfers;

import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import retrofit.client.OkClient;

/**
 * Created by Elijah on 7/3/2017.
 */

public class RestService {

    private static final String URL = "http://bbt.bigbrandtire.com/webApiTransfers/api/";
    private retrofit.RestAdapter restAdapter;
    private tireDataWarehouse apiService;
    private barcodes apiServices;
    private getTiresOnTruck apiServices2;

    public RestService()
    {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(2000, TimeUnit.MILLISECONDS);
        client.setReadTimeout(2000, TimeUnit.MILLISECONDS);
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                // try the request
                Response response = chain.proceed(request);

                int tryCount = 0;
                while (!response.isSuccessful() && tryCount < 3) {

                    Log.d("intercept", "Request is not successful - " + tryCount);

                    tryCount++;

                    // retry the request
                    response = chain.proceed(request);
                }

                // otherwise just pass the original response on
                return response;
            }
        });
        restAdapter = new retrofit.RestAdapter.Builder()
                .setEndpoint(URL)
                .setLogLevel(retrofit.RestAdapter.LogLevel.FULL)
                //.setClient(new OkClient(client))
                .build();

        apiService = restAdapter.create(tireDataWarehouse.class);
        apiServices = restAdapter.create(barcodes.class);
        apiServices2 = restAdapter.create(getTiresOnTruck.class);
    }


    public tireDataWarehouse getService()
    {
        return apiService;
    }
    public barcodes getServices()
    {
        return apiServices;
    }
    public getTiresOnTruck getServices2()
    {
        return apiServices2;
    }
}

