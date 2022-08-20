package com.example.foodrecipes2.core.SharedWidget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.foodrecipes2.R
import com.example.foodrecipes2.ui.theme.BottomMenuSelectedItemBg
import com.example.foodrecipes2.ui.theme.MainBlack
import com.example.foodrecipes2.ui.theme.MainWhite

@Composable
fun CustomBottomBar(){

    var selectedMenu by remember {
        mutableStateOf("home")
    }

    BottomNavigation(
        backgroundColor = MainBlack,
        modifier = Modifier
            .padding(10.dp, 0.dp)
            .height(60.dp)
            .fillMaxWidth()
            .clip(
                CircleShape
            )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxSize()) {
            listOf<BottomMenuItem>(BottomMenuItem.Home, BottomMenuItem.Chef, BottomMenuItem.Settings, BottomMenuItem.Profile).forEach {
                BottomNavItem(item = it, onClickFun = {
                                                      selectedMenu = it.title
                }, isSelected = selectedMenu.equals(it.title) )
            }
        }
    }
}

@Composable
fun BottomNavItem(item: BottomMenuItem, onClickFun : (BottomMenuItem)->Unit, isSelected: Boolean){
    IconButton(modifier = Modifier.
    then(Modifier.size(30.dp)),
        onClick = {
            onClickFun(item)
        }) {
        Icon(
            painterResource(id = item.icon),
            "contentDescription",
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(if (isSelected) BottomMenuSelectedItemBg else MainBlack)
                .padding(5.dp),
            tint = MainWhite)
    }
}

sealed class BottomMenuItem(val title: String, val icon: Int){
    object Home: BottomMenuItem("home", R.drawable.ic_home)
    object Chef: BottomMenuItem("chef", R.drawable.ic_chef)
    object Settings: BottomMenuItem("settings", R.drawable.ic_setting)
    object Profile: BottomMenuItem("profile", R.drawable.ic_person)
}