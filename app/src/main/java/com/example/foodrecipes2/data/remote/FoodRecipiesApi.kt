package com.example.foodrecipes2.data.remote

import com.example.foodrecipes2.data.remote.dto.CategoriesDto
import retrofit2.http.GET

interface FoodRecipiesApi {

    @GET("/api/json/v1/1/categories.php")
    suspend fun getAllFoodCategories(): CategoriesDto

    companion object{
        const val BASE_URL = "https://www.themealdb.com"
    }

}