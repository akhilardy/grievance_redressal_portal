package com.example.finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<messageItem> mExampleList;
    public ExampleAdapter(ArrayList<messageItem> exampleList){
        mExampleList=exampleList;
    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.message,parent,false);
        ExampleViewHolder evh=new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        messageItem currentItem=mExampleList.get(position);
        holder.usrName.setText(currentItem.getUsername());
        holder.loc.setText(currentItem.getLocation());
        holder.prob.setText(currentItem.getProblem());
        holder.sta.setText(currentItem.getStatus());
        holder.probCode.setText(currentItem.getProblemCode());
        holder.tStamp.setText(currentItem.getTime());

    }

    @Override
    public int getItemCount() {

        return mExampleList.size();
    }

    public  static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView usrName,loc,prob,sta,probCode,tStamp;


        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            usrName=itemView.findViewById(R.id.user_username);
            loc=itemView.findViewById(R.id.user_location);
            prob=itemView.findViewById(R.id.user_problem);
            sta=itemView.findViewById(R.id.user_status);
            probCode=itemView.findViewById(R.id.user_problemcode);
            tStamp=itemView.findViewById(R.id.user_timestamp);

        }
    }

}
