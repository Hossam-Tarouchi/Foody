package com.example.foodrecipes2.presentation.screens.detail_screen

import com.example.foodrecipes2.domain.model.Meal

sealed class DetailViewAction{
    data class GetRecipiesById(val id: String): DetailViewAction()
    data class IsMealLiked(val id: String): DetailViewAction()
    data class SwitchLikedRecipie(val state: Boolean, val meal: Meal): DetailViewAction()
}
