package com.myexpenses.myexpenses_api.repository;

import com.myexpenses.myexpenses_api.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mongodb.MongoDBContainer;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
class CategoryRespositoryTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:8.0");

    @Autowired
    private CategoryRespository respository;

    @BeforeEach
    void setUp() {
        this.respository.deleteAll().block();
    }


    @Test
    void shouldSaveAndFindCategoryWithSucess() {
        Category category = Category.builder()
                .name("Alimentação")
                .description("Gastos com alimentação")
                .color("#FF5733")
                .createdAt(LocalDateTime.now())
                .build();

        StepVerifier.create(this.respository.save(category))
                .consumeNextWith(result -> {
                    assertThat(result.getId()).isNotEmpty();
                    assertThat(result.getName()).isEqualTo(category.getName());
                    assertThat(result.getDescription()).isEqualTo(category.getDescription());
                    assertThat(result.getColor()).isEqualTo(category.getColor());
                    assertThat(result.getCreatedAt()).isEqualTo(category.getCreatedAt());
                })
                .verifyComplete();
    }

    @Test
    void shouldFindAllCategoriesWithSucess() {
        Category category = Category.builder()
                .name("Alimentação")
                .description("Gastos com alimentação")
                .color("#FF5733")
                .createdAt(LocalDateTime.now())
                .build();

        StepVerifier.create(this.respository.save(category))
                .consumeNextWith(result -> {
                    assertThat(result.getId()).isNotEmpty();
                    assertThat(result.getName()).isEqualTo(category.getName());
                    assertThat(result.getDescription()).isEqualTo(category.getDescription());
                    assertThat(result.getColor()).isEqualTo(category.getColor());
                    assertThat(result.getCreatedAt()).isEqualTo(category.getCreatedAt());
                })
                .verifyComplete();

        StepVerifier.create(this.respository.findAll())
                .expectNextCount(1)
                .verifyComplete();
    }
}