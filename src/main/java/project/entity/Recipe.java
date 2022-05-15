package project.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "recipe")
public class Recipe {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_recipe")
    private int idRecipe;
    @Column(name = "recipe_name")
    private String recipeName;
    @Column(name = "recipe_time")
    private int recipeTime;
    @Column(name = "recipe_desc")
    private String recipeDesc;
    @Column(name = "energy_value")
    private int energyValue;
    @Column(name = "recipe_img_path")
    private String recipeImgPath;

    //Recipe_ingridients
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "recipe_ingridient",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingridient_id"))
    private List<Ingridient> ingridientList;
    //------------------
    //Recipe_tag
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "recipe_tag",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tagList;

    public Recipe() {
    }

    public void addIngridients(Ingridient ingridient) {
        if (ingridientList == null) {
            ingridientList = new ArrayList<>();
        }
        ingridientList.add(ingridient);
    }

    public List<Ingridient> getIngridientList() {
        return ingridientList;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void addTag(Tag tag) {
        if (tagList == null) {
            tagList = new ArrayList<>();
        }
        tagList.add(tag);
    }

    public Recipe(String RecipeName, int RecipeTime, String RecipeDesc, int EnergyValue, String RecipeImgPath) {
        this.recipeName = RecipeName;
        this.recipeTime = RecipeTime;
        this.recipeDesc = RecipeDesc;
        this.energyValue = EnergyValue;
        this.recipeImgPath = RecipeImgPath;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id_recipe=" + idRecipe +
                ", recipe_name='" + recipeName + '\'' +
                ", recipe_time=" + recipeTime +
                ", recipe_desc='" + recipeDesc + '\'' +
                ", energy_value=" + energyValue +
                ", recipe_img_path='" + recipeImgPath + '\'' +
                '}';
    }

    public int getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(int id_recipe) {
        this.idRecipe = id_recipe;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipe_name) {
        this.recipeName = recipe_name;
    }

    public int getRecipeTime() {
        return recipeTime;
    }

    public void setRecipeTime(int recipe_time) {
        this.recipeTime = recipe_time;
    }

    public String getRecipeDesc() {
        return recipeDesc;
    }

    public void setRecipeDesc(String recipe_desc) {
        this.recipeDesc = recipe_desc;
    }

    public int getEnergyValue() {
        return energyValue;
    }

    public void setEnergyValue(int energy_value) {
        this.energyValue = energy_value;
    }

    public String getRecipeImgPath() {
        return recipeImgPath;
    }

    public void setRecipeImgPath(String recipe_img_path) {
        this.recipeImgPath = recipe_img_path;
    }
}
