package com.hd.galleryapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class pic extends AppCompatActivity  {
    int i;
    Button d,u;
    ImageView imageView;
    TextView textView;
    sqlhelper sql;
    SQLiteDatabase db;
   public static final  int PIC_CROP=22;
    int m;
    Bitmap b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic);
        u= (Button) findViewById(R.id.button5);
        imageView = (ImageView) findViewById(R.id.im12);
        textView= (TextView) findViewById(R.id.tv12);

        sql= new sqlhelper(this);
         db = sql.getWritableDatabase();

        i= getIntent().getIntExtra("id",0);
        String [] a={sqlhelper.uid,sqlhelper.caption,sqlhelper.im};
        Cursor cursor= db.query(sqlhelper.tsblename,a,null,null,null,null,null);
        cursor.moveToPosition(i);
         m= cursor.getInt(cursor.getColumnIndex(sqlhelper.uid));
        String s = cursor.getString(cursor.getColumnIndex(sqlhelper.caption));
        byte[] img = cursor.getBlob(cursor.getColumnIndex(sqlhelper.im));
         b= BitmapFactory.decodeByteArray(img,0,img.length);
        imageView.setImageBitmap(b);
        textView.setText(s);

    }
    public  void  butt(View v){
        db.delete(sqlhelper.tsblename,sqlhelper.uid+" = "+m,null);
        Intent i = new Intent(this,list.class);
        startActivity(i);

    }
    public  void  futt(View v){
        Intent i = new Intent(this,list.class);
        startActivity(i);

    }


}
