package com.example.elijah.barcodetransfers;

/**
 * Created by Elijah on 7/3/2017.
 */

public class barcode {
    public int id;
    public String UPC;
    public String S_Code;
    public String Size;

    public  barcode( ){

    }
    public  barcode(String barcode, String partno, String size ){
        this.UPC = barcode;
        this.S_Code = partno;
        this.Size = size;
    }

    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getPartno(){
        return S_Code;
    }

    public void setPartno(String S_Code) {
        this.S_Code = S_Code;
    }

    public String getBarcode(){
        return UPC;
    }
    public void setBarcode(String UPC) {
        this.UPC = UPC;
    }

    public String getSize(){
        return Size;
    }
    public void setSize(String Size) {
        this.Size = Size;
    }
}
