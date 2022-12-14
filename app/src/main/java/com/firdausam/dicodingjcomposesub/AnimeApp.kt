package com.firdausam.dicodingjcomposesub

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.firdausam.dicodingjcomposesub.ui.navigation.KEY_ID
import com.firdausam.dicodingjcomposesub.ui.navigation.NavigationItem
import com.firdausam.dicodingjcomposesub.ui.navigation.Screen
import com.firdausam.dicodingjcomposesub.ui.screen.about.AboutScreen
import com.firdausam.dicodingjcomposesub.ui.screen.detail.DetailScreen
import com.firdausam.dicodingjcomposesub.ui.screen.favorite.FavoriteScreen
import com.firdausam.dicodingjcomposesub.ui.screen.home.HomeScreen
import com.firdausam.dicodingjcomposesub.util.currentRoute
import com.firdausam.dicodingjcomposesub.util.navigateCommon
import com.firdausam.dicodingjcomposesub.util.rememberMySnackbar
import com.firdausam.dicodingjcomposesub.util.toIntentUrl

@Composable
fun AnimeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val currentRoute = navController.currentRoute
    val context = LocalContext.current

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            if (currentRoute != Screen.Detail.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier,
        content = { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Home.route) {
                    HomeScreen(navigateToDetail = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    })
                }
                composable(Screen.Favorite.route) {
                    FavoriteScreen(navigateToDetail = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    })
                }
                composable(Screen.About.route) {
                    AboutScreen(onSocmedIconClick = { context.toIntentUrl(it) })
                }
                composable(
                    route = Screen.Detail.route,
                    arguments = listOf(navArgument(KEY_ID) { type = NavType.IntType })
                ) {
                    val id: Int = it.arguments?.getInt(KEY_ID) ?: -1
                    DetailScreen(
                        id = id,
                        onBackClick = { navController.navigateUp() },
                        onToFavorite = {
                            navController.popBackStack()
                            navController.navigateCommon(Screen.Favorite.route)
                        },
                        mySnackbar = rememberMySnackbar(scaffoldState = scaffoldState)
                    )
                }
            }
        }
    )
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.home),
                icon = Icons.Default.Home,
                screen = Screen.Home,
                contentDesc = "home_page"
            ),
            NavigationItem(
                title = stringResource(id = R.string.favorite),
                icon = Icons.Default.Favorite,
                screen = Screen.Favorite,
                contentDesc = "favorite_page"
            ),
            NavigationItem(
                title = stringResource(id = R.string.about),
                icon = Icons.Default.AccountCircle,
                screen = Screen.About,
                contentDesc = "about_page"
            ),
        )

        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.contentDesc
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigateCommon(item.screen.route)
                    }
                )
            }
        }
    }
}
