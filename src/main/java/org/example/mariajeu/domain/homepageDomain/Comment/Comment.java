package org.example.mariajeu.domain.homepageDomain.Comment;

import jakarta.persistence.*;
import org.example.mariajeu.domain.homepageDomain.FoodArticle.FoodArticle;

import java.util.Set;
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private FoodArticle foodArticle;

   /* @ManyToOne
    private User user;*/

    @OneToMany(mappedBy = "comment")
    private Set<CommentLikes> likes;
}
