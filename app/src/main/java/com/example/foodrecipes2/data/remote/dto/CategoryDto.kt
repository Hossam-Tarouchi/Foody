package com.example.foodrecipes2.data.remote.dto

import com.example.foodrecipes2.data.local.entity.CategoryEntity
import com.example.foodrecipes2.domain.model.Category

data class CategoryDto(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String
) {
    fun toCategory(): Category{
        return Category(
            idCategory, strCategory, strCategoryThumb
        )
    }

    fun toCategoryEntity(): CategoryEntity{
        return CategoryEntity(
            idCategory, strCategory, strCategoryThumb
        )
    }
}