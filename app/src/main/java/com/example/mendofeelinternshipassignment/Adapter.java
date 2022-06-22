package com.example.mendofeelinternshipassignment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList<String> username;
    ArrayList<String> postTime;
    ArrayList<String> description;
    ArrayList<String> likesA;
    ArrayList<String> comments;
    ArrayList<String> userimage;
    ArrayList<String> postimage;
    ArrayList<String> response1;
    ArrayList<String> percent1;
    ArrayList<String> response2;
    ArrayList<String> percent2;
    int c1=0,c2=0;

    public Adapter(ArrayList<String> username, ArrayList<String> postTime, ArrayList<String> description, ArrayList<String> likesA, ArrayList<String> comments, ArrayList<String> userimage, ArrayList<String> postimage, ArrayList<String> response1, ArrayList<String> percent1, ArrayList<String> response2, ArrayList<String> percent2) {
        this.username = username;
        this.postTime = postTime;
        this.description = description;
        this.likesA = likesA;
        this.comments = comments;
        this.userimage=userimage;
        this.postimage=postimage;
        this.response1=response1;
        this.percent1=percent1;
        this.response2=response2;
        this.percent2=percent2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.usernameTV.setText(username.get(position));
        holder.postTimeTV.setText(postTime.get(position));
        holder.descriptionTV.setText(description.get(position));
        holder.likesTV.setText(likesA.get(position));
        holder.commentsTV.setText(comments.get(position));

        holder.res1.setText(response1.get(position));
        holder.per1.setText(percent1.get(position));
        holder.res2.setText(response2.get(position));
        holder.per2.setText(percent2.get(position));

//        try
//        {
//            URL imageurl=new URL(userimage.get(position));
//            Glide.with(holder.user.getContext()).load(imageurl).into(holder.user);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        Glide.with(holder.user.getContext()).load(userimage.get(position)).into(holder.user);
        Glide.with(holder.post.getContext()).load(postimage.get(position)).into(holder.post);

        if(postimage.get(position)=="null")
        {
            holder.post.setVisibility(View.GONE);
        }

        if(response1.get(position)=="null" || response2.get(position)=="null" || percent1.get(position)=="null" || percent2.get(position)=="null")
        {
            holder.ll.setVisibility(View.GONE);
        }

        holder.heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.heart.setBackgroundResource(R.drawable.ic_baseline_favorite_24_onselected);
                holder.likesTV.setText((likesA.get(position))+"+1");
            }
        });

        holder.per1card.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                c1++;
                if(c1%2==0)
                {
                    holder.per1card.setBackgroundResource(R.color.white);
                    holder.per1.setVisibility(View.GONE);
                }
                else
                {
                    holder.per1card.setBackgroundResource(R.color.red);
                    holder.per1.setVisibility(View.VISIBLE);
                }
            }
        });

        holder.per2card.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                c2++;
                if(c2%2==0)
                {
                    holder.per2card.setBackgroundResource(R.color.white);
                    holder.per2.setVisibility(View.GONE);
                }
                else
                {
                    holder.per2card.setBackgroundResource(R.color.grey);
                    holder.per2.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return username.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTV,postTimeTV,descriptionTV, likesTV, commentsTV,heart,res1,res2,per1,per2;
        ImageView user,post;
        LinearLayout ll;
        CardView per1card, per2card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            usernameTV=itemView.findViewById(R.id.username);
            postTimeTV=itemView.findViewById(R.id.postTime);
            descriptionTV=itemView.findViewById(R.id.description);
            likesTV=itemView.findViewById(R.id.likesT);
            commentsTV=itemView.findViewById(R.id.comments);
            user=itemView.findViewById(R.id.userImage);
            post=itemView.findViewById(R.id.postImage);
            heart=itemView.findViewById(R.id.heart);
            res1=itemView.findViewById(R.id.res1);
            res2 =itemView.findViewById(R.id.res2);
            per1=itemView.findViewById(R.id.per1);
            per2=itemView.findViewById(R.id.per2);
            ll=itemView.findViewById(R.id.ll);
            per1card=itemView.findViewById(R.id.per1card);
            per2card=itemView.findViewById(R.id.per2card);
        }
    }
}
