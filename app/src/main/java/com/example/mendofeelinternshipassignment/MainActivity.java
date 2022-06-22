package com.example.mendofeelinternshipassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> username=new ArrayList<>();
    ArrayList<String> postTime=new ArrayList<>();
    ArrayList<String> description=new ArrayList<>();
    ArrayList<String> likes= new ArrayList<>();
    ArrayList<String> comments=new ArrayList<>();
    ArrayList<String> userimage=new ArrayList<>();
    ArrayList<String> postimage=new ArrayList<>();
    ArrayList<String> response1= new ArrayList<>();
    ArrayList<String> percent1=new ArrayList<>();
    ArrayList<String> response2= new ArrayList<>();
    ArrayList<String> percent2=new ArrayList<>();
    Dialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        progress=new Dialog(this);
        progress.setContentView(R.layout.dialog_progress);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setCancelable(false);
        progress.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progress.dismiss();
                recyclerView.setVisibility(View.VISIBLE);
            }
        }, 3000);

        try {
            JSONObject obj=new JSONObject(loadJSONfromAssets());
            JSONArray userArray = obj.getJSONArray("results");
            for (int i=0;i<userArray.length();i++)
            {
                JSONObject userDetail = userArray.getJSONObject(i);
                username.add(userDetail.getString("fullName"));
                postTime.add(userDetail.getString("publishtime"));
                description.add(userDetail.getString("question"));
                likes.add(userDetail.getString("likescount"));
                comments.add(userDetail.getString("commentscount"));
                userimage.add(userDetail.getString("profile_photo"));
                postimage.add(userDetail.getString("post_photo"));

                response1.add(userDetail.getString("choice_text1"));
                response2.add(userDetail.getString("choice_text2"));
                percent1.add(userDetail.getString("percentage1"));
                percent2.add(userDetail.getString("percentage2"));

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        Adapter customAdapter=new Adapter(username, postTime, description, likes, comments, userimage, postimage, response1, percent1, response2, percent2);
        recyclerView.setAdapter(customAdapter);
    }

    private String loadJSONfromAssets() {
        String json=null;
        try
        {
            InputStream inputStream=getAssets().open("users.json");
            int fileSize= inputStream.available();
            byte [] bufferData=new byte[fileSize];
            inputStream.read(bufferData);
            inputStream.close();
            json=new String(bufferData, "UTF-8");
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}