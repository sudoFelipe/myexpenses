package com.myexpenses.myexpenses_api.repository;

import com.myexpenses.myexpenses_api.domain.model.Expense;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends ReactiveMongoRepository<Expense, String> {
}
