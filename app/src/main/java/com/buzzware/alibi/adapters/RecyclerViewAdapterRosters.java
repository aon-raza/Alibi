package com.buzzware.alibi.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.buzzware.alibi.R;
import com.buzzware.alibi.databinding.LayoutItemsEventsBinding;
import com.buzzware.alibi.databinding.LayoutItemsRostersBinding;

import java.util.List;


public class RecyclerViewAdapterRosters extends RecyclerView.Adapter<RecyclerViewAdapterRosters.ViewHolder>  {

    private static final String TAG = "RCA_C_Orders";

    private List<String> list;
    private Context mContext;

    public RecyclerViewAdapterRosters(Context mContext, List<String> list) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutItemsRostersBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder : called.");

        if((i%2) == 1){
            viewHolder.binding.rosterNameTV.setText("Going Away");
            viewHolder.binding.membersTV.setText("36 Members");
            viewHolder.binding.rosterTypeTV.setText("Private");
            viewHolder.binding.rosterTypeTV.setBackground(mContext.getResources().getDrawable(R.drawable.rounded_background_light_red));
            viewHolder.binding.rosterTypeTV.setTextColor(mContext.getResources().getColor(R.color.red));

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

        LayoutItemsRostersBinding binding;

        public ViewHolder(@NonNull LayoutItemsRostersBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
