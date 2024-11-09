package com.example.tacocloud.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import com.example.tacocloud.data.Ingredient;
import com.example.tacocloud.data.Ingredient.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootTest
public class IngredientRepositoryTests {

    @Autowired
    IngredientRepository ingredientRepo;

    @Autowired
    JdbcTemplate jdbc;

    @Test
    public void findById() {
        Optional<Ingredient> flto = ingredientRepo.findById("FLTO");
        assertThat(flto.isPresent()).isTrue();
        assertThat(flto.get()).isEqualTo(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));

        Optional<Ingredient> xxxx = ingredientRepo.findById("XXXX");
        assertThat(xxxx.isEmpty()).isTrue();

    }

}
