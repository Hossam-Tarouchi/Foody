package com.example.foodrecipes2.presentation.screens.home_screen.widgets.category_list

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.example.foodrecipes2.core.util.Screen
import com.example.foodrecipes2.domain.model.Category
import com.example.foodrecipes2.ui.theme.MainBlack
import com.example.foodrecipes2.ui.theme.MainWhite

@Composable
fun CategoryList(navController: NavController) {

    val viewModel: CategoryListViewModel = hiltViewModel()
    val state = viewModel.state.value
    Row() {
        if(state.isLoading) {
            LazyRow() {
                items(listOf(0, 0, 0, 0, 0)){
                      AnimatedShimmerCategory()
                }
            }
        } else {
            LazyRow(modifier = Modifier) {
                items(state.categoryItems) { item ->
                    CategoryItem(item = item) {
                       navController.navigate(Screen.ListOfRecipies.withArgs(item.strCategory))
                    }
                }
            }
        }
    }

}

@Composable
fun CategoryItem(item: Category, onClick: ()->Unit){
    Box(modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 0.dp).clickable { onClick() }) {
        Box(modifier = Modifier
            .padding(0.dp, 30.dp, 0.dp, 0.dp)
            .size(70.dp)
            .offset(10.dp, (-30).dp)
            .zIndex(2f)
            .clip(CircleShape)
            ){
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.strCategoryThumb)
                    //.error(R.drawable.error_placeholder)
                    .build(),
                contentScale = ContentScale.Fit ,
                contentDescription = "Image",

            )
        }
        Card(
            elevation = 5.dp,
            modifier = Modifier
                .padding(10.dp, 30.dp, 10.dp, 10.dp)
                .size(70.dp, 50.dp),
            backgroundColor = MainWhite

        ) {
            Column(modifier = Modifier
                .padding(10.dp)
                .background(MainWhite)
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {

                Text(text = item.strCategory, fontSize = 12.sp, maxLines = 1)
            }
        }
    }

}