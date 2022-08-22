package com.example.foodrecipes2.data.repository

import com.example.foodrecipes2.core.util.Resource
import com.example.foodrecipes2.data.local.MealDao
import com.example.foodrecipes2.data.remote.FoodRecipiesApi
import com.example.foodrecipes2.domain.model.Meal
import com.example.foodrecipes2.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.ArrayList

class MealsRepositoryImpl(
    private val api: FoodRecipiesApi,
    private val dao: MealDao
): MealsRepository {

    override fun getRandomFoodRecipie(): Flow<Resource<List<Meal>>> = flow {
        emit(Resource.Loading())

        try {
            val remoteRandomFoodRecipie = ArrayList<Meal>()
            for ( i in 0..2 ){
                var meal = api.getRandomFoodRecipie().meals.get(0).toMeal()
                if (!remoteRandomFoodRecipie.contains(meal)){
                    remoteRandomFoodRecipie.add( meal )
                }
            }
            emit(Resource.Success(remoteRandomFoodRecipie.toList()))
        } catch (e: HttpException){
            emit(Resource.Error("Oops !! some difficulties are faced while processing your request"))
        } catch (e: IOException){
            emit(Resource.Error("No internet, check your network !"))
        }
    }

    override fun getFoodRecipieByCategory(category: String): Flow<Resource<List<Meal>>> = flow {
        emit(Resource.Loading())

        try {
            val remoteRandomFoodRecipie = api.getFoodRecipieByCategory(category).meals.map { it.toMeal() }
            emit(Resource.Success(remoteRandomFoodRecipie))
        } catch (e: HttpException){
            emit(Resource.Error("Oops !! some difficulties are faced while processing your request"))
        } catch (e: IOException){
            emit(Resource.Error("No internet, check your network !"))
        }
    }

    override fun getFoodRecipieById(id: String): Flow<Resource<List<Meal>>> = flow {
        emit(Resource.Loading())
        try {
            val remoteRandomFoodRecipie = api.getFoodRecipieById(id).meals.map { it.toMeal() }
            emit(Resource.Success(remoteRandomFoodRecipie))
        } catch (e: HttpException){
            emit(Resource.Error("Oops !! some difficulties are faced while processing your request"))
        } catch (e: IOException){
            emit(Resource.Error("No internet, check your network !"))
        }
    }

    override fun searchFoodRecipie(query: String): Flow<Resource<List<Meal>>> = flow {
        emit(Resource.Loading())
        try {
            val remoteRandomFoodRecipie = api.searchFoodRecipieById(query)?.meals?.map { it.toMeal() }
            emit(Resource.Success(remoteRandomFoodRecipie))
        } catch (e: HttpException){
            emit(Resource.Error("Oops !! some difficulties are faced while processing your request"))
        } catch (e: IOException){
            emit(Resource.Error("No internet, check your network !"))
        }
    }

    override fun switchLikedRecipie(state: Boolean, meal: Meal): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            if (state){
                dao.LikeMeal(meal.toMealEntity())
                emit(Resource.Success(true))
            }else {
                meal.idMeal?.let { dao.DislikeMeal(it) }
                emit(Resource.Success(true))
            }
        }catch (e: IOException){
            emit(Resource.Error("Oops !! some difficulties are faced while processing your request"))
        }
    }

    override fun getLikedMeals(): Flow<Resource<List<Meal>>> = flow {
        emit(Resource.Loading())
        try {
            val result = dao.getLikedMeals().map { it.toMeal() }
            emit(Resource.Success(result))
        }catch (e: IOException){
            emit(Resource.Error("Oops !! some difficulties are faced while processing your request"))
        }
    }

    override fun isMealLiked(idMeal: String): Flow<Resource<Meal>> = flow {
        emit(Resource.Loading())
        try {
            val result = dao.isMealLiked(idMeal)
            if (result != null){
                emit(Resource.Success(result.toMeal()))
            }
        }catch (e: IOException){
            emit(Resource.Error("Oops !! some difficulties are faced while processing your request"))
        }
    }
}