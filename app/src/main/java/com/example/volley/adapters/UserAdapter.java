package com.example.volley.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.volley.R;
import com.example.volley.model.User;

import java.util.List;



public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private Context context;
    private List<User> mdata;
     RequestOptions option;

    public UserAdapter(Context context, List<User> data) {
        this.context = context;
        this.mdata = data;
        //Request option for Glide

        option=new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=(LayoutInflater.from(context).inflate(R.layout.user_item,parent,false));

       return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.fName.setText(mdata.get(position).getFirstname());
        holder.lName.setText(mdata.get(position).getLastName());
        holder.eMail.setText(mdata.get(position).getEmail());

        //load Image from the internet and set it into imageView using Glide

        Glide.with(context).load(mdata.get(position).getImage_url()).apply(option).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
         TextView fName,lName,eMail;
         ImageView img;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

             fName=itemView.findViewById(R.id.tvFirstName);
             lName=itemView.findViewById(R.id.tvLastName);
             eMail=itemView.findViewById(R.id.tvEmail);
             img=itemView.findViewById(R.id.imageView);

        }
    }



}
