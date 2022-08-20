package com.example.foodrecipes2.presentation.screens.home_screen.widgets.trending_recipies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.foodrecipes2.R
import com.example.foodrecipes2.core.util.Screen
import com.example.foodrecipes2.domain.model.Meal
import com.example.foodrecipes2.presentation.screens.home_screen.widgets.category_list.AnimatedShimmerCategory
import com.example.foodrecipes2.presentation.screens.home_screen.widgets.category_list.CategoryItem
import com.example.foodrecipes2.presentation.screens.home_screen.widgets.category_list.CategoryListViewModel
import com.example.foodrecipes2.ui.theme.MainBlack
import com.example.foodrecipes2.ui.theme.MainWhite
import java.lang.Double
import java.text.DecimalFormat
import kotlin.random.Random

@Composable
fun TrendingRecipies(navController: NavController) {

    val viewModel: TrendingRecipiesViewModel = hiltViewModel()
    val state = viewModel.state.value
    
    Column(
        modifier = Modifier.padding(10.dp, 10.dp)){

        Text(text = "Trending recipies", fontWeight = FontWeight.Bold)
        if(state.isLoading) {
            LazyRow() {
                items(listOf(0, 0, 0, 0, 0)){
                    AnimatedShimmerTrending()
                }
            }
        } else {
            LazyRow(){
                items(state.randomRecipiesItems){recipie ->
                    TrendingFoodRecipie(recipie = recipie){
                        recipie.idMeal.let {
                            navController.navigate(Screen.DetailScreen.withArgs(it ?: ""))
                        }
                    }
                }
            }
        }
    }
    
}

@Composable
fun TrendingFoodRecipie(recipie: Meal, onClick: () -> Unit){
    Card(
        modifier = Modifier
            .height(350.dp)
            .width(200.dp)
            .padding(0.dp, 10.dp, 20.dp, 10.dp)
            .clickable { onClick() }
        ,
        backgroundColor = MainWhite,
        elevation = 5.dp,
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp, 10.dp)) {
            Box(modifier = Modifier.clip(CircleShape)){
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(recipie.strMealThumb)
                        //.error(R.drawable.error_placeholder)
                        .build(),
                    contentScale = ContentScale.Fit ,
                    contentDescription = "Image",

                    )
            }

            Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                recipie.strMeal?.let { Text(text = it, modifier = Modifier
                    .padding(0.dp, 10.dp)
                    .fillMaxWidth(), textAlign = TextAlign.Center) }

                val df = DecimalFormat("#.#");
                val p = Double.valueOf(df.format(Random.nextDouble(3.0, 4.9)))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(id = R.drawable.ic_star), contentDescription = "review star")
                    Text(text = p.toString(), fontSize = 12.sp)
                }

                recipie.strTags?.let {
                    if (!recipie.strTags.isBlank()){
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }

                recipie.strTags?.let {
                    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                        val listOfTags = it.split(",")
                        if (listOfTags.size > 2){
                            listOfTags.subList(0,3).forEach{ tag ->
                                Text(text = tag, fontSize = 10.sp, maxLines = 1,color = MainWhite , modifier = Modifier
                                    .clip(
                                        RoundedCornerShape(10.dp)
                                    )
                                    .background(MainBlack)
                                    .padding(8.dp, 3.dp))
                            }
                        } else {
                            listOfTags.forEach{ tag ->
                                Text(text = tag, fontSize = 10.sp, maxLines = 1, color = MainWhite, modifier = Modifier
                                    .clip(
                                        RoundedCornerShape(10.dp)
                                    )
                                    .background(MainBlack)
                                    .padding(8.dp, 3.dp))
                            }
                        }


                    }
                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {

                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(30.dp)){
                        Image(painter = painterResource(id = R.drawable.ic_difficulty), contentDescription = "review star", modifier = Modifier.size(15.dp))
                        Text(text = if(Random.nextInt(0, 2) == 0) "Easy" else "Medium", fontSize = 10.sp)
                    }
                    Box(modifier = Modifier.clip(CircleShape)){
                        SubcomposeAsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://xsgames.co/randomusers/avatar.php?g=male")
                                //.error(R.drawable.error_placeholder)
                                .build(),
                            contentScale = ContentScale.Fit ,
                            contentDescription = "Image",
                            modifier = Modifier.size(30.dp)

                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(30.dp)){
                        Image(painter = painterResource(id = R.drawable.ic_prepare_time), contentDescription = "review star", modifier = Modifier.size(15.dp))
                        Text(text = "${Random.nextInt(10, 120)} mins", fontSize = 10.sp)
                    }
                }
            }

        }
    }
}