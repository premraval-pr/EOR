package com.example.eor.dao;

import android.os.AsyncTask;
import android.os.StrictMode;

import com.example.eor.api.Paths;
import com.example.eor.activity.SlidingDrawerActivity;
import com.example.eor.model.UploadPost_Model;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UploadPost_DAO {

    public void SendDatatoServer(final UploadPost_Model m)
    {
        class SendPostReqAsyncTask extends AsyncTask<UploadPost_Model, Void, String> {
            @Override
            protected String doInBackground(UploadPost_Model... params) {
                UploadPost_Model data = m;

                try {

                    List<NameValuePair> nameValuePairs = new ArrayList<>();
                    nameValuePairs.add(new BasicNameValuePair("title", data.getUploadpost_posttite()));

                    nameValuePairs.add(new BasicNameValuePair("username", SlidingDrawerActivity.USER_ID));
                    nameValuePairs.add(new BasicNameValuePair("description",data.getUploadpost_postdescription()));
                    for(String s : data.getUploadpost_imagepath()) {
                        if(s!=null) {
                            nameValuePairs.add(new BasicNameValuePair("image_eor[]", s));
                        }
                    }
                    nameValuePairs.add(new BasicNameValuePair("price",String.valueOf(data.getUploadpost_postprice())));
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(Paths.uploadPost);
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(entity.getContent()));
                    System.out.println(in.readLine());
                    String line;
                    StringBuilder stringBuilder=new StringBuilder();
                    while (( line = in.readLine())!=null)
                    {
                        stringBuilder.append(line+"\n");
                    }
                    String result=stringBuilder.toString();
                    System.out.println(result);


                } catch (Exception e) {
                    System.out.println(e);

                }
                return "Data Submit Successfully";
            }
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(m);
    }

}
