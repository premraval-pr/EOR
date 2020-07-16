package com.example.eor.dao;

import android.os.StrictMode;

import com.example.eor.api.Paths;
import com.example.eor.model.PostDescription_Model;

import org.json.JSONArray;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDescription_DAO {

    String post_title;
    String post_id;
    String post_description;
    String post_price;
    Date dateFrom;
    Date dateTo;
    Date dateCreated;
    String f_name,city;
    public static ArrayList<String> image_post;
    public PostDescription_Model getPost(String post_id) {
        String line, result;

        try {
            System.out.println(post_id);
            image_post=new ArrayList<>();
            for(String s : PostDescription_DAO.image_post)
            {
                PostDescription_DAO.image_post.remove(s);
            }
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url = new URL(Paths.postDescriptionURL + "?postid=" + post_id);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            result = stringBuilder.toString();

            System.out.println(result);
            JSONArray ja = new JSONArray(result);

            for (int i = 0; i < ja.length(); i++) {
                JSONObject jsonObject1 = ja.getJSONObject(i);
                System.out.println(jsonObject1.getString("post_id"));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                dateFrom = sdf.parse(jsonObject1.getString("post_available_from").substring(0,10));
                dateTo = sdf.parse(jsonObject1.getString("post_available_to").substring(0,10));
                dateCreated = sdf.parse(jsonObject1.getString("post_created").substring(0,10));
                image_post.add(jsonObject1.getString("image_link"));

                this.post_id=jsonObject1.getString("post_id");
                post_title=jsonObject1.getString("post_title");
                post_description=jsonObject1.getString("post_description");
                post_price=jsonObject1.getString("post_price");
                f_name=jsonObject1.getString("user_fname");
                city=jsonObject1.getString("user_city");


            }

            return new PostDescription_Model(this.post_id,post_title ,post_description
                    , post_price,dateFrom
                    , dateTo, dateCreated, image_post,f_name,city );
        } catch (Exception e) {
            System.out.println(e+"date error");
        }
        return null;
    }
}
