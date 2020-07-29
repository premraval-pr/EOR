package com.example.eor.dao;

import android.os.AsyncTask;
import android.os.StrictMode;

import com.example.eor.activity.SlidingDrawerActivity;
import com.example.eor.api.Paths;
import com.example.eor.listener.SavedPostListener;
import com.example.eor.model.MySavedPostsModel;
import com.example.eor.model.PostDescription_Model;
import com.example.eor.model.UserCredentials_Model;

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
import java.util.ArrayList;
import java.util.List;

public class Saved_Posts_DAO
{
    static ArrayList<MySavedPostsModel> savedPostList;
    String line;
    String result;
    String create_line,create_result;
    SavedPostListener savedPostListener;

    public Saved_Posts_DAO()
    {

    }


    public Saved_Posts_DAO(SavedPostListener savedPostListener) {
        this.savedPostListener = savedPostListener;
    }

    public void CreateSavedPost(final String postid)
    {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... strings) {
                try {
                    List<NameValuePair> nameValuePairs = new ArrayList<>();
                    nameValuePairs.add(new BasicNameValuePair("post_id", postid));
                    nameValuePairs.add(new BasicNameValuePair("user_id", SlidingDrawerActivity.USER_ID));
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(Paths.createSavedPost);
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(entity.getContent()));

                    StringBuilder stringBuilder=new StringBuilder();
                    while (( create_line = in.readLine())!=null)
                    {
                        stringBuilder.append(create_line);
                    }
                    create_result = stringBuilder.toString();
                    savedPostListener.apiResult(create_result);


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
        sendPostReqAsyncTask.execute(postid);
    }


    public ArrayList<MySavedPostsModel> retrieveSavedPosts()
    {
        try {
            savedPostList=new ArrayList<>();
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("userid", SlidingDrawerActivity.USER_ID));
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(Paths.retrieveSavedPost);
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(entity.getContent()));
            StringBuilder stringBuilder=new StringBuilder();
            while ((line = bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line+"\n");
            }
            result=stringBuilder.toString();
            System.out.println(result);
            JSONArray jsonArray = new JSONArray(result);

            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                MySavedPostsModel mySavedPostsModel = new MySavedPostsModel();
                mySavedPostsModel.setId(jsonObject1.getString("id"));
                mySavedPostsModel.setTitle(jsonObject1.getString("title"));
                mySavedPostsModel.setDescription(jsonObject1.getString("post_description"));
                mySavedPostsModel.setLocation(jsonObject1.getString("location"));
                mySavedPostsModel.setRent(jsonObject1.getString("price"));
                mySavedPostsModel.setImage_url(jsonObject1.getString("image_eor"));
                savedPostList.add(mySavedPostsModel);


                if(!savedPostList.contains(mySavedPostsModel)) {
                    savedPostList.add(mySavedPostsModel);
                }
            }


        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return savedPostList;
    }

}
