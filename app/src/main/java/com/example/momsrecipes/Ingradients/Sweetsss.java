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
import com.example.momsrecipes.activity.HomeActivity;

import java.util.ArrayList;

public class Sweetsss extends AppCompatActivity {

    ImageButton imageButton;
    private ArrayList<DailyAlbum> listitem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sabzirecyclerview);

        listitem = new ArrayList<>();
        listitem.add(new DailyAlbum("Baking Powder",R.drawable.bakingpowder));
        listitem.add(new DailyAlbum("Besan",R.drawable.besan));
        listitem.add(new DailyAlbum("Butter",R.drawable.butter));
        listitem.add(new DailyAlbum("Carrot",R.drawable.carrot));
        listitem.add(new DailyAlbum("Cashew",R.drawable.cashew));
        listitem.add(new DailyAlbum("Chocolate",R.drawable.chocolate));
        listitem.add(new DailyAlbum("Coconut",R.drawable.coconut));
        listitem.add(new DailyAlbum("Creame",R.drawable.cream));
        listitem.add(new DailyAlbum("Dry Fruits",R.drawable.dryfruits));
        listitem.add(new DailyAlbum("Flour",R.drawable.flour));
        listitem.add(new DailyAlbum("Ghee",R.drawable.ghee));
        listitem.add(new DailyAlbum("Gram Flour",R.drawable.gramflour));
        listitem.add(new DailyAlbum("Khoya",R.drawable.khoya));
        listitem.add(new DailyAlbum("Mawa",R.drawable.mava));
        listitem.add(new DailyAlbum("Milk",R.drawable.milk));
        listitem.add(new DailyAlbum("Rava(Suji)",R.drawable.ravasuji));
        listitem.add(new DailyAlbum("Saffron",R.drawable.saffron));
        listitem.add(new DailyAlbum("Sugar",R.drawable.sugar));
        listitem.add(new DailyAlbum("Vanilla Extract",R.drawable.vanillaextract));



        RecyclerView recyclerView = findViewById(R.id.recycler_viewdailydish);
        DailydishesAdapter dailydishesAdapter = new DailydishesAdapter(this,listitem);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(dailydishesAdapter);

        dailydishesAdapter.setOnItemClickListener(new DailydishesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(Sweetsss.this,"ITEM" + position, Toast.LENGTH_SHORT).show();
            }
        });

        imageButton = findViewById(R.id.imagebuttonnext);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sweetsss.this, HomeActivity.class));
            }
        });
    }

}

