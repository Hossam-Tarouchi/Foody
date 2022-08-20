package com.example.foodrecipes2.domain.use_case

import com.example.foodrecipes2.core.util.Resource
import com.example.foodrecipes2.domain.model.Meal
import com.example.foodrecipes2.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipiesUC(
    private val mealsRepository: MealsRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<Meal>>>{
        if (query.isBlank()){
            System.err.println("entred " + query)
            return flow {  }
        }
        return mealsRepository.searchFoodRecipie(query)
    }
}