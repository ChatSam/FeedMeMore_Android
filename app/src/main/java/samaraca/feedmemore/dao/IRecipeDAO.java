package samaraca.feedmemore.dao;

import android.content.Context;

import com.feedmemore.dto.Recipe;
import com.feedmemore.dto.RecipeResult;

import java.util.ArrayList;

/**
 * Created by ChatSam on 8/1/15.
 */
public interface IRecipeDAO {
    ArrayList<RecipeResult> fetchRecipes(String grocerySearchString);

    Recipe fetchRecipeDetails(String grocerySearchString);
}

