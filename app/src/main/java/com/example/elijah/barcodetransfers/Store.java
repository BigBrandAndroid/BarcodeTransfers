package com.example.elijah.barcodetransfers;

/**
 * Created by Elijah on 7/3/2017.
 */

public class Store {
    public String city;
    public String storenumber;
    public Store(){

    }
    public  Store(String s, String num){
        this.city = s;
        this.storenumber = num;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getStorenumber(){ return storenumber;}
    public void setStorenumber(String storenumber) {
        this.storenumber = storenumber;
    }

}
