package com.example.eor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UploadPostActivity extends AppCompatActivity {

    ImageView uploadImage;
    EditText posttitle,postdescription,rent_price;
    private static  int PICK_IMAGE = 1000;
    private static final int PERMISSION_CODE = 1001;
    Uri imageURI;
    static Bitmap bitmap;
    UploadPost_DAO post_dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_post);
        post_dao=new UploadPost_DAO();
        setVariables();
        uploadImage = findViewById(R.id.__imageview_postimage);
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking permission
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                    {
                        String[] permissions = {Manifest.permission .READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                    else
                    {
                        openGallery();
                    }
                }
                else
                {
                    openGallery();
                }
            }
        });
    }

    private void setVariables()
    {
        posttitle=findViewById(R.id.__edittext_adtitle);
        postdescription=findViewById(R.id.__edittext_addescription);
        rent_price=findViewById(R.id.__edittext_amount);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    openGallery();
                }
                else
                {
                    Toast.makeText(this, "Permission Denied!!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void openGallery() {
        Intent phoneGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(phoneGallery,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE)
        {
            imageURI = data.getData();
            uploadImage.setImageURI(imageURI);
            try {
                Bitmap bitmap1= MediaStore.Images.Media.getBitmap(getContentResolver(),imageURI);
                bitmap=bitmap1;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    private static String imagetoString(Bitmap bmp)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.WEBP,10,byteArrayOutputStream);
        byte[] imgBytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }


    public void uploadPost(View view) {
        post_dao.SendDatatoServer(new UploadPost_Model(imagetoString(bitmap),posttitle.getText().toString(),postdescription.getText().toString(),rent_price.getText().toString()));
    }
}
