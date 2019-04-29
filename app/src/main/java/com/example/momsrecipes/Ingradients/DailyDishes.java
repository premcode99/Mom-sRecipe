package com.example.momsrecipes.Ingradients;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.momsrecipes.MainActivity;
import com.example.momsrecipes.MomContent.Sliding_activity;
import com.example.momsrecipes.R;
import com.example.momsrecipes.RecyclerViewAdapter.RecyclerViewAdapter;
import com.example.momsrecipes.activity.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class DailyDishes extends AppCompatActivity {

   private ArrayList<DailyAlbum> listitem;
   private ImageButton imageButton;
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
        listitem.add(new DailyAlbum("CauliFlower",R.drawable.cauliflower));
        listitem.add(new DailyAlbum("Bringle",R.drawable.bringle));
        listitem.add(new DailyAlbum("Palak",R.drawable.palak));
        listitem.add(new DailyAlbum("Karele",R.drawable.karele));
        listitem.add(new DailyAlbum("Coriender",R.drawable.coriender));
        listitem.add(new DailyAlbum("Paneer",R.drawable.paneer));
        listitem.add(new DailyAlbum("Garlic",R.drawable.garlic));
        listitem.add(new DailyAlbum("Ginger",R.drawable.ginger));
        listitem.add(new DailyAlbum("LadyFinger",R.drawable.ladyfinger));



        RecyclerView recyclerView = findViewById(R.id.recycler_viewdailydish);
        DailydishesAdapter dailydishesAdapter = new DailydishesAdapter(this,listitem);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(dailydishesAdapter);

       dailydishesAdapter.setOnItemClickListener(new DailydishesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(DailyDishes.this,"ITEM" + position, Toast.LENGTH_SHORT).show();
            }
        });
        imageButton = findViewById(R.id.imagebuttonnext);
       imageButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(DailyDishes.this, HomeActivity.class));
           }
       });
    }

}
