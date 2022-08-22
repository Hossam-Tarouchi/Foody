package com.example.foodrecipes2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodrecipes2.data.local.entity.MealEntity

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun LikeMeal(meal: MealEntity)

    @Query("DELETE FROM mealentity WHERE idMeal = (:mealId)")
    suspend fun DislikeMeal(mealId: String)

    @Query("SELECT * FROM mealentity")
    suspend fun getLikedMeals(): List<MealEntity>

    @Query("SELECT * FROM mealentity WHERE idMeal = :mealId")
    suspend fun isMealLiked(mealId: String): MealEntity

}