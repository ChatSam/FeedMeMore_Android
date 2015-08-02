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

import com.feedmemore.dto.RecipeResult;

import java.util.ArrayList;

import samaraca.feedmemore.dao.IRecipeDAO;
import samaraca.feedmemore.dao.OnlineRecipeDAO;
import samaraca.feedmemore.dao.RecipeDAOStub;


public class SearchResults extends ActionBarActivity {

    public static final String RECIPE_ID = "RECIPE_ID";
    private ListView recipeSearchResultList;
    private ArrayList<String> groceryItemArray;
    private ArrayList<RecipeResult> recipeResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        //list which shows the recipe search results
        recipeSearchResultList = (ListView) findViewById(R.id.lstRecipeResults);

        //array which contains all the groceries
        groceryItemArray = getIntent().getStringArrayListExtra(QuickMeal.GROCERIES);

        //creating a thread to load data from the service
        LoadRecipeResults loadResults = new LoadRecipeResults();

        String searchString = convertToSearchString(groceryItemArray);

        //running the thread
        loadResults.execute(searchString);

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

            String grocerySearchStringUnit =  grocerySearchString[0];

            ArrayList<RecipeResult>resultList = new ArrayList<RecipeResult>();

            //IRecipeDAO recipeDAO = new RecipeDAOStub();
            IRecipeDAO recipeDAO = new OnlineRecipeDAO();

            ArrayList<RecipeResult> recipeResults = recipeDAO.fetchRecipes(grocerySearchStringUnit);

            return recipeResults;
        }

        @Override
        protected void onPostExecute(ArrayList<RecipeResult> recipeResults) {

            SearchResults.this.recipeResults = recipeResults;

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


                for(int i = 0; i < recipeResults.size();i++){
                    String recipeNameFromResults = recipeResults.get(i).getRecipeName();

                    if(recipeNameFromResults.equalsIgnoreCase(recipeItemString)){

                        String recipeId = recipeResults.get(i).getId();

                        Intent recipeViewIntent = new Intent(SearchResults.this,RecipeView.class);

                        recipeViewIntent.putExtra(RECIPE_ID, recipeId);

                        startActivity(recipeViewIntent);
                    };
                }


            }
        });
    }
}
