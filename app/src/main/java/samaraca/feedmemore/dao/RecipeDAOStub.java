package samaraca.feedmemore.dao;

import com.feedmemore.dto.Ingredient;
import com.feedmemore.dto.Recipe;
import com.feedmemore.dto.RecipeResult;

import java.util.ArrayList;

/**
 * Created by ChatSam on 8/1/15.
 */
public class RecipeDAOStub implements IRecipeDAO {

    /**
     * dummy data to replicate a web service
     * @param
     * @return
     */
    @Override
    public ArrayList<RecipeResult> fetchRecipes(String grocerySearchString) {
        ArrayList<RecipeResult> result = new ArrayList<>();

        RecipeResult dummyResult = new RecipeResult();

        ArrayList<Ingredient> ingredients = new ArrayList<>();

        Ingredient pepper = new Ingredient("Pepper","2","teaspoons");
        ingredients.add(pepper);

        Ingredient chicken = new Ingredient("Chicken","1","Kilograms");
        ingredients.add(chicken);

        String steps = "Sauté the chicken with cooking oil and add pepper";

        dummyResult.setId("lumprais");
        dummyResult.setRecipeName("Lumprais");
        dummyResult.setRecipeDetailsUrl(steps);

        result.add(dummyResult);

        return result;
    }

    @Override
    public Recipe fetchRecipeDetails(String grocerySearchString) {
        return null;
    }
}
