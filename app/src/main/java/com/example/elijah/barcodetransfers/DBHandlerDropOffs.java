package com.example.elijah.barcodetransfers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elijah on 7/4/2017.
 */

public class DBHandlerDropOffs extends SQLiteOpenHelper {
    private SQLiteDatabase xe;
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "AndroidTransfersDropOffs";
    // Contacts table name
    private static final String TABLE_DROP_OFFS = "dropOffs";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_BRAND = "Brand";
    private static final String KEY_PART_NUMBER = "PartNumber";
    private static final String KEY_DESCRIPTION = "Description";
    private static final String KEY_QUANTITY = "Quantity";
    private static final String KEY_TO_STORE = "ToStore";
    private static final String KEY_CREATE_DATE = "createDate";
    private static final String KEY_CREATE_PRESENT = "present";
    private static final String KEY_CREATE_BARCODE = "barcod";
    public DBHandlerDropOffs(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DROPOFF_TABLE = "CREATE TABLE " + TABLE_DROP_OFFS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_BRAND + " TEXT," + KEY_PART_NUMBER + " TEXT,"+ KEY_DESCRIPTION + " TEXT," + KEY_QUANTITY + " TEXT," + KEY_TO_STORE + " TEXT,"+ KEY_CREATE_DATE + " TEXT,"+ KEY_CREATE_PRESENT + " TEXT,"+ KEY_CREATE_BARCODE + " TEXT)";
        db.execSQL(CREATE_DROPOFF_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DROP_OFFS);
        // Creating tables again
        onCreate(db);
    }
    public void deleteAllRows(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("dropOffs",null,null);
    }
    public void addDropOffs(getTiresOnTrucks incomingLoads) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID , incomingLoads.getId());
        values.put(KEY_BRAND , incomingLoads.getBrand());
        values.put(KEY_PART_NUMBER , incomingLoads.getPartNumber());
        values.put(KEY_DESCRIPTION , incomingLoads.getDescription());
        values.put(KEY_QUANTITY  , incomingLoads.getQuantity());
        values.put(KEY_TO_STORE  , incomingLoads.getToStore());
        values.put(KEY_CREATE_DATE  , incomingLoads.getCreateDate());
        values.put(KEY_CREATE_PRESENT  , incomingLoads.getPresent());
        values.put(KEY_CREATE_BARCODE  , incomingLoads.getBarcode23());


        db.insert(TABLE_DROP_OFFS, null, values);
        db.close();
    }
    public getTiresOnTrucks getDropOff(String barcode){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM dropOffs WHERE TRIM(barcod) = '"+barcode.trim()+"'", null);
        getTiresOnTrucks tire = new getTiresOnTrucks();
        if (c.moveToFirst()) {
            do {

                tire.setId(Integer.parseInt(c.getString(0)));
                tire.setBrand(c.getString(1));
                tire.setPartNumber(c.getString(2));
                tire.setDescription(c.getString(3));
                tire.setQuantity(c.getString(4));
                tire.setToStore(c.getString(5));
                tire.setCreateDate(c.getString(6));
                tire.setPresent(Integer.valueOf(c.getString(7)));
                tire.setBarcode23(c.getString(8));

// Adding contact to list

            } while (c.moveToNext());
        }


// return shop
        return tire;
    }
    public getTiresOnTrucks getDropOffs(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DROP_OFFS, new String[]{KEY_ID,
                        KEY_BRAND , KEY_PART_NUMBER ,KEY_DESCRIPTION ,KEY_QUANTITY ,KEY_TO_STORE ,KEY_CREATE_DATE,KEY_CREATE_PRESENT }, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        getTiresOnTrucks tire = new getTiresOnTrucks();
// return shop
        return tire;
    }
    public List<getTiresOnTrucks> getAllDropOffs() {
        List<getTiresOnTrucks> shopList = new ArrayList<getTiresOnTrucks>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_DROP_OFFS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                getTiresOnTrucks tire = new getTiresOnTrucks();
                tire.setId(Integer.parseInt(cursor.getString(0)));
                tire.setBrand(cursor.getString(1));
                tire.setPartNumber(cursor.getString(2));
                tire.setDescription(cursor.getString(3));
                tire.setQuantity(cursor.getString(4));
                tire.setToStore(cursor.getString(5));
                tire.setCreateDate(cursor.getString(6));
                tire.setPresent(Integer.valueOf(cursor.getString(7)));
                tire.setBarcode23(cursor.getString(8));

// Adding contact to list
                shopList.add(tire);
            } while (cursor.moveToNext());
        }

// return contact list
        return shopList;
    }
    // Getting shops Count
    public int getDropOffsCount() {
        String countQuery = "SELECT * FROM " + TABLE_DROP_OFFS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

// return count
        return cursor.getCount();
    }
    // Updating a shop
    public int updateDropOffs(getTiresOnTrucks incomingLoads) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID , incomingLoads.getId());
        values.put(KEY_BRAND , incomingLoads.getBrand());
        values.put(KEY_PART_NUMBER , incomingLoads.getPartNumber());
        values.put(KEY_DESCRIPTION , incomingLoads.getDescription());
        values.put(KEY_QUANTITY  , incomingLoads.getQuantity());
        values.put(KEY_TO_STORE  , incomingLoads.getToStore());
        values.put(KEY_CREATE_DATE  , incomingLoads.getCreateDate());
        values.put(KEY_CREATE_PRESENT  , incomingLoads.getPresent());
        values.put(KEY_CREATE_BARCODE  , incomingLoads.getBarcode23());

// updating row
        return db.update(TABLE_DROP_OFFS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(incomingLoads.getId())});
    }

    // Deleting a shop
    public void deleteDropOffs(getTiresOnTrucks incomingLoads) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DROP_OFFS, KEY_ID + " = ?",
                new String[] { String.valueOf(incomingLoads.getId()) });
        db.close();
    }
}
