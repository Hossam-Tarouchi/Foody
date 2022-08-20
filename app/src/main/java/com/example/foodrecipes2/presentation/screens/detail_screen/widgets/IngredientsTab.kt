package com.example.foodrecipes2.presentation.screens.detail_screen.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.foodrecipes2.domain.model.Meal
import com.example.foodrecipes2.ui.theme.MainWhite

@Composable
fun IngredientsTab(meal: Meal){
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(0.dp, 10.dp)
        .horizontalScroll(rememberScrollState())) {
        if (meal.strIngredient1?.isBlank() == false){
            IngredientItem(title = meal.strIngredient1, measure = meal.strMeasure1)
        }

        if (meal.strIngredient2?.isBlank() == false){
            IngredientItem(title = meal.strIngredient2, measure = meal.strMeasure2)
        }

        if (meal.strIngredient3?.isBlank() == false){
            IngredientItem(title = meal.strIngredient3, measure = meal.strMeasure3)
        }

        if (meal.strIngredient4?.isBlank() == false){
            IngredientItem(title = meal.strIngredient4, measure = meal.strMeasure4)
        }

        if (meal.strIngredient5?.isBlank() == false){
            IngredientItem(title = meal.strIngredient5, measure = meal.strMeasure5)
        }

        if (meal.strIngredient6?.isBlank() == false){
            IngredientItem(title = meal.strIngredient6, measure = meal.strMeasure6)
        }

        if (meal.strIngredient7?.isBlank() == false){
            IngredientItem(title = meal.strIngredient7, measure = meal.strMeasure7)
        }

        if (meal.strIngredient8?.isBlank() == false){
            IngredientItem(title = meal.strIngredient8, measure = meal.strMeasure8)
        }

        if (meal.strIngredient9?.isBlank() == false){
            IngredientItem(title = meal.strIngredient9, measure = meal.strMeasure9)
        }

        if (meal.strIngredient10?.isBlank() == false){
            IngredientItem(title = meal.strIngredient10, measure = meal.strMeasure10)
        }

        if (meal.strIngredient11?.isBlank() == false){
            IngredientItem(title = meal.strIngredient11, measure = meal.strMeasure11)
        }

        if (meal.strIngredient12?.isBlank() == false){
            IngredientItem(title = meal.strIngredient12, measure = meal.strMeasure12)
        }

        if (meal.strIngredient13?.isBlank() == false){
            IngredientItem(title = meal.strIngredient13, measure = meal.strMeasure13)
        }

        if (meal.strIngredient14?.isBlank() == false){
            IngredientItem(title = meal.strIngredient14, measure = meal.strMeasure14)
        }

        if (meal.strIngredient15?.isBlank() == false){
            IngredientItem(title = meal.strIngredient15, measure = meal.strMeasure15)
        }

        if (meal.strIngredient16?.isBlank() == false){
            IngredientItem(title = meal.strIngredient16, measure = meal.strMeasure16)
        }

        if (meal.strIngredient17?.isBlank() == false){
            IngredientItem(title = meal.strIngredient17, measure = meal.strMeasure17)
        }

        if (meal.strIngredient18?.isBlank() == false){
            IngredientItem(title = meal.strIngredient18, measure = meal.strMeasure18)
        }

        if (meal.strIngredient19?.isBlank() == false){
            IngredientItem(title = meal.strIngredient19, measure = meal.strMeasure19)
        }

        if (meal.strIngredient20?.isBlank() == false){
            IngredientItem(title = meal.strIngredient20, measure = meal.strMeasure20)
        }
    }
}

@Composable
fun IngredientItem(title: String, measure: Any?){
    Column(modifier = Modifier

        .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Card(modifier = Modifier
            .padding(10.dp)
            .size(60.dp)
            .background(MainWhite), elevation = 3.dp){
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://www.themealdb.com/images/ingredients/$title.png")
                    //.error(R.drawable.error_placeholder)
                    .build(),
                contentScale = ContentScale.Crop  ,
                contentDescription = "Image",

                )
        }
        Spacer(modifier = Modifier.height(0.dp))
        Text(text = "$measure - $title", fontSize = 11.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), fontWeight = FontWeight.Bold)
    }
    Spacer(modifier = Modifier.width(5.dp))

    

}