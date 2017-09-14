package com.example.elijah.barcodetransfers;

/**
 * Created by Elijah on 7/3/2017.
 */

public class IncomingLoads {
    public int id;
    public String model;
    public String brand;
    public String size;
    public String partno;
    public String barcode;
    public String binLocation;
    public IncomingLoads(){

    }
    public IncomingLoads(String brand,String size,String partno,String barcode,String binLoc, String model){
        this.model = model;
        this.brand = brand;
        this.size = size;
        this.partno = partno;
        this.barcode = barcode;
        this.binLocation = binLoc;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setModel(String model) {
        this.model = model;
    }
    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {this.brand = brand;}

    public void setSize(String size) {
        this.size = size;
    }
    public String getSize() {
        return size;
    }

    public String getPartno() {return partno; }
    public void setPartno(String partno) {this.partno = partno;}


    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }


    public String getBinLocation() {
        return binLocation;
    }
    public void setBinLocation(String binLocation) {
        this.binLocation = binLocation;
    }
}