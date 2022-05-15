package project.service;


import project.errors.EntityNotFoundException;
import project.dao.RecipeRep;
import project.entity.Recipe;
import project.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImp implements RecipeService {
    @Autowired
    private RecipeRep recipeRep;

    @Override
    public List<Recipe> getAllRecipe() {
        return recipeRep.findAll();
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        recipeRep.save(recipe);
    }


    @Override
    public Recipe getRecipe(int id) throws Exception {

        Optional<Recipe> optional = recipeRep.findById(id);

        //*Recipe recipe = null;*//*

        if (optional.isPresent()) {
            Recipe recipe = optional.get();

            return recipe;
        } else {
            throw new EntityNotFoundException("Объект не найден");
        }

    }


    @Override
    public void deleteRecipe(int id) {

        recipeRep.deleteById(id);
    }

    /* поправить*/
    @Override
    public List<Recipe> getByName(String name) {
        /*List<Recipe> recipes = recipeRep.findAll();
        Iterator<Recipe> recipeIterator = recipes.iterator();
        while (recipeIterator.hasNext()) {
            Recipe recipe = recipeIterator.next();
            if (!recipe.getRecipeName().equals(name)) {
                recipeIterator.remove();
            }
        }
        System.out.println(recipes);
        return recipes;*/
        List<Recipe> recipes = recipeRep.findAllByRecipeName(name);
        return recipes;
    }

    @Override
    public List<Recipe> findAllByRecipeName(String name) {
        List<Recipe> recipes = recipeRep.findAllByRecipeName(name);
        return recipes;
    }

    @Override
    public List<Recipe> findAllByTagListContaining(Tag tag) {
        List<Recipe> recipes = recipeRep.findAllByTagListContaining(tag);
        return recipes;
    }


}
