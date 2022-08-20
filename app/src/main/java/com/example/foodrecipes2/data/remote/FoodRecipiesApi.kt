package com.example.foodrecipes2.data.remote

import com.example.foodrecipes2.data.remote.dto.CategoriesDto
import com.example.foodrecipes2.data.remote.dto.MealsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodRecipiesApi {

    @GET("/api/json/v1/1/categories.php")
    suspend fun getAllFoodCategories(): CategoriesDto

    @GET("/api/json/v1/1/random.php")
    suspend fun getRandomFoodRecipie(): MealsDto

    @GET("/api/json/v1/1/filter.php")
    suspend fun getFoodRecipieByCategory(@Query("c") categoty: String): MealsDto

    @GET("/api/json/v1/1/lookup.php")
    suspend fun getFoodRecipieById(@Query("i") id: String): MealsDto

    @GET("/api/json/v1/1/search.php")
    suspend fun searchFoodRecipieById(@Query("s") query: String): MealsDto?

    companion object{
        const val BASE_URL = "https://www.themealdb.com"
    }

}