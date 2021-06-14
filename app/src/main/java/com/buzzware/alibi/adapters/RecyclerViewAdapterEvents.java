package com.buzzware.alibi.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.buzzware.alibi.R;
import com.buzzware.alibi.databinding.LayoutItemsEventsBinding;

import java.util.List;


public class RecyclerViewAdapterEvents extends RecyclerView.Adapter<RecyclerViewAdapterEvents.ViewHolder>  {

    private static final String TAG = "RCA_C_Orders";

    private List<String> list;
    private Context mContext;

    public RecyclerViewAdapterEvents(Context mContext, List<String> list) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutItemsEventsBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder : called.");

        if((i%2) == 0){
            viewHolder.binding.eventNameTV.setText("Medical Appointment");
            viewHolder.binding.eventTimeTV.setText("In 2 Days");
            viewHolder.binding.eventDetailsTV.setText("Trippler Army Medical Center");
            viewHolder.binding.eventTypeTV.setText("Private");
            viewHolder.binding.eventTypeTV.setBackground(mContext.getResources().getDrawable(R.drawable.rounded_background_light_red));
            viewHolder.binding.eventTypeTV.setTextColor(mContext.getResources().getColor(R.color.red));

        }

        viewHolder.binding.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, Chat.class);
//                mContext.startActivity(intent);
//                ((Activity) mContext).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    @Override
    public int getItemCount() {
        int arr = 0;
        try{
            if(list.size()==0){
                arr = 0;
            }
            else{

                arr=list.size();
            }
        }catch (Exception e){
        }

        return 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        LayoutItemsEventsBinding binding;

        public ViewHolder(@NonNull LayoutItemsEventsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
