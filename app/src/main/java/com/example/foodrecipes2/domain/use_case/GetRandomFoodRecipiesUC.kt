package com.example.foodrecipes2.domain.use_case

import com.example.foodrecipes2.core.util.Resource
import com.example.foodrecipes2.domain.model.Category
import com.example.foodrecipes2.domain.model.Meal
import com.example.foodrecipes2.domain.repository.CategoryRepository
import com.example.foodrecipes2.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow

class GetRandomFoodRecipiesUC(
    private val mealsRepository: MealsRepository
) {
    operator fun invoke(): Flow<Resource<List<Meal>>>{
        return mealsRepository.getRandomFoodRecipie()
    }
}