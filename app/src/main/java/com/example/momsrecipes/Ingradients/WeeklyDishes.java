package com.example.momsrecipes.Ingradients;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.momsrecipes.R;
import com.example.momsrecipes.RecyclerViewAdapter.RecyclerViewAdapter;
import com.example.momsrecipes.activity.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class WeeklyDishes extends AppCompatActivity {

    ImageButton imageButton;
    ArrayList<DailyAlbum> listitem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sabzirecyclerview);

        listitem = new ArrayList<>();
        listitem.add(new DailyAlbum("Tomato",R.drawable.tomato));
        listitem.add(new DailyAlbum("Potato",R.drawable.potato));
        listitem.add(new DailyAlbum("Peas",R.drawable.peas));
        listitem.add(new DailyAlbum("Carrot",R.drawable.carrot));
        listitem.add(new DailyAlbum("Cabbage",R.drawable.cabbage));
        listitem.add(new DailyAlbum("Capsicum",R.drawable.capsicum));
        listitem.add(new DailyAlbum("Oats",R.drawable.oats));
        listitem.add(new DailyAlbum("Toordal",R.drawable.toordal));
        listitem.add(new DailyAlbum("ChanaDal",R.drawable.chanadal));
        listitem.add(new DailyAlbum("UradDal",R.drawable.uraddal));
        listitem.add(new DailyAlbum("Coriender",R.drawable.coriender));
        listitem.add(new DailyAlbum("Methi",R.drawable.methi));
        listitem.add(new DailyAlbum("Suji",R.drawable.suji));
        listitem.add(new DailyAlbum("Saviya",R.drawable.saviya));
        listitem.add(new DailyAlbum("Poha",R.drawable.poha));
        listitem.add(new DailyAlbum("Coconut",R.drawable.coconut));
        listitem.add(new DailyAlbum("Bread",R.drawable.bread));
        listitem.add(new DailyAlbum("Eggs",R.drawable.eggs));



        RecyclerView recyclerView = findViewById(R.id.recycler_viewdailydish);
        DailydishesAdapter dailydishesAdapter = new DailydishesAdapter(this,listitem);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(dailydishesAdapter);

        dailydishesAdapter.setOnItemClickListener(new DailydishesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(WeeklyDishes.this,"ITEM" + position, Toast.LENGTH_SHORT).show();
            }
        });

        imageButton = findViewById(R.id.imagebuttonnext);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WeeklyDishes.this, HomeActivity.class));
            }
        });
    }
}
