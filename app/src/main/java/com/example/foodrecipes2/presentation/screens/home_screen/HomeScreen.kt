package com.example.foodrecipes2.presentation.screens.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.foodrecipes2.core.SharedWidget.CustomTopBar
import com.example.foodrecipes2.presentation.screens.home_screen.widgets.category_list.CategoryList
import com.example.foodrecipes2.ui.theme.MainWhite

@Composable
fun HomeScreen(){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MainWhite,
        topBar = {CustomTopBar()},
    ) {
        CategoryList()
    }
}