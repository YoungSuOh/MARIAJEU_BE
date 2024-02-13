package org.example.mariajeu.domain.homepageDomain.FoodArticle;

import jakarta.persistence.*;
@Entity
public class FoodArticleLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;*/

    @ManyToOne
    @JoinColumn(name = "foodArticle_id")
    private FoodArticle foodArticle;
}
