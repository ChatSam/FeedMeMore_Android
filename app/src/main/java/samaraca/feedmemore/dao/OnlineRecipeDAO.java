package samaraca.feedmemore.dao;

import com.feedmemore.dto.Ingredient;
import com.feedmemore.dto.Recipe;
import com.feedmemore.dto.RecipeResult;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ChatSam on 8/1/15.
 * Gets the recipes from Pearson Kitchen Manager API
 */
public class OnlineRecipeDAO implements IRecipeDAO{


    //private JSONObject recipeJSONObject;

    @Override
    public ArrayList<RecipeResult> fetchRecipes(String grocerySearchString) {

        ArrayList<RecipeResult> recievedRecipes = new ArrayList<>();

        // the request used to get raw data from web service
        String requestUri = "http://api.pearson.com:80/kitchen-manager/v1/recipes?ingredients-all="+ grocerySearchString;

        //refering to the lower level NetworkDAO
        NetworkDAO networkDAO = new NetworkDAO();

        try {
            //make the request
            String rawRecipeData =  networkDAO.Request(requestUri);

            //Pass the data into an JSON object
            JSONObject recipeJSONObject = new JSONObject(rawRecipeData);

            //get data from the array 'results' in the JSON object
            JSONArray recipeJSONArray = recipeJSONObject.getJSONArray("results");

            for (int i=0; i< recipeJSONArray.length() ; i ++){

                RecipeResult recipeResult = new RecipeResult();

                //Get single json object in the 'results' array
                JSONObject jsonObject = recipeJSONArray.getJSONObject(i);

                //getting the recipe Id
                String id = jsonObject.getString("id");
                recipeResult.setId(id);

                //getting the recipe name
                String name = jsonObject.getString("name");
                recipeResult.setRecipeName(name);

                //getting the url of the recipe details
                String recipeUrl = jsonObject.getString("url");
                recipeResult.setRecipeDetailsUrl(recipeUrl);

                //insert the retrived data in to the results array
                recievedRecipes.add(recipeResult);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
             NetworkDAO networkDAO = new NetworkDAO();

            try {
                String response = networkDAO.Request(requestUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(context,"No network connection available.",Toast.LENGTH_LONG).show();
        }

        //this return statement is faulty
        return null;
        */

        return recievedRecipes;
    }


    public Recipe fetchRecipeDetails(String recipeId) {
        Recipe recipeDetail = new Recipe();

        // the request used to get raw data from web service
        String requestUri = "https://api.pearson.com/kitchen-manager/v1/recipes/"+ recipeId;

        //referring to the lower level NetworkDAO
        NetworkDAO networkDAO = new NetworkDAO();

        //get data from the array 'results' in the JSON object
        try {
            //make the request
            String rawRecipeDetailData =  networkDAO.Request(requestUri);

            //Pass the data into an JSON object
            JSONObject recipeDetailsSONObject = new JSONObject(rawRecipeDetailData);

            //Recipe recipeDetail = new Recipe();

            //getting the recipe Id
            String id = recipeDetailsSONObject.getString("id");

            //setting the recipe Id
            recipeDetail.setId(id);

            //getting the recipe name
            String name = recipeDetailsSONObject.getString("name");

            recipeDetail.setRecipeName(name);

            //getting the url of the recipe details
            String recipeUrl = recipeDetailsSONObject.getString("url");

            recipeDetail.setRecipeDetailsUrl(recipeUrl);

            //getting the cooking directions of the recipe

            JSONArray directionsJSON = recipeDetailsSONObject.getJSONArray("directions");

            ArrayList<String> directions = new ArrayList<>();

            for (int l=0; l<directionsJSON.length(); l++){

               String directionsObject = String.valueOf(directionsJSON.get(l));

                directions.add(directionsObject);
            }

            recipeDetail.setDirections(directions);

            //getting the image url of the recipe
            String imageUrl = recipeDetailsSONObject.getString("image");

            recipeDetail.setImageUrl(imageUrl);

            // Array list which holds the ingredients;
            JSONArray ingredientsJSONArray = recipeDetailsSONObject.getJSONArray("ingredients");

            ArrayList<Ingredient> ingredients = new ArrayList<>();

            for (int i=0; i< ingredientsJSONArray.length() ; i ++){


                Ingredient ingredient = new Ingredient();

                //get the ingredients array from the JSON object
                JSONObject jsonIngredientObject = ingredientsJSONArray.getJSONObject(i);

                //getting the name of the ingredient
                String ingredientName = jsonIngredientObject.getString("name");

                //setting
                ingredient.setName(ingredientName);

                //getting the quantity
                String quantity = jsonIngredientObject.getString("quantity");

                //setting
                ingredient.setQuantity(quantity);

                //getting the units of the ingredient
                String unit = jsonIngredientObject.getString("unit");

                //setting
                ingredient.setUnit(unit);

                //all all the ingredients to the array
                ingredients.add(ingredient);
            }

            recipeDetail.setIngredients(ingredients);

        } catch (JSONException e) {
            e.getMessage();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return recipeDetail;
    }


}
