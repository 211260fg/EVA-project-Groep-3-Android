package com.example.floriangoeteyn.androidproject3.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.floriangoeteyn.androidproject3.R;
import com.example.floriangoeteyn.androidproject3.adapter.RecipeAdapter;
import com.example.floriangoeteyn.androidproject3.models.Recipe;
import com.example.floriangoeteyn.androidproject3.models.RecipeRepository;
import com.example.floriangoeteyn.androidproject3.rest.RestClient;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class RecipeActivity extends AppCompatActivity implements Callback<List<Recipe>> {

    private RecipeRepository recipeRepository;
    List<Recipe> recipes;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        recipeRepository = new RecipeRepository();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        dialog = ProgressDialog.show(this, "", "loading...");

        RestClient.RecipeApiInterface service= RestClient.getClient();
        Call<List<Recipe>> call=service.getRecipes();
        call.enqueue(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResponse(Response<List<Recipe>> response) {
        dialog.dismiss();
        recipes = response.body();

        RecyclerView rv = (RecyclerView)findViewById(R.id.recipeRecyclerView);
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        rv.setLayoutManager(glm);

        RecipeAdapter adapter = new RecipeAdapter(recipes, this);
        rv.setAdapter(adapter);

    }

    @Override
    public void onFailure(Throwable t) {
        dialog.dismiss();
        new AlertDialog.Builder(this)
                .setTitle("Connectie probleem")
                .setMessage("Verbind met het internet om de recepten op te halen")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getBaseContext(),MainActivity.class));
                    }})
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}