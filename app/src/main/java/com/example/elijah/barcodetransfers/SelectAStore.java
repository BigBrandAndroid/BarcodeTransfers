package com.example.elijah.barcodetransfers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.media.CamcorderProfile.get;

/**
 * Created by Elijah on 7/3/2017.
 */

public class SelectAStore extends AppCompatActivity {
    RestService restService;
    final CharSequence[] items = {"02766TI","62053C2","8U66228","91482U1","6W00950","44806C2","5HMY565"};
    final CharSequence[] itemsss = {"951","952","953","954","955","956","957"};
    // arraylist to keep the selected items
    final ArrayList seletedItems=new ArrayList();
    String stre;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.listview);
        super.onCreate(savedInstanceState);
        final List<Store> st = new ArrayList<>();
        st.add(new Store("01","Santa Barbara"));
        st.add(new Store("02","Goleta"));
        st.add(new Store("03","Lompoc"));
        st.add(new Store("04","Thousand Oaks"));
        st.add(new Store("05","Camarillo"));
        st.add(new Store("06","Backers white ln"));
        st.add(new Store("07","Ventura"));
        st.add(new Store("08","Oxnard"));
        st.add(new Store("09","Bakers 21st"));
        st.add(new Store("10","Porterville"));
        st.add(new Store("11","Paso Robles"));
        st.add(new Store("12","Santa Maria"));
        st.add(new Store("13","San Luis Obispo"));
        st.add(new Store("14","Bakers Rosdale"));
        st.add(new Store("15","Tulare"));
        st.add(new Store("16","Atascadero"));
        st.add(new Store("17","Arroyo Grande"));
        st.add(new Store("18","Simi Valley"));
        st.add(new Store("19","Canoga Park"));
        restService = new RestService();
        ListView lv = (ListView) findViewById(R.id.storeslistView);
        StoreAdapter store = new StoreAdapter(SelectAStore.this,R.layout.storeslist,st);
        lv.setAdapter(store);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String num =  st.get(position).city.toString();

                Intent intent = getIntent();
                String extras = intent.getStringExtra("PICK_A_STORE").toString();
                AlertDialog dialog = new AlertDialog.Builder(SelectAStore.this)
                        .setTitle("Select The Truck Your In")
                        .setMultiChoiceItems(itemsss, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    seletedItems.add(indexSelected);
                                } else if (seletedItems.contains(indexSelected)) {
                                    // Else, if the item is already in the array, remove it
                                    seletedItems.remove(Integer.valueOf(indexSelected));
                                }
                            }
                        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String nu = seletedItems.get(0).toString();
                                stre = items[Integer.valueOf(nu)].toString();
                                Global.licensePlate = stre;
                                String s = Global.licensePlate;
                                restService.getServices2().getTiresOnTruckById(num, stre, new Callback<List<getTiresOnTrucks>>() {
                                    @Override
                                    public void success(List<getTiresOnTrucks> getTiresOnTrucksess, Response response) {
                                        DBHandlerDropOffs db = new DBHandlerDropOffs(SelectAStore.this);
                                        db.deleteAllRows();
                                        int i =1;
                                        for(getTiresOnTrucks tire: getTiresOnTrucksess){

                                            db.addDropOffs(new getTiresOnTrucks(i,tire.getBrand(),tire.getPartNumber(),tire.getDescription(),tire.getQuantity(),tire.getToStore(),tire.getCreateDate(),tire.getBarcode23()==null?"":tire.getBarcode23(),0));
                                            i++;
                                        }
                                        if(getTiresOnTrucksess.size() == 0) {
//                                            db.addDropOffs(new getTiresOnTrucks(1, "Hankook", "100010", "205/45ZR17 XL", "4", "05", "06/05/2017", 0,""));
//                                            db.addDropOffs(new getTiresOnTrucks(2, "Cooper", "100020", "205/45ZR17 XL", "4", "05", "06/05/2017", 0,""));
//                                            db.addDropOffs(new getTiresOnTrucks(3, "Bfg", "100030", "205/45ZR17 XL", "4", "05", "06/05/2017", 0,""));
//                                            db.addDropOffs(new getTiresOnTrucks(4, "Ricky Martin", "100040", "205/45ZR17 XL", "4", "05", "06/05/2017", 0,""));
//                                            db.addDropOffs(new getTiresOnTrucks(5, "Diamond", "1015410", "205/45ZR17 XL", "4", "05", "06/05/2017", 0,""));
//                                            db.addDropOffs(new getTiresOnTrucks(6, "Hankook", "10001020", "205/45ZR17 XL", "4", "05", "06/05/2017", 0,""));
//                                            db.addDropOffs(new getTiresOnTrucks(7, "Cooper", "10001020", "205/45ZR17 XL", "4", "05", "06/05/2017", 0,""));
//                                            db.addDropOffs(new getTiresOnTrucks(8, "Hankook", "10001020", "205/45ZR17 XL", "4", "05", "06/05/2017", 0,""));
//                                            db.addDropOffs(new getTiresOnTrucks(9, "Hankook", "10001020", "205/45ZR17 XL", "4", "05", "06/05/2017", 0,"644890323353"));
//                                            db.addDropOffs(new getTiresOnTrucks(10, "Hankook", "10001020", "205/45ZR17 XL", "4", "05", "06/05/2017", 0,"644890323353"));
//                                            db.addDropOffs(new getTiresOnTrucks(11, "Hankook", "10001020", "205/45ZR17 XL", "4", "05", "06/05/2017", 0,"644890323353"));
//                                            db.addDropOffs(new getTiresOnTrucks(12, "Cooper", "10001020", "205/45ZR17 XL", "4", "05", "06/05/2017", 0,"644890323353"));
//                                            db.addDropOffs(new getTiresOnTrucks(13, "Hankook", "10001020", "205/45ZR17 XL", "4", "05", "06/05/2017", 0,"644890323353"));
//                                            db.addDropOffs(new getTiresOnTrucks(14, "Hankook", "10001020", "205/45ZR17 XL", "4", "05", "06/05/2017", 0,"644890323353"));
//                                            db.addDropOffs(new getTiresOnTrucks(15, "Hank", "10001020", "205/45ZR17 XL", "4", "05", "06/05/2017", 0,"644890323353"));
                                        }
                                        Intent inte = new Intent(SelectAStore.this,DropOffs.class);
                                        startActivity(inte);
                                        Toast.makeText(SelectAStore.this, "The Drop Off Was Pulled Down", Toast.LENGTH_LONG).show();


                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        Toast.makeText(SelectAStore.this, "Try Again", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).create();
                dialog.show();



                }
//            }
        });
    }
}
