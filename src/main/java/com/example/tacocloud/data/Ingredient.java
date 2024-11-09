package com.example.tacocloud.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.domain.Persistable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Ingredient {

    @Id
    private final String id;
    private final String name;
    private final Type type;


    public enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
