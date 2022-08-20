package com.example.foodrecipes2.di

import com.example.foodrecipes2.data.remote.FoodRecipiesApi
import com.example.foodrecipes2.data.repository.CategoryRepositoryImpl
import com.example.foodrecipes2.data.repository.MealsRepositoryImpl
import com.example.foodrecipes2.domain.repository.CategoryRepository
import com.example.foodrecipes2.domain.repository.MealsRepository
import com.example.foodrecipes2.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoryAppModule {

    @Provides
    @Singleton
    fun provideGetCategoriesUseCase(repository: CategoryRepository): GetCategoriesUC {
        return GetCategoriesUC(repository)
    }

    @Provides
    @Singleton
    fun provideGetMealsUseCase(repository: MealsRepository): GetRandomFoodRecipiesUC {
        return GetRandomFoodRecipiesUC(repository)
    }

    @Provides
    @Singleton
    fun provideGetRecipiesByCategoryUseCase(repository: MealsRepository): GetRecipiesByCategoryUC {
        return GetRecipiesByCategoryUC(repository)
    }

    @Provides
    @Singleton
    fun provideGetRecipiesByIdUseCase(repository: MealsRepository): GetRecipiesByIdUC {
        return GetRecipiesByIdUC(repository)
    }

    @Provides
    @Singleton
    fun provideSearchRecipiesUseCase(repository: MealsRepository): SearchRecipiesUC {
        return SearchRecipiesUC(repository)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(
        //db: WordInfoDatabase,
        api: FoodRecipiesApi
    ): CategoryRepository {
        return CategoryRepositoryImpl(api)
    }


    @Provides
    @Singleton
    fun provideMealRepository(
        //db: WordInfoDatabase,
        api: FoodRecipiesApi
    ): MealsRepository {
        return MealsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideFoodRecipiesApi(): FoodRecipiesApi {
        return Retrofit.Builder()
            .baseUrl(FoodRecipiesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodRecipiesApi::class.java)
    }

}