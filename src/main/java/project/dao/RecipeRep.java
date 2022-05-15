package project.dao;


import project.entity.Recipe;
import project.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRep extends JpaRepository<Recipe, Integer> {
    public List<Recipe> findAllByRecipeName(String name);

    public List<Recipe> findAllByTagListContaining(Tag tag);

}

