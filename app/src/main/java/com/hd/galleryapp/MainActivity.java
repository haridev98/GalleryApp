package com.hd.galleryapp;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b,a,v,c;
    Bitmap cam;
    ImageView i,y;
    EditText t;
    String d;
    TextView f;
    sqlhelper sql;
    View l;
    private  static  final  int CAMERA_REQUEST =99;
    private static final int r =100;
    Uri imageuri;
       int h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l=findViewById(R.id.activity_main);
        l.setBackgroundColor(Color.GRAY);


        a= (Button) findViewById(R.id.button);
         b = (Button) findViewById(R.id.button3);
        i = (ImageView) findViewById(R.id.imageView);
        c= (Button) findViewById(R.id.button2);
        v= (Button) findViewById(R.id.button4);
        f= (TextView) findViewById(R.id.textView2);
        t= (EditText) findViewById(R.id.editText);
        a.setOnClickListener(this);
        c.setOnClickListener(this);
        b.setOnClickListener(this);
        v.setOnClickListener(this);
        sql= new sqlhelper(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.button){
            d=t.getText().toString();
            byte[] k=getBytes(cam);
             SQLiteDatabase db = sql.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(sqlhelper.caption,d);
            cv.put(sqlhelper.im,k);
            db.insert(sqlhelper.tsblename,null,cv);
            db.close();

        }
        if (v.getId()==R.id.button2){
            Intent intent = new Intent(this,list.class);
            startActivity(intent);
        }
        if (v.getId()==R.id.button3){
            opencamera();
        }
        if (v.getId() == R.id.button4){
            opengallery();
        }

    }
    public void opencamera()
    {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera,CAMERA_REQUEST);
    }
    public void opengallery()
    {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        gallery.putExtra("crop","true");
        gallery.putExtra("aspectX",1);
        gallery.putExtra("aspectY",1);
        gallery.putExtra("outputX",200);
        gallery.putExtra("outputY",200);
        gallery.putExtra("return-data",true);

        startActivityForResult(gallery,r);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode==r)&& resultCode==RESULT_OK)
        {  Bundle extras = data.getExtras();
            cam=extras.getParcelable("data");




            i.setImageBitmap(cam);
            Toast.makeText(this,"Gallerycalled",Toast.LENGTH_LONG).show();

        }


        if ((requestCode==CAMERA_REQUEST)&& resultCode==RESULT_OK)
        {
            cam= (Bitmap) data.getExtras().get("data");
            i.setImageBitmap(cam);
            Toast.makeText(this,"cameracalled",Toast.LENGTH_LONG).show();
        }
    }
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        return stream.toByteArray();
    }
}
