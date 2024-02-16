package org.example.mariajeu.domain.boardDomain;

import jakarta.persistence.*;
import lombok.*;
import org.example.mariajeu.domain.userDomain.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String foodName;
    private String foodKind;

    private String ateDate;
    private String ateLocation;
    private String withPerson;
    private String wineName;
    private String winery;
    private String createdYear;
    private String kind;
    private String country;
    private String createdLocation;
    private String content;
    private int viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board")
    private List<Likes> likes = new ArrayList<>();

    public void updateViewCount() {
        this.viewCount += 1;
    }
}