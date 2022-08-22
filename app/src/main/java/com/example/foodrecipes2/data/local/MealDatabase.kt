package com.example.foodrecipes2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodrecipes2.data.local.entity.MealEntity

@Database(
    entities = [MealEntity::class],
    version = 1
)

abstract class MealDatabase: RoomDatabase() {
    abstract val dao: MealDao
}