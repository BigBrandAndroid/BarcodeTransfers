package com.example.elijah.barcodetransfers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Elijah on 7/4/2017.
 */

public class getTiresOnTrucks {
    public int id;
    public String Brand;
    public String PartNumber;
    public String Description;
    public String Quantity;
    public String ToStore;
    public String createDate;
    public String UpcCode;
    public int present;


    public getTiresOnTrucks(int id,String Brand,String PartNumber,String Description,String Quantity,String ToStore, String createDate,String barcode,int present){
        this.id = id;
        this.Brand = Brand;
        this.PartNumber = PartNumber;
        this.Description = Description;
        this.Quantity = Quantity;
        this.ToStore = ToStore;
        this.createDate = createDate;
        this.UpcCode = barcode;
        this.present = present;

    }
    public getTiresOnTrucks(){

    }

    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getPresent(){
        return present;
    }
    public void setPresent(int present) {
        this.present = present;
    }

//    public String getCode(){
//        return code;
//    }
//    public void setCode(String code) {
//        this.code = code;
//    }

    public String getBrand(){
        return Brand;
    }
    public void setBrand(String Brand) {
        this.Brand = Brand;
    }


    public String getBarcode23(){
        return UpcCode;
    }
    public void setBarcode23(String UpcCode) {
        this.UpcCode = UpcCode;
    }


    public String getPartNumber(){
        return PartNumber;
    }
    public void setPartNumber(String PartNumber) {
        this.PartNumber = PartNumber;
    }

    public String getDescription(){
        return Description;
    }
    public void setDescription(String Description) {this.Description = Description;}

    public String getQuantity(){
        return Quantity;
    }
    public void setQuantity(String Quantity) {this.Quantity = Quantity;}

    public String getToStore(){
        return ToStore;
    }
    public void setToStore(String ToStore) {this.ToStore = ToStore;}

    public String getCreateDate(){
        return createDate;
    }
    public void setCreateDate(String createDate) {this.createDate = createDate;}
}
