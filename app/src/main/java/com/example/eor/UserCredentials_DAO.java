package com.example.eor;

import android.os.StrictMode;
import com.example.eor.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserCredentials_DAO
{
    String result;
    String line;
    static List<UserCredentials_Model> ulist;

    public void collectData()
    {
        try {
            ulist=new ArrayList<>();
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url=new URL(Paths.retriveUsersUrl);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
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

            JSONObject jsonObject = new JSONObject(result);

            JSONArray ja = jsonObject.getJSONArray("users");

            for(int i=0;i<ja.length();i++)
            {
                JSONObject jsonObject1=ja.getJSONObject(i);
                ulist.add(new UserCredentials_Model(jsonObject1.getString("user_id"),jsonObject1.getString("user_email"),jsonObject1.getString("user_password")));
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }



    }

    public String authenticateCredetials(String email, String password)
    {
        for(int i=0 ; i < ulist.size(); i++)
        {
            if(ulist.get(i).getUser_email().equals(email) && ulist.get(i).getUser_password().equals(password))
            {
                return ulist.get(i).getUser_id();
            }
        }
        return "-1";
    }
}
