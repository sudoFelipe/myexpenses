package com.myexpenses.myexpenses_api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "categories")
public class Expense {

    @Id
    private String id;

    private String description;

    private BigDecimal amount;

    private LocalDateTime date;

    private String categoryId;

    private LocalDateTime createdAt;
}
