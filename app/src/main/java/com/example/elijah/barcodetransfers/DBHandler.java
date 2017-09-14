package com.example.elijah.barcodetransfers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

/**
 * Created by Elijah on 7/3/2017.
 */

public class DBHandler  extends SQLiteOpenHelper {
    private SQLiteDatabase xe;
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "AndroidTransfers";
    // Contacts table name
    private static final String TABLE_BARCODES = "barcodes";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_SIZE = "Size";
    private static final String KEY_PARTNO = "S_Code";
    private static final String KEY_BARCODE = "UPC";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_BARCODES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SIZE + " TEXT," + KEY_PARTNO + " TEXT,"+ KEY_BARCODE + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BARCODES);
        // Creating tables again
        onCreate(db);
    }
    public void deleteAllRows(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("barcodes",null,null);
    }
    public void addBarcodes(barcode incomingLoads) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SIZE, incomingLoads.getSize()); // Shop Name
        values.put(KEY_PARTNO, incomingLoads.getPartno()); // Shop Phone Number
        values.put(KEY_BARCODE, incomingLoads.getBarcode()); // Shop Name



        db.insert(TABLE_BARCODES, null, values);
        db.close();
    }
    public barcode getBarcodes2(String barcodes){
        SQLiteDatabase db = this.getReadableDatabase();
        barcode tire = new barcode();
        Cursor c = db.rawQuery("SELECT * FROM barcodes WHERE TRIM(UPC) = '"+barcodes.trim()+"'", null);
        if (c.moveToFirst()) {
            do {
                tire.setId(Integer.parseInt(c.getString(0)));
                tire.setSize(c.getString(1));
                tire.setPartno(c.getString(2));
                tire.setBarcode(c.getString(3));

// Adding contact to list

            } while (c.moveToNext());
        }


// return shop
        return tire;
    }
    public barcode getBarcodes(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BARCODES, new String[]{KEY_ID,
                        KEY_SIZE, KEY_PARTNO,KEY_BARCODE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        barcode tire = new barcode();
// return shop
        return tire;
    }
    public List<barcode> getAllBarcodes() {
        List<barcode> shopList = new ArrayList<barcode>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_BARCODES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                barcode tire = new barcode();
                tire.setId(Integer.parseInt(cursor.getString(0)));
                tire.setSize(cursor.getString(1));
                tire.setPartno(cursor.getString(2));
                tire.setBarcode(cursor.getString(3));

// Adding contact to list
                shopList.add(tire);
            } while (cursor.moveToNext());
        }

// return contact list
        return shopList;
    }
    // Getting shops Count
    public int getBarcodesCount() {
        String countQuery = "SELECT * FROM " + TABLE_BARCODES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

// return count
        return cursor.getCount();
    }
    // Updating a shop
    public int updateBarcodes(barcode incomingLoads) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PARTNO, incomingLoads.getPartno()); // Shop Phone Number
        values.put(KEY_BARCODE, incomingLoads.getBarcode()); // Shop Name


// updating row
        return db.update(TABLE_BARCODES, values, KEY_ID + " = ?",
                new String[]{String.valueOf(incomingLoads.getId())});
    }

    // Deleting a shop
    public void deleteBarcodes(barcode incomingLoads) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BARCODES, KEY_ID + " = ?",
                new String[] { String.valueOf(incomingLoads.getId()) });
        db.close();
    }
}
