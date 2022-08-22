package com.example.foodrecipes2.domain.use_case

import com.example.foodrecipes2.core.util.Resource
import com.example.foodrecipes2.domain.model.Meal
import com.example.foodrecipes2.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow

class IsMealLikedUC(
    private val mealsRepository: MealsRepository
) {
    operator fun invoke(idMeal: String): Flow<Resource<Meal>> {
        return mealsRepository.isMealLiked(idMeal)
    }
}