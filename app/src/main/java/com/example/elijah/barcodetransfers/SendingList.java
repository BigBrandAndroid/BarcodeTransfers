package com.example.elijah.barcodetransfers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SendingList extends AppCompatActivity {

    private TextView mTextMessage;
    DBHandlerDropOffs db;
    List<getTiresOnTrucks> tr;
    RestService restService;
    ListView lv;
    List<getTiresOnTrucks> goodList;
    View view;
    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_list);
        db = new DBHandlerDropOffs(SendingList.this);
        tr =  db.getAllDropOffs();
        restService = new RestService();
        lv = (ListView) findViewById(R.id.dropOffListView);
        goodList = new ArrayList<getTiresOnTrucks>();
        for(getTiresOnTrucks ti : tr){
            if(ti.getPresent() > 0){
                getTiresOnTrucks goLis = new getTiresOnTrucks();
                goLis = ti;
                goodList.add(goLis);
            }

        }
        SendingListAdapter dropOffs = new SendingListAdapter(SendingList.this,R.layout.dropofflist,goodList);
        lv.setAdapter(dropOffs);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            view = (View) findViewById(R.id.dropOffListView);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent ints = new Intent(SendingList.this,MainActivity.class);
                    startActivity(ints);
                    break;
                case R.id.backToList:
                    onBackPressed();
                    break;
                case R.id.send:
                    navigation.setVisibility(View.GONE);

                    for(getTiresOnTrucks tirz: goodList) {
                        tireData ti = new tireData();
                        ti.brand = tirz.getBrand();
                        ti.description = tirz.getDescription();
                        ti.qty = Integer.toString(tirz.getPresent());
                        ti.toStore = tirz.getToStore();
                        ti.partNo = tirz.getPartNumber();
                        ti.barcode = tirz.getBarcode23();
                        restService.getService().addTire(ti, new Callback<tireData>() {
                            @Override
                            public void success(tireData tireData, Response response) {
                                Snackbar.make(view, "The Scanned Tires Have Been Sent", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            Intent intent = new Intent(SendingList.this, MainActivity.class);
                            startActivity(intent);
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                navigation.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                   break;
            }
            return false;
        }

    };

}
