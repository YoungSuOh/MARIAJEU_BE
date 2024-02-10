package org.example.mariajeu.domain.HomePage;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Ingredient {
    private String name;
    private String quantity;

    Ingredient(String name,String quantity){
        this.name=name;
        this.quantity=quantity;
    }

    public Ingredient() {}
}
