package org.example.mariajeu.domain.homepageDomain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String englishName;

    @Column
    private String photoUrl;

    @Column
    private String videoUrl;

    @ElementCollection
    @CollectionTable(name = "food_primary_ingredients", joinColumns = @JoinColumn(name = "food_id"))
    private List<Ingredient> primaryIngredients;

    @ElementCollection
    @CollectionTable(name = "food_sauce_ingredients", joinColumns = @JoinColumn(name = "food_id"))
    private List<Ingredient> sauceIngredients;

    @ElementCollection
    @CollectionTable(name = "food_instructions", joinColumns = @JoinColumn(name = "food_id"))
    @Column(name = "instruction")
        private List<String> instructions;

    public Food(String name, String englishName, String photoUrl, String videoUrl, List<Ingredient> primaryIngredients, List<Ingredient> sauceIngredients, List<String> instructions) {
        this.name = name;
        this.englishName = englishName;
        this.photoUrl = photoUrl;
        this.videoUrl = videoUrl;
        this.primaryIngredients = primaryIngredients;
        this.sauceIngredients = sauceIngredients;
        this.instructions = instructions;
    }
}
