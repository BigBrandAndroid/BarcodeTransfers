package com.example.elijah.barcodetransfers;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Elijah on 7/3/2017.
 */

public class StoreAdapter extends ArrayAdapter<Store> {
    Context context;
    List<Store> list;
    public StoreAdapter(@NonNull Context context, @LayoutRes int resource, List<Store> objects) {
        super(context, resource);
        this.context = context;
        this.list = objects;
    }
    @Override
    public int getCount() {
        if(list != null)
            return list.size();
        return 0;
    }
    @Override
    public Store getItem(int position) {
        if(list != null)
            return list.get(position);
        return null;
    }
    @Override
    public long getItemId(int position) {
        if(list != null)
            return list.get(position).hashCode();
        return 0;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;

        //If the listview does not have an xml layout ready set the layout
        if (convertView == null){

            //we need a new holder to hold the structure of the cell
            holder = new Holder();

            //get the XML inflation service
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //Inflate our xml cell to the convertView
            convertView = inflater.inflate(R.layout.storeslist, null);

            //Get xml components into our holder class

            holder.textView7 = (TextView)convertView.findViewById(R.id.textView7);
            holder.textView8 = (TextView)convertView.findViewById(R.id.textView8);


            //Attach our holder class to this particular cell
            convertView.setTag(holder);

        }else{

            //The listview cell is not empty and contains already components loaded, get the tagged holder
            holder = (Holder)convertView.getTag();

        }

        //Fill our cell with data

        //get our person object from the list we passed to the adapter
        Store tir = getItem(position);
        // String price = Double.toString(Double.parseDouble(cart.getPrice())*Double.parseDouble(cart.getQty())) ;
        //Fill our view components with data
        holder.textView7.setText(tir.getCity());
        holder.textView8.setText(tir.getStorenumber());


//        Picasso.with(context).load(person.getImage()).placeholder(R.drawable.bbtlogo).fit().centerCrop().into(holder.imageView2);

        return convertView;
    }
    private class Holder{

        TextView textView7;
        TextView textView8;


    }
}