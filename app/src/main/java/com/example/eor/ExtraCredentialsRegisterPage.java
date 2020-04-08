package com.example.eor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ExtraCredentialsRegisterPage extends AppCompatActivity {

    Button __button_registerButton_extraCredentials;
    EditText displayname,pw,cpw;
    Drawable err_indiactor;
    boolean s_displayname,s_pw,s_cpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_credentials_register_page);

        
        __button_registerButton_extraCredentials = findViewById(R.id.__button_regsiter_ExtraCredentialsPage);

        __button_registerButton_extraCredentials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                displayname = findViewById(R.id.__edittext_displayname);
                s_displayname = displayname.getText().toString().isEmpty();


                pw = findViewById(R.id.__edittext_password_signup);
                s_pw = pw.getText().toString().isEmpty();


                cpw = findViewById(R.id.__edittext_confirmpassword_signup);
                s_cpw = cpw.getText().toString().isEmpty();

                err_indiactor = getResources().getDrawable(R.drawable.__errorindicator);


                if (displayname.getText().toString().isEmpty()) {
                    displayname.setCompoundDrawablesWithIntrinsicBounds(null, null, err_indiactor, null);
                    displayname.setError("Display Name Empty", err_indiactor);

                } else if (pw.getText().toString().isEmpty()) {
                    pw.setCompoundDrawablesWithIntrinsicBounds(null, null, err_indiactor, null);
                    pw.setError("Password Empty", err_indiactor);
                    displayname.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);

                } else if (cpw.getText().toString().isEmpty()) {
                    cpw.setCompoundDrawablesWithIntrinsicBounds(null, null, err_indiactor, null);
                    cpw.setError("Confirm Password Not Valid", err_indiactor);
                    pw.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);


                }
                else if(!(cpw.getText().toString().equals(pw.getText().toString())))
                {
                    cpw.setCompoundDrawablesWithIntrinsicBounds(null, null, err_indiactor, null);
                    cpw.setError("Password and Confirm Password doesnt Match", err_indiactor);
                    pw.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                }
                else {
                    removeErrorLogoFromAll();
                }


                if (!s_pw && !s_cpw  && !s_displayname && (pw.getText().toString().equals(cpw.getText().toString()))) {


                    Intent i2 = getIntent();

                    removeErrorLogoFromAll();
                    Intent i = new Intent(getApplicationContext(), SlidingDrawerActivity.class);

                    i.putExtra("name",displayname.getText().toString());
                    i.putExtra("uid",123);
                    startActivity(i);
                }


            }

            private void removeErrorLogoFromAll() {
                displayname.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                pw.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                cpw.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }

        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

