package com.example.eor.popup;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eor.R;

public class ReplyPop extends AppCompatActivity {

    Button cancelReply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replypop);

        cancelReply = findViewById(R.id.__button_reply_cancel);
        cancelReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        DisplayMetrics displaymatrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymatrics);

        int width = displaymatrics.widthPixels;
        int height = displaymatrics.heightPixels;

        getWindow().setLayout(width,(int) (height*0.25));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;

        params.x=0;
        params.y=-20;

        getWindow().setAttributes(params);
    }
}
