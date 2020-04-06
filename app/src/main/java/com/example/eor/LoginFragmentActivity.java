package com.example.eor;


import android.content.Intent;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;



public class LoginFragmentActivity extends Fragment {

    private View __view_login;
    private Button __loginBtn;
    private TextView __userName,__password;
    private Intent __nextActivity;
    private Drawable err_indiactor;
    boolean Allfill = false;

    public LoginFragmentActivity() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        __view_login = inflater.inflate(R.layout.layout_signinfragment, container, false);
        __userName = __view_login.findViewById(R.id.__edittext_username);
        __password = __view_login.findViewById(R.id.__edittext_password_signin);
        __loginBtn = __view_login.findViewById(R.id.__button_login);
        err_indiactor = getResources().getDrawable(R.drawable.__errorindicator);
        __loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(__userName.getText().toString().isEmpty())
                {
                    __userName.setCompoundDrawablesWithIntrinsicBounds(null, null, err_indiactor, null);
                    __userName.setError("Username Empty",err_indiactor);
                    __userName.requestFocus();
                    Allfill = false;

                }
                else if(__password.getText().toString().isEmpty())
                {
                    __password.setCompoundDrawablesWithIntrinsicBounds(null, null, err_indiactor, null);
                    __password.setError("Password Empty",err_indiactor);
                    __password.requestFocus();
                    __userName.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                    Allfill = false;

                }
                else
                {

                    __password.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                    Allfill = true;

                }

                if(Allfill)
                {
                    __nextActivity = new Intent(getContext(),SlidingDrawerActivity.class);
                    __nextActivity.putExtra("data",__userName.getText().toString());
                    startActivity(__nextActivity);
                }
            }
        });

        return __view_login;
    }
}