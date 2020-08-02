package com.example.eor.dao;

import android.os.AsyncTask;
import android.os.StrictMode;

import com.example.eor.activity.SlidingDrawerActivity;
import com.example.eor.api.Paths;
import com.example.eor.listener.IProfileListener;
import com.example.eor.model.ExplorePost_Model;
import com.example.eor.model.PostDescription_Model;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Profile_DAO  extends AsyncTask<Void,Void,Void> {
    String result;
    String line;
    String uid;
    public static List<ExplorePost_Model> list;
    IProfileListener iProfileListener;

    public Profile_DAO(IProfileListener iProfileListener) {
        this.iProfileListener = iProfileListener;
    }

    public void collectData(String user_id)
    {
        this.uid = user_id;
        try {
            list=new ArrayList<>();
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("userid", uid));
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
            JSONArray ja=new JSONArray(result);

            for(int i=0;i<ja.length();i++)
            {
                JSONObject jsonObject=ja.getJSONObject(i);
                ExplorePost_Model explorePost_model=new ExplorePost_Model(jsonObject.getString("id"),
                        jsonObject.getString("title"),
                        jsonObject.getString("username"),
                        jsonObject.getString("location"),
                        jsonObject.getString("image_eor")
                       );
                
                if(!list.contains(explorePost_model)) {
                    list.add(explorePost_model);
                }
            }
            System.out.println("Profile Data --> "+list);
            iProfileListener.getUserProfilePosts(list);

        }
        catch (Exception e)
        {
            System.out.println(e+"Hello");
        }


    }

    @Override
    protected Void doInBackground(Void... voids) {
        collectData(uid);
        return null;
    }
}
