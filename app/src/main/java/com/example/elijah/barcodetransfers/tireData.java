package com.example.elijah.barcodetransfers;

/**
 * Created by Elijah on 7/3/2017.
 */

public class tireData {

    public int id;
    public String barcode;
    public String brand;
    public String description;
    public String qty;
    public String toStore;
    public String partNo;



    public  tireData(){

    }
    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getBarcode(){
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getQty(){ return qty;}
    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand(){
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getToStore(){
        return toStore;
    }
    public void setToStore(String toStore) {
        this.toStore = toStore;
    }

    public String getPartno(){
        return partNo;
    }
    public void setPartno(String partNo) {
        this.partNo = partNo;
    }
}


