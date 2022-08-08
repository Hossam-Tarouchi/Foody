package com.example.foodrecipes2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodrecipes2.domain.model.Category

@Entity
data class CategoryEntity(
    @PrimaryKey
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String
) {
    fun toCategory(): Category{
        return Category(
            idCategory, strCategory, strCategoryThumb
        )
    }
}