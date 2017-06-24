package com.hd.galleryapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class list extends AppCompatActivity {
    GridView gridView;
    modbaseadapter mod = null;
    ArrayList<single> list;
    sqlhelper sql;
    Context context;
    View l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        gridView= (GridView) findViewById(R.id.gridview);
        sql= new sqlhelper(this);
        l=findViewById(R.id.list1);
        l.setBackgroundColor(Color.GRAY);
        context = this;
        list = new ArrayList<>();
        mod = new modbaseadapter(R.layout.single_view_layout,list,this);
        gridView.setAdapter(mod);
        SQLiteDatabase db = sql.getWritableDatabase();
        String [] a={sqlhelper.uid,sqlhelper.caption,sqlhelper.im};
        Cursor cursor= db.query(sqlhelper.tsblename,a,null,null,null,null,null);
        while (cursor.moveToNext()){
           int h= cursor.getInt(cursor.getColumnIndex(sqlhelper.uid));
            byte[] imd =  cursor.getBlob(cursor.getColumnIndex(sqlhelper.im));
            String s = cursor.getString(cursor.getColumnIndex(sqlhelper.caption));
            list.add(new single(s,imd,h));}
        mod.notifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context,pic.class);
                intent.putExtra("id",position);
                startActivity(intent);
            }
        });



    }
    public void gull(View v){
        Intent i= new Intent(this,MainActivity.class);
        startActivity(i);
    }

}
