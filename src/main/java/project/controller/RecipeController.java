package project.controller;


import project.errors.NullPointerException;
import project.errors.PersistenceException;
import project.entity.Ingridient;
import project.entity.Product;
import project.entity.Recipe;
import project.entity.Tag;
import project.service.IngridientService;
import project.service.ProductService;
import project.service.RecipeService;
import project.service.TagService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@CrossOrigin
@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Value("${upload.path}")
    private String uploadPath;

    private static Logger logger = LogManager.getLogger();

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private TagService tagService;


    @Autowired
    private ProductService productService;

    @Autowired
    private IngridientService ingridientService;

    @CrossOrigin
    @GetMapping("/all")
    public List<Recipe> showAllRecipe() throws IOException {
        logger.info("Attempt to load all recipes");
        List<Recipe> recipes = recipeService.getAllRecipe();
        logger.info("Recipes was loaded");
        return recipes;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable int id) throws Exception {
        logger.info("Attemp to get recipe with id= " + id);
        Recipe recipe = recipeService.getRecipe(id);
        logger.info("Recipe with id= " + id + " was loaded");
        return recipe;
    }

    /*@RequestMapping(path = "/name/{name}")*/
    @GetMapping("/Test/name/{name}")
    public List<Recipe> getByName(@PathVariable("name") String name) throws Exception {
        logger.info("Attempt to search recipes with parameter " + name);
        if (name == "") {
            logger.warn("Пустое имя");
            throw new NullPointerException("Пустое имя");
        }
        List<Recipe> recipes = recipeService.findAllByRecipeName(name);

        List<Tag> tags = tagService.findAllByTagName(name);
        if (tags.size() > 0) {
            Tag search = tags.get(0);
            List<Recipe> recipes1 = recipeService.findAllByTagListContaining(search);
            for (Recipe recipe : recipes1
            ) {
                recipes.add(recipe);
            }
        }
        logger.info("Recipes was loaded " + name);
        return recipes;
    }


    @CrossOrigin
    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        logger.info("Attempt to set file");
        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String result = uuidFile + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + result));
            logger.info("File " + result + " was loaded");
            return String.format(result);
        }

        return String.format("File is not uploaded ");
    }

    @CrossOrigin
    @PostMapping("/")
    public int addNewRecipe(@RequestBody Recipe recipe) throws Exception {
        if (recipe == null) {
            logger.error("Введен пустой рецепт");
            throw new NullPointerException("Введен пустой рецепт");
        }
        if (recipe.getRecipeName() == null || recipe.getRecipeName().isEmpty()) {
            logger.error("Введено пустое имя");
            throw new NullPointerException("Введено пустое имя");
        }
        if (recipe.getRecipeDesc() == null || recipe.getRecipeDesc().isEmpty()) {
            logger.error("Введено пустое описание");
            throw new NullPointerException("Введено пустое описание");
        }
        if (recipe.getRecipeDesc().length() > 5000) {
            logger.error("Слишком большое описание");
            throw new PersistenceException("Слишком большое описание");
        }
        if (recipe.getRecipeName().length() > 50) {
            logger.error("Слишком большое имя");
            throw new PersistenceException("Слишком большое имя");
        }
        if (recipe.getRecipeImgPath().length() > 200) {
            logger.error("Слишком большое имя файла");
            throw new PersistenceException("Слишком большое имя файла");
        }
        if (recipe.getRecipeImgPath() == null) {
            throw new NullPointerException("Нет картинки");
        }
        Integer test = recipe.getRecipeTime();
        if (test == 0 || test == null) {
            throw new NullPointerException("Не указано время приготовления");
        }
        recipeService.saveRecipe(recipe);
        return recipe.getIdRecipe();
    }

    @CrossOrigin
    @PutMapping("/")
    public int updateRecipe(@RequestBody Recipe recipe) {
        if (recipe == null) {
            logger.error("Введен пустой рецепт");
            throw new NullPointerException("Введен пустой рецепт");
        }
        if (recipe.getRecipeName() == null || recipe.getRecipeName().isEmpty()) {
            logger.error("Введено пустое имя");
            throw new NullPointerException("Введено пустое имя");
        }
        if (recipe.getRecipeDesc() == null || recipe.getRecipeDesc().isEmpty()) {
            logger.error("Введено пустое описание");
            throw new NullPointerException("Введено пустое описание");
        }
        if (recipe.getRecipeDesc().length() > 5000) {
            logger.error("Слишком большое описание");
            throw new PersistenceException("Слишком большое описание");
        }
        if (recipe.getRecipeName().length() > 50) {
            logger.error("Слишком большое имя");
            throw new PersistenceException("Слишком большое имя");
        }
        if (recipe.getRecipeImgPath().length() > 200) {
            logger.error("Слишком большое имя файла");
            throw new PersistenceException("Слишком большое имя файла");
        }
        if (recipe.getRecipeImgPath() == null||recipe.getRecipeImgPath().isEmpty()) {
            throw new NullPointerException("Нет картинки");
        }
        Integer test = recipe.getRecipeTime();
        if (test == 0 || test == null) {
            throw new NullPointerException("Не указано время приготовления");
        }

        recipeService.saveRecipe(recipe);
        return recipe.getIdRecipe();
    }

    @PostMapping("/{prodid}/{weight}")
    public Ingridient ProdIng(@PathVariable int prodid, @PathVariable int weight) throws Exception {
        Product product = productService.getProduct(prodid);

        Ingridient ingridient = new Ingridient(weight);
        ingridient.setProduct(product);

        productService.saveProduct(product);
        ingridientService.saveIngridient(ingridient);
        return ingridient;
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable int id) {
        recipeService.deleteRecipe(id);
    }

    @GetMapping("/{id}/showIngridients")
    public List<Ingridient> showIngridient(@PathVariable int id) throws Exception {
        Recipe recipe = recipeService.getRecipe(id);
        return recipe.getIngridientList();
    }

    @CrossOrigin
    @PostMapping("/ingrid/{id}")
    public Recipe addIng(@RequestBody Ingridient ing, @PathVariable int id) throws Exception {
        logger.info("Add Ingridient to recipe" + id);
        Recipe recipe = recipeService.getRecipe(id);
        recipe.addIngridients(ing);
        recipeService.saveRecipe(recipe);
        return recipe;
    }

    @PostMapping("/tag/add/{id}")
    public Recipe addTag(@PathVariable int id, @RequestBody Tag tag) throws Exception {
        logger.info("Добавление тега "+ tag.getTagName()+" в рецепт" + id);
        Recipe recipe = recipeService.getRecipe(id);
        List<Tag> tag1=tagService.findAllByTagName(tag.getTagName());
        recipe.addTag(tag1.get(0));
        recipeService.saveRecipe(recipe);
        return recipe;
    }


    @PostMapping("/tag/add/{id}/{name}")
    public Recipe addTag(@PathVariable int id, @PathVariable String name) throws Exception {

        Recipe recipe = recipeService.getRecipe(id);
        List<Tag> tag=tagService.findAllByTagName(name);
        recipe.addTag(tag.get(0));
        recipeService.saveRecipe(recipe);
        return recipe;
    }



    @CrossOrigin
    @GetMapping("/tag/{tag}")
    public Tag returnTag(@PathVariable String tag) {
        logger.info("Поиск тега с именем="+tag);
        List<Tag> tags = tagService.findAllByTagName(tag);

        if (tags.size() != 0) {
            Tag tagUnit = tags.get(0);
            logger.info("Тег был найден");
            return tagUnit;
        }
        else {
            Tag tagUnit = new Tag();
            tagUnit.setTagName(tag);
            tagService.saveTag(tagUnit);
            logger.info("Тег был создан");
            return tagUnit;
        }

    }

/*
    @GetMapping("/tag/Test/name/{tag}")
    public List<Recipe> findTagi(@PathVariable String tag) {
        List<Tag> tags = tagService.getByName(tag);
        Tag tagUnit = null;
        if (tags.size() != 0) {
            tagUnit = tags.get(0);
        } else {
            tagUnit = new Tag();
            tagUnit.setTagName(tag);
            tagService.saveTag(tagUnit);
        }
        List<Recipe> recipes = recipeService.findAllByTagListContaining(tagUnit);
        return recipes;
    }*/

}
