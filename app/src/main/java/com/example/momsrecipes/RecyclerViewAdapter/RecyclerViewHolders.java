package com.example.momsrecipes.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.momsrecipes.FirebaseAcitvity.LoginActivity;
import com.example.momsrecipes.Ingradients.DailyDishes;
import com.example.momsrecipes.Ingradients.MonthlyDishes;
import com.example.momsrecipes.Ingradients.Sweetsss;
import com.example.momsrecipes.Ingradients.WeeklyDishes;
import com.example.momsrecipes.MainActivity;
import com.example.momsrecipes.MomContent.Sliding_activity;
import com.example.momsrecipes.R;

import static android.support.v4.content.ContextCompat.getCodeCacheDir;
import static android.support.v4.content.ContextCompat.startActivity;


public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView countryName;
    public ImageView countryPhoto;
    public final Context context;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        context = itemView.getContext();
        countryName = (TextView)itemView.findViewById(R.id.ingdishname);
        countryPhoto = (ImageView)itemView.findViewById(R.id.ingdishphoto);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Category Position = " + getPosition(), Toast.LENGTH_SHORT).show();
        final Intent intent;
        if(getPosition()== 1)
        {
         context.startActivity(new Intent(context,DailyDishes.class));
        }
        if(getPosition()==2)
        {
            context.startActivity(new Intent(context, WeeklyDishes.class));
        }
        if(getPosition()==3)
        {
            context.startActivity(new Intent(context, MonthlyDishes.class));
        }
        if(getPosition()==4)
        {
            context.startActivity(new Intent(context, Sweetsss.class));
        }
    }
}