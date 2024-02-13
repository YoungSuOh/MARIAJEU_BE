package org.example.mariajeu.domain.HomePage;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Wine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name; // 와인 이름

    @Column
    private String country; // 와인 원산지

    @Column
    private int age; // 와인 숙성 기간

    @Column
    private double price; // 와인 가격

    @Enumerated(EnumType.STRING)
    private WineType type; // 와인 타입

    @Column
    @Min(1)
    @Max(100)  // 1~100까지 정도
    private int boldness;

    @Column
    @Min(1)
    @Max(100)
    private int acidity;

    @Column
    @Min(1)
    @Max(100)
    private int fizziness;

    @Column
    @Min(1)
    @Max(100)
    private int tannic;

    public Wine(String name, String type, int boldness,
                int acidity, int fizziness, int tannic){
        this.name=name;
        this.type= WineType.valueOf(type);
        this.boldness=boldness;
        this.acidity=acidity;
        this.fizziness=fizziness;
        this.tannic=tannic;
    }
}
