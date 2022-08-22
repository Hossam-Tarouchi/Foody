package com.example.foodrecipes2.presentation.screens.detail_screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.foodrecipes2.R
import com.example.foodrecipes2.core.SharedWidget.CustomBottomBar
import com.example.foodrecipes2.core.SharedWidget.CustomTopBar
import com.example.foodrecipes2.presentation.screens.detail_screen.widgets.TabItem
import com.example.foodrecipes2.ui.theme.MainBlack
import com.example.foodrecipes2.ui.theme.MainWhite
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import java.lang.Double
import java.text.DecimalFormat
import kotlin.random.Random

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun DetailScreen(recipieId :String?, navController: NavController){

    val viewModel: DetailViewModel = hiltViewModel()
    val state = viewModel.state.value

    LaunchedEffect(key1 = true) {
        recipieId?.let { viewModel.handle(DetailViewAction.GetRecipiesById(it)) }
        recipieId?.let { viewModel.handle(DetailViewAction.IsMealLiked(it)) }
    }

    Scaffold(topBar = { CustomTopBar(navController = navController, showBackBtn = true) }, bottomBar = { CustomBottomBar(
        navController
    )
    }, backgroundColor = MainWhite,) {
        Column(modifier = Modifier
            .fillMaxSize()
            .offset(0.dp, -60.dp), horizontalAlignment = Alignment.End         ) {
            if (state.recipiesItems.size > 0){
                Box {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(state.recipiesItems[0].strMealThumb)
                            //.error(R.drawable.error_placeholder)
                            .build(),
                        contentScale = ContentScale.Crop ,
                        contentDescription = "Image",
                    )
                }

                Box(modifier = Modifier.offset(-20.dp, -35.dp).shadow(5.dp, CircleShape, false).clip(CircleShape).background(MainWhite).size(50.dp).clickable {
                    viewModel.handle(DetailViewAction.SwitchLikedRecipie(!state.isLiked, state.recipiesItems[0]))
                }) {
                    Image(painter = painterResource(id = if (state.isLiked )R.drawable.ic_liked else R.drawable.ic_not_liked), contentDescription = "review star", modifier = Modifier.size(30.dp).align(Alignment.Center))
                }


                Column(modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 10.dp).offset(0.dp, -25.dp)) {
                    state.recipiesItems[0].strMeal?.let { Text(text = it, modifier = Modifier
                        .padding(0.dp, 10.dp)
                        .fillMaxWidth(),
                        textAlign = TextAlign.Center
                        , fontWeight = FontWeight.Bold) }

                    Spacer(modifier = Modifier.height(0.dp))
                    val df = DecimalFormat("#.#");
                    val p = Double.valueOf(df.format(Random.nextDouble(3.0, 4.9)))

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Column() {
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(25.dp)){
                                Image(painter = painterResource(id = R.drawable.ic_prepare_time), contentDescription = "review star", modifier = Modifier.size(30.dp))
                                Text(text = "${Random.nextInt(10, 120)} mins", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                        Column() {
                            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                                Image(painter = painterResource(id = R.drawable.ic_star), contentDescription = "review star", modifier = Modifier.size(20.dp))
                                Text(text = p.toString(), fontSize = 12.sp)
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

                        }
                        Column() {
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(25.dp)){
                                Image(painter = painterResource(id = R.drawable.ic_difficulty), contentDescription = "review star", modifier = Modifier.size(30.dp))
                                Text(text = if(Random.nextInt(0, 2) == 0) "Easy" else "Medium", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                    Divider(modifier = Modifier.padding(0.dp, 20.dp), color = MainBlack, thickness = 2.dp)
                    val tabs = listOf(
                        TabItem.Ingredients(state.recipiesItems[0]),
                        TabItem.Steps(state.recipiesItems[0])
                    )

                    val pagerState = rememberPagerState(pageCount = tabs.size)

                    Column(verticalArrangement = Arrangement.Top) {
                        Tabs(tabs = tabs, pagerState = pagerState)
                        TabsContent(tabs = tabs, pagerState = pagerState)
                    }

                }

            }

        }
    }

}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState){

    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.Transparent,
        contentColor = Color.Black,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        tabs.forEachIndexed{ index, tab ->

            Tab(
                text = { Text(text = tab.title) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState){
    HorizontalPager(state = pagerState) { page ->
        Box(contentAlignment = Alignment.TopStart){
            tabs[page].screen()
        }
    }
}