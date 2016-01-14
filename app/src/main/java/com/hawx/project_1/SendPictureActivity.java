package com.hawx.project_1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Target;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Data.DataList;
import Utils.PictureRVAdapter;

/**
 * Created by Administrator on 2016/1/14.
 */
public class SendPictureActivity extends BaseActivity {
    private final int REQUEST_STORAGE_PERMISSION=1;
    public  final String SER_KEY = "friendprofilelist";
    private Toolbar toolbar;
    private String userID;
    private TextView textView;
    private Button send;
    private String imgPath;
    private int SystemCapture=1;
    private List<Bitmap> list=new ArrayList<Bitmap>();
    private List<String> list_time=new ArrayList<String>();
    private RecyclerView recyclerview;
    private PictureRVAdapter adapter;
    private int width;
    private Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendpic_layout);
        context=this;
        BaseActivity.addActivity(this);
        userID=getIntent().getStringExtra(SER_KEY);
        toolbar= (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Send Picture");
        setSupportActionBar(toolbar);
        textView= (TextView) findViewById(R.id.userid);
        textView.setText(userID);
        send= (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkSelfPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if(checkSelfPermission != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(SendPictureActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_STORAGE_PERMISSION);
                }else {
                    File vFile = new File(imgPath);
                    if (!vFile.exists()) {
                        File vDirPath = vFile.getParentFile(); //new File(vFile.getParent());
                        vDirPath.mkdirs();
                    }
                    Uri uri = Uri.fromFile(vFile);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);//
                    startActivityForResult(intent, SystemCapture);
                }
            }
        });
        imgPath = Environment.getExternalStorageDirectory().toString()+"/" + "img.jpg";;//获取跟目录
        recyclerview= (RecyclerView) findViewById(R.id.recyclerview);
        adapter=new PictureRVAdapter(this,list,list_time);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        WindowManager window= (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display=window.getDefaultDisplay();
        Point point=new Point();
        display.getSize(point);
        width=point.x;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
        {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            Bitmap bmp = BitmapFactory.decodeFile(imgPath,options);
            options.outWidth=width/2;
            options.outHeight=(width/2)/options.outWidth*options.outHeight;
            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeFile(imgPath, options);
            ExifInterface exif=null;
            try {
                exif=new ExifInterface(imgPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String orientString =exif.getAttribute(ExifInterface.TAG_ORIENTATION);
            int orientation=orientString!=null?Integer.parseInt(orientString):ExifInterface.ORIENTATION_NORMAL;
            int rotationAngle=0;
            if(orientation==ExifInterface.ORIENTATION_ROTATE_90){
                rotationAngle=90;
            }
            if(orientation==ExifInterface.ORIENTATION_ROTATE_180){
                rotationAngle=180;
            }
            if(orientation==ExifInterface.ORIENTATION_ROTATE_270){
                rotationAngle=270;
            }
            Matrix matrix=new Matrix();
            matrix.setRotate(rotationAngle);
            Bitmap rotatedBitmap=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
            SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
            Date curDate=new Date(System.currentTimeMillis());
            String str=formater.format(curDate);
            list_time.add(str);
            list.add(rotatedBitmap);
            adapter.setList_time(list_time);
            adapter.setList(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_STORAGE_PERMISSION:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    File vFile = new File(imgPath);
                    if (!vFile.exists()) {
                        File vDirPath = vFile.getParentFile(); //new File(vFile.getParent());
                        vDirPath.mkdirs();
                    }
                    Uri uri = Uri.fromFile(vFile);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);//
                    startActivityForResult(intent, SystemCapture);
                }else{
                    Toast.makeText(SendPictureActivity.this,"获取权限失败",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
