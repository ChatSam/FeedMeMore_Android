package samaraca.feedmemore;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;


public class QuickMeal extends ActionBarActivity {

    public static final String GROCERIES = "GROCERIES";
    private static EditText txtGroceries;
    //private String groceryList;
    public static ArrayList<String> groceryList;
    private static TextView itemCounter;
    private TextView lastItemAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_meal);

        txtGroceries = (EditText) findViewById(R.id.txtGroceries);

        itemCounter = (TextView) findViewById(R.id.txtItemCounter);

        lastItemAdded = (TextView) findViewById(R.id.txtLastItemAdded);

        groceryList = new ArrayList<String>();
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


        String currentItem = txtGroceries.getText().toString();

        //add grocery items to the grocery list
        groceryList.add(currentItem);

        lastItemAdded.setText(txtGroceries.getText().toString());

        txtGroceries.setText("");

        //int itemCounterNumber = Integer.parseInt(itemCounter.getText().toString());

        //itemCounterNumber++;

        //String itemCounterDisplay = String.valueOf(itemCounterNumber);

        //itemCounter.setText(itemCounterDisplay);

        int groceryListSize = groceryList.size();

        itemCounter.setText(String.valueOf(groceryListSize));


    }

    /**
     * Searches for recipes with the provided grocery items
     * @param v
     */
    public void OnClickFindRecipes(View v){

        if(groceryList.isEmpty() == false) {
            Intent recipeIntent = new Intent(this, SearchResults.class);

            recipeIntent.putStringArrayListExtra(GROCERIES, groceryList);

            startActivity(recipeIntent);

        }else{
            Toast.makeText(this,"Enter at least one Grocery item to find a recipe",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Shows the list containing the groceries added.
     * @param v
     */
    public void onClickShowGroceryList(View v){

        Intent groceryListIntent = new Intent(this,GroceryList.class);

        groceryListIntent.putExtra(GROCERIES,groceryList);

        startActivity(groceryListIntent);
    }

    /**
     * Clears the items in the list
     * @param V
     */
    public void onClickClearItems(View V){
        groceryList.clear();

        lastItemAdded.setText("");

        itemCounter.setText("");

    }



    public void scanNow(View view) {
//        Toast.makeText(this,"Button works",Toast.LENGTH_LONG).show();
        IntentIntegrator.initiateScan(this);

//        IntentIntegrator integrator = new IntentIntegrator(yourActivity);
//        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
//        if (scanResult != null) {
//            String re = scanResult.getContents();
//            Log.d("code", re);
////            Toast.makeText(this,"Button works",Toast.LENGTH_LONG).show();
//            // handle scan result
//        }
//        if (requestCode == 0) {
        if (resultCode == RESULT_OK) {
            String contents = intent.getStringExtra("SCAN_RESULT");
            String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

            // Handle successful scan
            Toast toast = Toast.makeText(this, "Content:" + contents + " Format:" + format , Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 25, 400);
            toast.show();
        } else if (resultCode == RESULT_CANCELED) {
            // Handle cancel
            Toast toast = Toast.makeText(this, "Scan was Cancelled!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 25, 400);
            toast.show();

        }

//
//
    }
}
