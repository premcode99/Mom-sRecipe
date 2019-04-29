package com.example.momsrecipes.Ingradients;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.momsrecipes.R;
import com.example.momsrecipes.RecyclerViewAdapter.RecyclerViewAdapter;

import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.List;

public class DailydishesAdapter extends RecyclerView.Adapter<DailydishesAdapter.MyViewHolder>{

    private OnItemClickListener mlistener;
      private Context mContext;
      private List<DailyAlbum> mData;
      public interface OnItemClickListener{

          void onItemClick(int position);
      }
      public void setOnItemClickListener(OnItemClickListener listener)
      {
          mlistener = listener;
      }


      public DailydishesAdapter(Context mContext,ArrayList<DailyAlbum> mData)
      {
          this.mContext = mContext;
          this.mData = mData;
      }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.onecardviewdaily,viewGroup,false);
        return new MyViewHolder(view,mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
          DailyAlbum dailyAlbum = mData.get(i);
          myViewHolder.ingradientname.setText(dailyAlbum.getName());
          myViewHolder.imgingradient.setImageResource(dailyAlbum.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ingradientname;
        ImageView imgingradient;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            ingradientname = itemView.findViewById(R.id.ingdishname);
            imgingradient = itemView.findViewById(R.id.ingdishphoto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     if(listener != null)
                     {
                         int position = getAdapterPosition();

                         Snackbar.make(v, "Click detected on item " + position,
                                 Snackbar.LENGTH_LONG)
                                 .setAction("Action", null).show();
                         if(position != RecyclerView.NO_POSITION)
                         {
                             listener.onItemClick(position);
                         }
                     }
                }
            });
        }
    }
}
