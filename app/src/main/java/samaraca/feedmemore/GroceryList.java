package samaraca.feedmemore;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


public class GroceryList extends ActionBarActivity {

    private ListView groceryList;
    private ArrayList<String> groceryArrayList;
    private ArrayList<String> groceryItems;
    private EditText currentGroceries;
    public static ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grocery_list);

        currentGroceries = (EditText) findViewById(R.id.txtGroceries);

        groceryList = (ListView) findViewById(R.id.lstGroceryList);

        //groceryItems = getIntent().getStringExtra(QuickMeal.GROCERIES);

        //get the list of groceries from the Quick Meal Activity
        groceryItems = getIntent().getStringArrayListExtra(QuickMeal.GROCERIES);


        //create an adapter to populate the list with grocery items
        //adapter= new ArrayAdapter<String>(this,R.layout.list_element_container,groceryItems);
        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,groceryItems);

        groceryList.setAdapter(adapter);

        //update the list if new items are added
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grocery_list, menu);
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
     * Adds a grocery item to the list view
     * @param v
     */
    public void onClickAddItemToList(View v){

        String item = currentGroceries.getText().toString();

        //adding items to the grocery list
        groceryItems.add(item);

        QuickMeal.groceryList.add(item);

        //updating the list view
        adapter.notifyDataSetChanged();

        currentGroceries.setText("");


    }

   /* public void populateGroceryList(String gItems){



    }
    */
}
