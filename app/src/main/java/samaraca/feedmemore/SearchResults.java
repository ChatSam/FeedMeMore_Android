package samaraca.feedmemore;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.feedmemore.dto.Ingredient;
import com.feedmemore.dto.RecipeResult;

import java.util.ArrayList;


public class SearchResults extends ActionBarActivity {

    public static final String RECIPE_NAME = "RECIPE_NAME";
    private ListView recipeSearchResultList;
    private String groceryItemArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        //list which shows the recipe search results
        recipeSearchResultList = (ListView) findViewById(R.id.lstRecipeResults);

        //array which contains all the groceries
        groceryItemArray = getIntent().getStringExtra(QuickMeal.GROCERIES);

        //creating a thread to load data from the service
        LoadRecipeResults loadResults = new LoadRecipeResults();

        //running the thread
        loadResults.execute();

        ListItemClicked();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_results, menu);
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

    //dummy data to replicate a web service
    public RecipeResult insertDummyData(){
        RecipeResult dummyResult = new RecipeResult();

        ArrayList<Ingredient> ingredients = new ArrayList<>();

        Ingredient pepper = new Ingredient("Pepper","2","teaspoons");
        ingredients.add(pepper);

        Ingredient chicken = new Ingredient("Chicken","1","Kilograms");
        ingredients.add(chicken);

        String steps = "Sauté the chicken with cooking oil and add pepper";

        dummyResult.setRecipeName("Lumprais");
        dummyResult.setIngredients(ingredients);
        dummyResult.setSteps(steps);

        return dummyResult;

    }


    //converts the groceries array to single string with comma seperated values
    private String convertToSearchString(ArrayList<String> groceryItemArray){

        String groceryItemString = null;

        for(int i = 0; i < groceryItemArray.size();i++) {
            if(i==0){
                groceryItemString = groceryItemArray.get(i);
            }
            else if(i>0) {
                groceryItemString = groceryItemString + "," + groceryItemArray.get(i);
            }
        }
        return groceryItemString;
    }

    //class which handles multi threading
    // loads recipe data from the web service
    class LoadRecipeResults extends AsyncTask<String,Integer,ArrayList<RecipeResult>>{

        @Override
        protected ArrayList<RecipeResult> doInBackground(String... grocerySearchString) {

            ArrayList<RecipeResult>resultList = new ArrayList<RecipeResult>();

            RecipeResult result = new RecipeResult();

            result = insertDummyData();

            resultList.add(result);

            return resultList;
        }

        @Override
        protected void onPostExecute(ArrayList<RecipeResult> recipeResults) {

            ArrayList<String> recipeNameList = new ArrayList<String>();

            for(int i = 0; i < recipeResults.size(); i++){

                String recipeName = recipeResults.get(i).getRecipeName();

                recipeNameList.add(recipeName);
            }

            ArrayAdapter<String> resultView;

            resultView = new ArrayAdapter<String>(SearchResults.this,android.R.layout.simple_list_item_1,recipeNameList);

            recipeSearchResultList.setAdapter(resultView);

        }

    }

    //switches to the Recipe View activity when a list item is clicked.
    private void ListItemClicked() {
        recipeSearchResultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                TextView recipeItem = (TextView) viewClicked;

                final String recipeItemString = recipeItem.getText().toString();

                Intent recipeViewIntent = new Intent(SearchResults.this,RecipeView.class);

                recipeViewIntent.putExtra(RECIPE_NAME, recipeItemString);

                startActivity(recipeViewIntent);
            }
        });
    }
}
