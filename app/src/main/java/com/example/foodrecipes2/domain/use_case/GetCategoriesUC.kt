package com.example.foodrecipes2.domain.use_case

import com.example.foodrecipes2.core.util.Resource
import com.example.foodrecipes2.domain.model.Category
import com.example.foodrecipes2.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetCategoriesUC(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(): Flow<Resource<List<Category>>>{
        return categoryRepository.getFoodCategories()
    }
}