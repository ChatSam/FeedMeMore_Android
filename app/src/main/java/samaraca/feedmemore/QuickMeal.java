package samaraca.feedmemore;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class QuickMeal extends ActionBarActivity {

    public static final String GROCERIES = "GROCERIES";
    private static EditText txtGroceries;
    private String groceryList;
    private static TextView itemCounter;
    private TextView lastItemAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_meal);

        txtGroceries = (EditText) findViewById(R.id.txtGroceries);

        itemCounter = (TextView) findViewById(R.id.txtItemCounter);

        lastItemAdded = (TextView) findViewById(R.id.txtLastItemAdded);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quick_meal, menu);
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

    /**
     * Adds each grocery item into a string varaible
     * @param v
     */
    public void OnClickAddItem(View v){

        if(groceryList == null){
            groceryList = txtGroceries.getText().toString();

        }else{
            groceryList = groceryList+","+ txtGroceries.getText().toString();
        }

        lastItemAdded.setText(txtGroceries.getText().toString());

        txtGroceries.setText("");

        int itemCounterNumber = Integer.parseInt(itemCounter.getText().toString());

        itemCounterNumber++;

        String itemCounterDisplay = String.valueOf(itemCounterNumber);

        itemCounter.setText(itemCounterDisplay);


    }

    /**
     * Searches for recipes with the provided grocery items
     * @param v
     */
    public void OnClickFindRecipes(View v){

        if(groceryList == null) {
            Intent recipeIntent = new Intent(this, RecipeView.class);

            recipeIntent.putExtra(GROCERIES, txtGroceries.getText().toString());

            startActivity(recipeIntent);

        }else{
            Toast.makeText(this,"Enter at least one Grocery item to find a recipe",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Shows the list containing the gorceries added.
     * @param v
     */
    public void onClickShowGroceryList(View v){

        Intent groceryListIntent = new Intent(this,GroceryList.class);

        groceryListIntent.putExtra(GROCERIES,groceryList);

        startActivity(groceryListIntent);
    }
}
