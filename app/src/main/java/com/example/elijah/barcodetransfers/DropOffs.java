package com.example.elijah.barcodetransfers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
public class DropOffs extends AppCompatActivity {
    DBHandlerDropOffs db;
    EditText edit;
    DBHandler d2;
    List<getTiresOnTrucks> tr;
    List<getTiresOnTrucks> trr;
    List<getTiresOnTrucks> trrr;
    List<getTiresOnTrucks> trrrr;
    List<getTiresOnTrucks> trrrrr;
    RestService restService;
    View view;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_drop_offs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Effects.getInstance().init(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        db = new DBHandlerDropOffs(DropOffs.this);
        d2 = new DBHandler(DropOffs.this);
        tr =  db.getAllDropOffs();
        restService = new RestService();
        lv = (ListView) findViewById(R.id.dropOffListView);
        DropOffAdapter dropOffs = new DropOffAdapter(DropOffs.this,R.layout.dropofflist,tr);
        lv.setAdapter(dropOffs);
        edit = (EditText) findViewById(R.id.barcodeGet);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (tr.get(position).getBarcode23().toString().trim().isEmpty()) {
                    LayoutInflater li = LayoutInflater.from(DropOffs.this);
                    View promptsView = li.inflate(R.layout.customdialogtextinput, null);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DropOffs.this);
                    alertDialogBuilder.setView(promptsView);

                    final EditText userInput = (EditText) promptsView
                            .findViewById(R.id.editTextDialogUserInput);
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            String s = userInput.getText().toString().trim();
                                            if (!s.isEmpty()) {
                                                int d = position;
                                                trrr = db.getAllDropOffs();
                                                getTiresOnTrucks ge = new getTiresOnTrucks();
                                                ge = trrr.get(position);
                                                ge.setBarcode23(userInput.getText().toString().trim());
                                                ge.setQuantity(Integer.toString((int) (Double.valueOf(ge.getQuantity()) - 1)));
                                                ge.setPresent(1);
                                                db.updateDropOffs(ge);
                                                trrrrr = db.getAllDropOffs();
                                                DropOffAdapter dropOffss = new DropOffAdapter(DropOffs.this, R.layout.dropofflist, trrrrr);
                                                lv.setAdapter(dropOffss);
                                            }

                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();

                }
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(final View view) {
                                       Intent inte = new Intent(DropOffs.this, SendingList.class);
                                       startActivity(inte);
                                   }
                               });
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        view = (View) findViewById(R.id.dropOffListView);
        try{
                    edit.requestFocus();
                    String s = edit.getText().toString();

            final getTiresOnTrucks  as = db.getDropOff(s);


                tireData ti = new tireData();
                ti.brand = as.getBrand();
                ti.description = as.getDescription();
                ti.qty = as.getQuantity();
                ti.toStore = as.getToStore();
                ti.partNo = as.getPartNumber();
                ti.barcode = as.getBarcode23();



                 edit.setText(" ");
            if(as.getBrand() != null && !s.trim().isEmpty() && s!=null) {
                if(Double.valueOf(as.getQuantity())>0.00){
                    Effects.getInstance().playSound(Effects.SOUND_1);
                    as.setPresent(as.getPresent()+1);
                    as.setQuantity(Integer.toString((int) (Double.valueOf(as.getQuantity()) - 1)));
                    db.updateDropOffs(as);

                    trrrr = db.getAllDropOffs();
                    DropOffAdapter dropOffs = new DropOffAdapter(DropOffs.this, R.layout.dropofflist, trrrr);
                    lv.setAdapter(dropOffs);
                }

                final getTiresOnTrucks fin = db.getDropOff(s);
                if(Integer.valueOf(fin.getQuantity() )== 10 ) {
                    restService.getService().addTire(ti, new Callback<tireData>() {
                        @Override
                        public void success(tireData tireData, Response response) {
                            Snackbar.make(view, "The Scanned Tires Have Been Sent", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
//                        Intent intent = new Intent(DropOffs.this, MainActivity.class);
//                        startActivity(intent);
                            db.deleteDropOffs(as);
                            trr = db.getAllDropOffs();
                            DropOffAdapter dropOffs = new DropOffAdapter(DropOffs.this, R.layout.dropofflist, trr);
                            lv.setAdapter(dropOffs);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Snackbar.make(view, "Not Today", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    });
                }
            }
                }catch(Exception e){

                }

        return super.onKeyDown(keyCode, event);
    }



}