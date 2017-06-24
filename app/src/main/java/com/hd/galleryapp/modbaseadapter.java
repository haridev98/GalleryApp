package com.hd.galleryapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class modbaseadapter extends BaseAdapter {
    ArrayList<single> roll;
    Context context;
    int layout;

    public modbaseadapter(int layout, ArrayList<single> roll, Context context) {
        this.layout = layout;
        this.roll = roll;
        this.context = context;
    }

    @Override
    public int getCount() {
        return roll.size();
    }

    @Override
    public Object getItem(int position) {
        return roll.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private  class holder{
         ImageView imageViews;
        TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        holder hold = new holder();
        if(row == null)
        {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.single_view_layout,parent,false);


        hold.textView= (TextView) row.findViewById(R.id.textView3);
        hold.imageViews = (ImageView) row.findViewById(R.id.imageView3);
            row.setTag(hold);}
        else {
            hold = (holder) row.getTag();
        }
        single temp = roll.get(position);
        hold.textView.setText(temp.caption);
        Bitmap des = BitmapFactory.decodeByteArray(temp.image,0,temp.image.length);
        hold.imageViews.setImageBitmap(des);
        return row;
    }
}
