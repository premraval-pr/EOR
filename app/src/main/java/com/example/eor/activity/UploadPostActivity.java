package com.example.eor.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.eor.R;
import com.example.eor.dao.UploadPost_DAO;
import com.example.eor.model.UploadPost_Model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadPostActivity extends AppCompatActivity {

    ImageView uploadImage,uploadImage2,uploadImage3,uploadImage4,imageView;
    EditText posttitle,postdescription,rent_price;
    private static  int PICK_IMAGE = 1000;
    private static final int PERMISSION_CODE = 1001;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    UploadPost_DAO post_dao;
    Bitmap bm;
    String [] image_paths;
    Bitmap bitmapPickedGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_post);
        post_dao=new UploadPost_DAO();
        setVariables();
        image_paths=new String[4];
        uploadImage = findViewById(R.id.__imageview_postimage);
        uploadImage2=findViewById(R.id.__imageview_postimage2);
        uploadImage3=findViewById(R.id.__imageview_postimage3);
        uploadImage4=findViewById(R.id.__imageview_postimage4);
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView=uploadImage;
                selectImage();
            }
        });
        uploadImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView = uploadImage2;
                selectImage();
            }
        });

        uploadImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView=uploadImage3;
                selectImage();
            }
        });

        uploadImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView=uploadImage4;
                selectImage();
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }

    }

    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }



    private void setVariables()
    {
        posttitle=findViewById(R.id.__edittext_adtitle);
        postdescription=findViewById(R.id.__edittext_addescription);
        rent_price=findViewById(R.id.__edittext_amount);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "Permission Denied!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openGallery() {
        Intent phoneGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(phoneGallery,PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        bm = (Bitmap) data.getExtras().get("data");
                        imageView.setImageBitmap(bm);
                        //setData(bm);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                Bitmap bitmapPickedGallery = BitmapFactory.decodeFile(picturePath);
                                imageView.setImageBitmap(bitmapPickedGallery);
                               // setData(bitmapPickedGallery);
                                cursor.close();

                            }
                        }
                    }
                    break;
            }
        }
    }




    private void setData(Bitmap bm) {
        if(imageView.getId()==R.id.__imageview_postimage)
        {
            image_paths[0]=imageToString(bm);
        }
        if(imageView.getId()==R.id.__imageview_postimage2)
        {
            image_paths[1]=imageToString(bm);
        }
        if(imageView.getId()==R.id.__imageview_postimage3)
        {
            image_paths[2]=imageToString(bm);
        }
        if(imageView.getId()==R.id.__imageview_postimage4)
        {
            image_paths[3]=imageToString(bm);
        }
    }

    private static String imageToString(Bitmap bmp)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.WEBP,10,byteArrayOutputStream);
        byte[] imgBytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }



    public void uploadPost(View view) {

        Toast.makeText(getApplicationContext(),"Your post has been created",Toast.LENGTH_SHORT).show();
        post_dao.SendDatatoServer(new UploadPost_Model(image_paths,posttitle.getText().toString(),postdescription.getText().toString(),rent_price.getText().toString()));
        Intent intent = new Intent(getApplicationContext(), SlidingDrawerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}
