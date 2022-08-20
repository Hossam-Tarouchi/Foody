package com.example.foodrecipes2.data.repository

import com.example.foodrecipes2.core.util.Resource
import com.example.foodrecipes2.data.remote.FoodRecipiesApi
import com.example.foodrecipes2.domain.model.Category
import com.example.foodrecipes2.domain.model.Meal
import com.example.foodrecipes2.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.ArrayList

class CategoryRepositoryImpl(
    private val api: FoodRecipiesApi
): CategoryRepository {


    override fun getFoodCategories(): Flow<Resource<List<Category>>> = flow {
        emit(Resource.Loading())
        try {

            val remoteCategories = api.getAllFoodCategories().categories.map { it.toCategory() }
            System.err.println(remoteCategories)
            emit(Resource.Success(remoteCategories))

        } catch (e: HttpException){
            emit(Resource.Error("Oops !! some difficulties are faced while processing your request"))
        } catch (e: IOException){
            emit(Resource.Error("No internet, check your network !"))
        }
    }

    override fun getRandomFoodRecipie(): Flow<Resource<List<Meal>>> = flow {
        emit(Resource.Loading())

        try {
            val remoteRandomFoodRecipie = ArrayList<Meal>()
            for ( i in 0..3 ){
                var meal = api.getRandomFoodRecipie().meals.get(0).toMeal()
                if (!remoteRandomFoodRecipie.contains(meal))
                    remoteRandomFoodRecipie.add( meal )
            }
        } catch (e: HttpException){
            emit(Resource.Error("Oops !! some difficulties are faced while processing your request"))
        } catch (e: IOException){
            emit(Resource.Error("No internet, check your network !"))
        }
    }
}