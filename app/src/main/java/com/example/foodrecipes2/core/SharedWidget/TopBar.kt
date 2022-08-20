package com.example.foodrecipes2.core.SharedWidget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodrecipes2.R
import com.example.foodrecipes2.presentation.screens.home_screen.HomeScreenViewModel
import com.example.foodrecipes2.ui.theme.MainBlack
import com.example.foodrecipes2.ui.theme.MainWhite

@Composable
fun CustomTopBar(navController: NavController, showBackBtn: Boolean) {

    val viewModel: HomeScreenViewModel = hiltViewModel()
    val state = viewModel.state.value
    var showSearchBar by remember{
        mutableStateOf(false)
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp, 30.dp, 20.dp, 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        if (showBackBtn){
            IconButton(modifier = Modifier.
            then(Modifier.size(20.dp)),onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }

        AnimatedVisibility(showSearchBar){
            CustomTextField(
                leadingIcon = {
                    Icon(
                        Icons.Filled.Search,
                        null,
                        tint = LocalContentColor.current.copy(alpha = 0.3f)
                    )
                },
                trailingIcon = null,
                modifier = Modifier
                    .background(
                        MaterialTheme.colors.surface,
                        RoundedCornerShape(percent = 50)
                    )
                    .padding(4.dp)
                    .height(25.dp),
                fontSize = 10.sp,
                placeholderText = "Search"
            )
        }
        
        Spacer(modifier = Modifier.width(30.dp))


        Box() {

            Row() {


                IconButton(modifier = Modifier.
                then(Modifier.size(20.dp)),
                    onClick = {
                        if (showSearchBar == true){
                            viewModel.onSearch("")
                        }
                            showSearchBar = !showSearchBar
                    }) {

                    Icon(
                        painterResource(id = if(showSearchBar) R.drawable.ic_cancel else R.drawable.ic_search),
                        "contentDescription",
                        tint = MainBlack)
                }

                Spacer(modifier = Modifier.width(20.dp))

                IconButton(modifier = Modifier.
                then(Modifier.size(20.dp)),
                    onClick = { }) {
                    Icon(
                        painterResource(id = R.drawable.ic_menu),
                        "contentDescription",
                        tint = MainBlack)
                }
            }
        }





    }
}

@Composable
private fun CustomTextField(
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholderText: String = "Placeholder",
    fontSize: TextUnit = MaterialTheme.typography.body2.fontSize
) {
    val viewModel: HomeScreenViewModel = hiltViewModel()
    val state = viewModel.state.value
    BasicTextField(
        modifier = modifier
            .background(
                MaterialTheme.colors.surface,
                MaterialTheme.shapes.small,
            )
            .fillMaxWidth(0.6f),
        value = state.searchQuery,
        onValueChange = viewModel::onSearch,
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colors.primary),
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colors.onSurface,
            fontSize = fontSize
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()
                Box(Modifier.weight(1f)) {
                    if (state.searchQuery.isEmpty()) Text(
                        placeholderText,
                        style = LocalTextStyle.current.copy(
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
                            fontSize = fontSize
                        )
                    )
                    innerTextField()
                }
                if (trailingIcon != null) trailingIcon()
            }
        }
    )
}