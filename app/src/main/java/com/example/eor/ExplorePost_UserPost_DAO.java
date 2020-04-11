package com.example.eor;

import android.os.AsyncTask;
import android.os.StrictMode;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ExplorePost_UserPost_DAO extends AsyncTask<Void,Void,Void> {

    String s;

    String result;
    String line;
    static List<ExplorePost_Model> list;
    public void collectData()
    {
        try {
            list=new ArrayList<>();
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("userid",SlidingDrawerActivity.USER_ID));
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(Paths.retiveUsersposts);
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(entity.getContent()));
            StringBuilder stringBuilder=new StringBuilder();
            while ((line = bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line+"\n");
            }
            result=stringBuilder.toString();
            System.out.println(result);
            JSONArray ja=new JSONArray(result);

            for(int i=0;i<ja.length();i++)
            {
                JSONObject jsonObject=ja.getJSONObject(i);
                list.add(new ExplorePost_Model(jsonObject.getString("title"),jsonObject.getString("username"),jsonObject.getString("location"),jsonObject.getString("image_eor"),jsonObject.getDouble("price")));

            }

        }
        catch (Exception e)
        {
            System.out.println(e+"Hello");
        }


    }

    @Override
    protected Void doInBackground(Void... voids) {
        collectData();
        return null;
    }
}



