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


import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipeList;
    private Context context;
    private RecipeListener recipeListener;

    public RecipeAdapter(Context context) {
        this.context = context;
        this.recipeListener = (RecipeListener) context;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.recipe_list_item, viewGroup, false);
        return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int position) {

        final Recipe recipe = recipeList.get(position);

        recipeViewHolder.recipeName.setText(recipe.getStrMeal());
        Glide.with(context).load(recipe.getStrMealThumb()).into(recipeViewHolder.recipeImage);
        recipeViewHolder.recipeType.setText(" "+recipe.getStrCategory() + ", "+recipe.getStrArea());

        recipeViewHolder.recipeImage.setOnClickListener(new View.OnClickListener() {
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

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        private TextView recipeName;
        private ImageView recipeImage;
        private TextView recipeType;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipeNameTV);
            recipeImage = itemView.findViewById(R.id.recipeImage);
            recipeType = itemView.findViewById(R.id.typeTextView);
        }
    }
}
