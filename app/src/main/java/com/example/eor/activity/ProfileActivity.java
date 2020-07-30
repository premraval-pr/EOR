package com.example.eor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.eor.R;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    String img1 ="https://d7hftxdivxxvm.cloudfront.net/?resize_to=width&src=https%3A%2F%2Fartsy-media-uploads.s3.amazonaws.com%2F5hHS2QIKHR72V3oqRqZU2g%252Ffarmhouse-picasso-lead.jpg&width=1200&quality=80";
    String img2 = "https://static01.nyt.com/images/2018/03/02/arts/design/02picasso-print/01picasso1-superJumbo.jpg";
    String img3= "https://assets.vogue.com/photos/5be9adcb7509832ced44221c/master/pass/00-promo-picasso-an-intimate-portrait.jpg";
    ImageView image1, image2, image3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        image1 = findViewById(R.id.__imageviewPost_imageOfPost1);
        image2 = findViewById(R.id.__imageviewPost_imageOfPost2);
        image3 = findViewById(R.id.__imageviewPost_imageOfPost3);

        Picasso.with(this).load(img1).resize(800,800).into(image1);
        Picasso.with(this).load(img2).resize(800,800).into(image2);
        Picasso.with(this).load(img3).resize(800,800).into(image3);
    }
}