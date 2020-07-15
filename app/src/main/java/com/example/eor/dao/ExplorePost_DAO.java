package com.example.eor.dao;

import android.os.StrictMode;

import com.example.eor.fragment.FilterFragment;
import com.example.eor.api.Paths;
import com.example.eor.model.ExplorePost_Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ExplorePost_DAO {

    String s;

    String result;
    String line;
    public static List<ExplorePost_Model> list;



    public void collectData()
    {
        try {
            list=new ArrayList<>();
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url=new URL(Paths.retivreUrl);
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

            JSONArray ja=new JSONArray(result);

            for(int i=0;i<ja.length();i++)
            {
                JSONObject jsonObject=ja.getJSONObject(i);
                if(FilterFragment.productPrice>jsonObject.getDouble("price") || FilterFragment.productPrice ==0) {
                    list.add(new ExplorePost_Model(jsonObject.getString("id"),
                            jsonObject.getString("title"),
                            jsonObject.getString("username"),
                            jsonObject.getString("location"),
                            jsonObject.getString("image_eor"),
                            jsonObject.getDouble("price"),
                            jsonObject.getDouble("latitude"),
                            jsonObject.getDouble("longitude")));
                }
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }


    }

}



