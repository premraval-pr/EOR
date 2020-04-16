package com.example.eor.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eor.R;
import com.example.eor.activity.FirstActivity;
import com.example.eor.api.Paths;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ExtraCredentialsRegisterPage extends AppCompatActivity {

    Button __button_registerButton_extraCredentials;
    EditText displayname,pw,cpw;
    Drawable err_indiactor;
    boolean s_displayname,s_pw,s_cpw;
    private ProgressDialog PD;

    String string_displayname,string_password;

    String fname,lname,address,contactno,gender,useremail;

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
                    fname = i2.getStringExtra("firstname");
                    lname = i2.getStringExtra("lastname");
                    address = i2.getStringExtra("address");
                    contactno = i2.getStringExtra("contact");
                    gender = i2.getStringExtra("radioSex");
                    useremail =  i2.getStringExtra("email");
                    string_displayname = displayname.getText().toString();
                    string_password = pw.getText().toString();
                    removeErrorLogoFromAll();

                    try {
                        registerUser(fname,lname,address,contactno,gender,useremail,string_displayname,string_password);
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
//
                }


            }

            private void registerUser(String fname, String lname, String address, String contactno,
                                      String gender, String useremail, String string_displayname, String string_password) throws JSONException, IOException {

                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    List<NameValuePair> nameValuePairs = new ArrayList<>();
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(Paths.userRegistrationUrl);



                    String cont = contactno.substring(1,4) + contactno.substring(6,9) + contactno.substring(10,14);
                    System.out.println(cont+" Length: "+cont.length());



                    nameValuePairs.add(new BasicNameValuePair("firstname", fname.trim()));
                    nameValuePairs.add(new BasicNameValuePair("lastname", lname.trim()));
                    nameValuePairs.add(new BasicNameValuePair("street", "none"));
                    nameValuePairs.add(new BasicNameValuePair("aptno", "none"));
                    nameValuePairs.add(new BasicNameValuePair("city", "none"));
                    nameValuePairs.add(new BasicNameValuePair("state", "none"));
                    nameValuePairs.add(new BasicNameValuePair("postal", "none"));
                    nameValuePairs.add(new BasicNameValuePair("contact", cont.trim()));
                    nameValuePairs.add(new BasicNameValuePair("gender", gender.trim()));
                    nameValuePairs.add(new BasicNameValuePair("useremail", useremail.trim()));
                    nameValuePairs.add(new BasicNameValuePair("displayname", string_displayname.trim()));
                    nameValuePairs.add(new BasicNameValuePair("password", string_password.trim()));

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();


                    BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
                    System.out.println(br.readLine());

                    if(br.readLine() == "-1")
                    {
                        Toast.makeText(getApplicationContext(),"Email Already Exists",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Registered Successfully. Please Login",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), FirstActivity.class);
                        startActivity(i);
                    }

                }catch (Exception e)
                {
                    System.out.println(e);
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

