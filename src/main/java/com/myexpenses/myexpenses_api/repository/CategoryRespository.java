package com.myexpenses.myexpenses_api.repository;

import com.myexpenses.myexpenses_api.domain.model.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRespository extends ReactiveMongoRepository<Category, String> {
}
