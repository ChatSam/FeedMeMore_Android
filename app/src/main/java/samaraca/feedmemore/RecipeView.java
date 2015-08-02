package samaraca.feedmemore;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.feedmemore.dto.Ingredient;
import com.feedmemore.dto.Recipe;
import com.feedmemore.dto.RecipeResult;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import samaraca.feedmemore.dao.IRecipeDAO;
import samaraca.feedmemore.dao.OnlineRecipeDAO;


public class RecipeView extends ActionBarActivity {

    private String recipeName;
    private TextView txtRecipeName;
    private ListView recipeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_view);

        recipeName = getIntent().getStringExtra(SearchResults.RECIPE_ID);

        txtRecipeName = (TextView) findViewById(R.id.lblRecipeTitle);

        recipeListView = (ListView) findViewById(R.id.lstView);

        LoadRecipeDetails loadRecipeDetails = new LoadRecipeDetails();

        loadRecipeDetails.execute(recipeName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipe_view, menu);
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

    class LoadRecipeDetails extends AsyncTask<String,Integer,Recipe>{


        @Override
        protected Recipe doInBackground(String... recipeIdString) {
            String recipeIdStringUnit =  recipeIdString[0];

            //IRecipeDAO recipeDAO = new RecipeDAOStub();
            IRecipeDAO recipeDAO = new OnlineRecipeDAO();

            Recipe recipeResults = recipeDAO.fetchRecipeDetails(recipeIdStringUnit);

            return recipeResults;
        }

        @Override
        protected void onPostExecute(Recipe recipe) {

            ArrayList<Ingredient> ingredients = recipe.getIngredients();

            ArrayList<String> directions = recipe.getDirections();

            txtRecipeName.setText(recipe.getRecipeName());

            String ingredientDisplay = "Ingredients - ";

            String directionsDisplay = "Directions - ";

            for(int a=0 ; a< ingredients.size(); a++){

                String currentIngredient = ingredients.get(a).getName();

                ingredientDisplay= ingredientDisplay+" , "+currentIngredient;

            }

            ArrayList<String> recipeDisplayElementsStrings = new ArrayList<>();

            recipeDisplayElementsStrings.add(ingredientDisplay);

            for(int b=0 ; b< directions.size(); b++){

                String currentDirection = directions.get(b);

                directionsDisplay= directionsDisplay+" \n "+currentDirection;

                recipeDisplayElementsStrings.add(currentDirection);

            }



          /*  TextView ingredientView = (TextView) findViewById(R.id.lblIngredients);
            ingredientView.setText(ingredientDisplay);

            TextView directionsView = (TextView) findViewById(R.id.lblDirections);
            directionsView.setText(directionsDisplay);
        */
            ArrayAdapter<String> resultView;

            resultView = new ArrayAdapter<String>(RecipeView.this,android.R.layout.simple_list_item_1,recipeDisplayElementsStrings);

            recipeListView.setAdapter(resultView);




            



         /*   ImageView recipeImage = (ImageView) findViewById(R.id.imgRecipeImage);

            try {
                 URL imageUrl = new URL(recipe.getImageUrl());

                 Bitmap bmp = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());

                 recipeImage.setImageBitmap(bmp);


            }  catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            */

        }
    }


}
