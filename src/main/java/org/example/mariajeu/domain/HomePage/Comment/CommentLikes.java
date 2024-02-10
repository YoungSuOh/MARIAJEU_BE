package org.example.mariajeu.domain.HomePage.Comment;

import jakarta.persistence.*;

@Entity
public class CommentLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

/*    @ManyToOne
    private User user;*/

    @ManyToOne
    private Comment comment;
}
