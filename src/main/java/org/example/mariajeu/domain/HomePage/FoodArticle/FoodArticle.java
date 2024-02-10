package org.example.mariajeu.domain.HomePage.FoodArticle;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.mariajeu.domain.HomePage.Comment.Comment;
import org.example.mariajeu.domain.HomePage.Food;
import org.example.mariajeu.domain.HomePage.Wine;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FoodArticle {
    @Id  // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foodArticle_id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "food_id", referencedColumnName = "id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "wine_id", referencedColumnName = "id")
    private Wine wine;

    @OneToMany(mappedBy = "foodArticle")
    private Set<FoodArticleLikes> likes;

    @OneToMany(mappedBy = "foodArticle")
    private Set<Comment> comments;

    @Builder
    public FoodArticle(Food food, Wine wine, Set<FoodArticleLikes> likes) {
        this.food = food;
        this.wine = wine;
        this.likes = likes;
    }
}