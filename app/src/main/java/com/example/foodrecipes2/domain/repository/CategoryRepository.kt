package com.example.foodrecipes2.domain.repository

import com.example.foodrecipes2.core.util.Resource
import com.example.foodrecipes2.domain.model.Category
import com.example.foodrecipes2.domain.model.Meal
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun getFoodCategories(): Flow<Resource<List<Category>>>

    fun getRandomFoodRecipie(): Flow<Resource<List<Meal>>>

}