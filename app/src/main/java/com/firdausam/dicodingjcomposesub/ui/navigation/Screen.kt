package com.firdausam.dicodingjcomposesub.ui.navigation

const val KEY_ID = "id"

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object Detail: Screen("detail/{$KEY_ID}") {
        fun createRoute(id: Int) = "detail/$id"
    }
}