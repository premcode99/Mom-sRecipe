package com.example.momsrecipes.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.momsrecipes.R;
import com.example.momsrecipes.adapters.RecipeAdapter;
import com.example.momsrecipes.model.Recipe;
import com.example.momsrecipes.utils.NetworkFetcher;
import com.example.momsrecipes.utils.RecipeListener;
import com.example.momsrecipes.utils.URL;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements RecipeListener {

    private RecyclerView recipeList;
    private ProgressBar progressBar;
    private SearchView searchView;
    private TextView stateTextView;

    private Toast toast;
    private RecipeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);

        progressBar = findViewById(R.id.progressIndicator);
        searchView = findViewById(R.id.searchView);
        stateTextView = findViewById(R.id.stateTV);

        recipeList = findViewById(R.id.recipeList);
        recipeList.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecipeAdapter(this);
        recipeList.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.trim().length() > 3) {
                    findRecipe(query.trim());
                } else {
                    showToast("Please insert valid name.");
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //not required
                return false;
            }
        });
        //get focus by default
        getFocus();
    }

    private void getFocus() {
        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchView, InputMethodManager.SHOW_IMPLICIT);
    }

    private void findRecipe(String query) {
        new FetchTask().execute(query);
    }

    private void updateList(List<Recipe> list) {

        progressBar.setVisibility(View.GONE);
        stateTextView.setVisibility(View.GONE);
        recipeList.setVisibility(View.VISIBLE);
        adapter.updateList(list);

    }

    public void expand(View view) {
        //this will expand the search view when user click anywhere on searchView
        searchView.onActionViewExpanded();
    }

    private void setState(int i) {

        stateTextView.setVisibility(View.VISIBLE);
        recipeList.setVisibility(View.GONE);

        switch (i) {
            case 0: //Network error
                stateTextView.setText(getResources().getString(R.string.network_error));
                break;
            case 1: //result length zero
                stateTextView.setText(getResources().getString(R.string.no_recipe_found));
                break;
        }
    }

    private void showToast(String message) {

        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();

    }

    @Override
    public void recipeClicked(Recipe recipe) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("recipe", recipe);
        startActivity(intent);
    }

    class FetchTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
            stateTextView.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... strings) {

            String query = strings[0].trim();
            String fetchURL = URL.SEARCH + query;
            return NetworkFetcher.makeServiceCall(fetchURL);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressBar.setVisibility(View.GONE);
            if (s == null || s.length() == 0) {
                setState(0);
            } else {
                try {

                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray meals = jsonObject.getJSONArray("meals");

                    if (meals.length() > 0) {
                        Gson gson = new Gson();
                        List<Recipe> recipeList = new ArrayList<>();
                        for (int i = 0; i < meals.length(); i++) {
                            recipeList.add(gson.fromJson(meals.get(i).toString(), Recipe.class));
                        }
                        updateList(recipeList);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    //This will get called when value at meals is null.
                    setState(1);
                }
            }

        }


    }
}
