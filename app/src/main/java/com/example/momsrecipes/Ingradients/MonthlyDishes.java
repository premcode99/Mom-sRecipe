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

public class MonthlyDishes extends AppCompatActivity {

    ImageButton imageButton;
    ArrayList<DailyAlbum> listitem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sabzirecyclerview);

        listitem = new ArrayList<>();
        ;
        listitem.add(new DailyAlbum("Chiken",R.drawable.chicken));
        listitem.add(new DailyAlbum("Capsicum",R.drawable.capsicum));
        listitem.add(new DailyAlbum("Sauce",R.drawable.sauce));
        listitem.add(new DailyAlbum("Lattuce",R.drawable.lattuce));
        listitem.add(new DailyAlbum("Onion",R.drawable.onion));
        listitem.add(new DailyAlbum("Cheese",R.drawable.cheese));
        listitem.add(new DailyAlbum("Bun",R.drawable.bun));
        listitem.add(new DailyAlbum("Dough",R.drawable.dough));
        listitem.add(new DailyAlbum("Yeast",R.drawable.yeast));
        listitem.add(new DailyAlbum("Pepperoni",R.drawable.pepperoni));
        listitem.add(new DailyAlbum("Bacon",R.drawable.baccon));
        listitem.add(new DailyAlbum("Black Olive",R.drawable.blackolive));
        listitem.add(new DailyAlbum("Avocado",R.drawable.avocado));
        listitem.add(new DailyAlbum("Mushroom",R.drawable.mushroom));
        listitem.add(new DailyAlbum("Sausage",R.drawable.sausage));
        listitem.add(new DailyAlbum("Potato",R.drawable.potato));
        listitem.add(new DailyAlbum("Peas",R.drawable.peas));
        listitem.add(new DailyAlbum("Carrot",R.drawable.carrot));

        RecyclerView recyclerView = findViewById(R.id.recycler_viewdailydish);
        DailydishesAdapter dailydishesAdapter = new DailydishesAdapter(this,listitem);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(dailydishesAdapter);

        dailydishesAdapter.setOnItemClickListener(new DailydishesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MonthlyDishes.this,"ITEM" + position, Toast.LENGTH_SHORT).show();
            }
        });

        imageButton = findViewById(R.id.imagebuttonnext);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MonthlyDishes.this, HomeActivity.class));
            }
        });
    }
}
