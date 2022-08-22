package com.example.foodrecipes2.presentation.screens.detail_screen.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodrecipes2.domain.model.Meal

@Composable
fun StepsTab(meal: Meal){
    Column(modifier = Modifier
        .padding(0.dp, 10.dp, 0.dp, 0.dp)
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        meal.strInstructions?.let { it.split("\r\n").forEach { step ->
            Text(text = step)
            Spacer(modifier = Modifier.height(5.dp))
        } }
    }

}