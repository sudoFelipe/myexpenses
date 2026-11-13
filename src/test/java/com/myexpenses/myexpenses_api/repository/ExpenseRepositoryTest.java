package com.myexpenses.myexpenses_api.repository;

import com.myexpenses.myexpenses_api.domain.model.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mongodb.MongoDBContainer;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
class ExpenseRepositoryTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:8.0");

    @Autowired
    private ExpenseRepository repository;

    @BeforeEach
    void setUp() {
        this.repository.deleteAll().block();
    }


    @Test
    void shouldSaveAndFindCategoryWithSucess() {
        Expense expense = Expense.builder()
                .description("Expense1")
                .amount(BigDecimal.valueOf(10))
                .date(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .categoryId("category-123")
                .build();

        StepVerifier.create(this.repository.save(expense))
                .consumeNextWith(result -> {
                    assertThat(result.getId()).isNotEmpty();
                    assertThat(result.getDescription()).isEqualTo(expense.getDescription());
                    assertThat(result.getAmount()).isEqualTo(expense.getAmount());
                    assertThat(result.getDate()).isEqualTo(expense.getDate());
                    assertThat(result.getCreatedAt()).isEqualTo(expense.getCreatedAt());
                    assertThat(result.getCategoryId()).isEqualTo(expense.getCategoryId());
                })
                .verifyComplete();
    }

    @Test
    void shouldFindAllCategoriesWithSucess() {
        Expense expense = Expense.builder()
                .description("Expense1")
                .amount(BigDecimal.valueOf(10))
                .date(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .categoryId("category-123")
                .build();

        StepVerifier.create(this.repository.save(expense))
                .consumeNextWith(result -> {
                    assertThat(result.getId()).isNotEmpty();
                    assertThat(result.getDescription()).isEqualTo(expense.getDescription());
                    assertThat(result.getAmount()).isEqualTo(expense.getAmount());
                    assertThat(result.getDate()).isEqualTo(expense.getDate());
                    assertThat(result.getCreatedAt()).isEqualTo(expense.getCreatedAt());
                    assertThat(result.getCategoryId()).isEqualTo(expense.getCategoryId());
                })
                .verifyComplete();

        StepVerifier.create(this.repository.findAll())
                .expectNextCount(1)
                .verifyComplete();
    }
}