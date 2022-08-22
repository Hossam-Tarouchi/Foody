package com.example.foodrecipes2.domain.use_case

import com.example.foodrecipes2.core.util.Resource
import com.example.foodrecipes2.domain.model.Meal
import com.example.foodrecipes2.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow

class SwitchLikedRecipieUC(
    private val mealsRepository: MealsRepository
) {
    operator fun invoke(state: Boolean, meal: Meal): Flow<Resource<Boolean>> {
        return mealsRepository.switchLikedRecipie(state, meal)
    }
}