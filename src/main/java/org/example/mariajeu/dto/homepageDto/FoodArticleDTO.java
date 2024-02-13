package org.example.mariajeu.dto.homepageDto;

import lombok.Getter;
import lombok.Setter;
import org.example.mariajeu.domain.homepageDomain.Food;
import org.example.mariajeu.domain.homepageDomain.FoodArticle.FoodArticle;
import org.example.mariajeu.domain.homepageDomain.Ingredient;
import org.example.mariajeu.domain.homepageDomain.Wine;
import org.example.mariajeu.domain.homepageDomain.WineType;

import java.util.List;

@Getter
@Setter
public class FoodArticleDTO {
    private Long id;
    private String foodName;
    private String foodEnglishName;
    private String photoUrl;
    private String videoUrl;
    private List<Ingredient> primaryIngredients;
    private List<Ingredient> sauceIngredients;
    private List<String> instructions;

    private String wineName;
    private WineType wineType;
    private int boldness;
    private int acidity;
    private int fizziness;
    private int tannic;
    private int likesCount;

    // 수정된 생성자
    public FoodArticleDTO(Long id, String foodName, String foodEnglishName, String photoUrl, String videoUrl,
                          List<Ingredient> primaryIngredients, List<Ingredient> sauceIngredients, List<String> instructions,
                          String wineName, WineType wineType, int boldness, int acidity, int fizziness, int tannic, int likesCount) {
        this.id = id;
        this.foodName = foodName;
        this.foodEnglishName = foodEnglishName;
        this.photoUrl = photoUrl;
        this.videoUrl = videoUrl;
        this.primaryIngredients = primaryIngredients;
        this.sauceIngredients = sauceIngredients;
        this.instructions = instructions;
        this.wineName = wineName;
        this.wineType = wineType;
        this.boldness = boldness;
        this.acidity = acidity;
        this.fizziness = fizziness;
        this.tannic = tannic;
        this.likesCount = likesCount;
    }

    public FoodArticleDTO(FoodArticle savedFoodArticle) {
    }

    public FoodArticle toEntity() {
        // Food 객체 생성
        Food food = new Food();
        food.setName(this.foodName);
        food.setEnglishName(this.foodEnglishName);
        food.setPhotoUrl(this.photoUrl);
        food.setVideoUrl(this.videoUrl);
        food.setPrimaryIngredients(this.primaryIngredients);
        food.setSauceIngredients(this.sauceIngredients);
        food.setInstructions(this.instructions);


        // Wine 객체 생성
        Wine wine = new Wine();
        wine.setName(this.wineName);
        wine.setType(this.wineType);
        wine.setBoldness(this.boldness);
        wine.setAcidity(this.acidity);
        wine.setFizziness(this.fizziness);
        wine.setTannic(this.tannic);
        // FoodArticle 객체 생성
        FoodArticle foodArticle = new FoodArticle();
        foodArticle.setFood(food);
        foodArticle.setWine(wine);

        return foodArticle;
    }


}
