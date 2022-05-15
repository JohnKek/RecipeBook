package project.service;



import project.entity.Recipe;
import project.entity.Tag;

import java.util.List;

public interface RecipeService {

    public List<Recipe> getAllRecipe();

    public void saveRecipe(Recipe recipe);

    public Recipe getRecipe(int id) throws Exception;

    public void deleteRecipe(int id);

    public List<Recipe> getByName(String name);

    public List<Recipe> findAllByRecipeName(String name);

    public List<Recipe> findAllByTagListContaining(Tag tag);

}
