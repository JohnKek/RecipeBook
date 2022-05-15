package project.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tag")
    private int id;
    @Column(name = "tag_name")
    private String tagName;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "recipe_tag",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private List<Recipe> recipeList;


    public void addRecipe(Recipe recipe){
        if(recipeList==null){
            recipeList=new ArrayList<>();
        }
        recipeList.add(recipe);
    }
/*
    public List<Recipe> getRecipeList() {
        return recipeList;
    }*/

    public Tag() {
    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tag_name) {
        this.tagName = tag_name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tag_name=" + tagName +
                '}';
    }
}
