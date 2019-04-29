package com.example.momsrecipes.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.momsrecipes.R;
import com.example.momsrecipes.model.Recipe;
import com.example.momsrecipes.utils.RecipeListener;


import java.util.ArrayList;
import java.util.List;

public class LatestAdapter extends RecyclerView.Adapter<LatestAdapter.LatestViewHolder> {

    private Context context;
    private RecipeListener recipeListener;
    private List<Recipe> recipeList = new ArrayList<>();


    public LatestAdapter(Context context) {
        this.context = context;
        recipeListener = (RecipeListener) context;
    }

    @NonNull
    @Override
    public LatestViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.latest_list_item, viewGroup, false);
        return new LatestViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LatestViewHolder latestViewHolder, int i) {

        final Recipe recipe = recipeList.get(i);

        Glide.with(context).load(recipe.getStrMealThumb()).into(latestViewHolder.recipeImage);
        latestViewHolder.nameTextView.setText(recipe.getStrMeal());
        latestViewHolder.areaTextView.setText(recipe.getStrArea());

        latestViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeListener.recipeClicked(recipe);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList == null ? 0 : recipeList.size();
    }

    public void updateList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
        notifyDataSetChanged();
    }

    public class LatestViewHolder extends RecyclerView.ViewHolder {

        private ImageView recipeImage;
        private TextView nameTextView, areaTextView;

        public LatestViewHolder(@NonNull View itemView) {
            super(itemView);

            recipeImage = itemView.findViewById(R.id.latestImageView);
            nameTextView = itemView.findViewById(R.id.latestMealTV);
            areaTextView = itemView.findViewById(R.id.latestAreaTV);

        }
    }
}
