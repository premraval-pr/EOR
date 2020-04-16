package com.example.eor.dao;

import android.os.StrictMode;

import com.example.eor.api.Paths;
import com.example.eor.model.UserCredentials_Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class User_DAO {
    public UserCredentials_Model getUser(String user_id){
        String line,result;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url=new URL(Paths.userDetailsUrl+"?userid="+user_id);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder=new StringBuilder();
            while ((line = bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line+"\n");
            }
            result=stringBuilder.toString();

            System.out.println(result);
            JSONArray ja = new JSONArray(result);

            for(int i=0;i<ja.length();i++)
            {
                JSONObject jsonObject1=ja.getJSONObject(i);
                return new UserCredentials_Model(jsonObject1.getString("user_id"),jsonObject1.getString("user_fname"),jsonObject1.getString("user_lname"),
                        jsonObject1.getString("user_street"),jsonObject1.getString("user_apt_num"),jsonObject1.getString("user_city"),
                        jsonObject1.getString("user_state"),jsonObject1.getString("user_postal_code"),jsonObject1.getString("user_contact"),
                        jsonObject1.getString("user_gender"),jsonObject1.getString("user_email"),jsonObject1.getString("user_display_name"),
                        jsonObject1.getString("user_password"));
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
}
