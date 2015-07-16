package samaraca.feedmemore;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


public class GroceryList extends ActionBarActivity {

    //private String groceryItems;
    private ListView groceryList;
    private ArrayList<String> groceryArrayList;
    private ArrayList<String> groceryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grocery_list);

        groceryList = (ListView) findViewById(R.id.lstGroceryList);

        //groceryItems = getIntent().getStringExtra(QuickMeal.GROCERIES);

        groceryItems = getIntent().getStringArrayListExtra(QuickMeal.GROCERIES);

         ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.list_element_container,groceryItems);

        groceryList.setAdapter(adapter);

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


    public void populateGroceryList(String gItems){



    }
}
