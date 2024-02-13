package org.example.mariajeu.domain.FoodArticle;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.mariajeu.domain.Comment.Comment;
import org.example.mariajeu.domain.Food;
import org.example.mariajeu.domain.Wine;

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

    private int views;

    @Builder
    public FoodArticle(Food food, Wine wine, Set<FoodArticleLikes> likes) {
        this.food = food;
        this.wine = wine;
        this.likes = likes;
    }
}
