package com.example.spreng.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.spreng.data.DefaultMainNavItemRepo
import com.example.spreng.data.MainNavItem
import com.example.spreng.data.MainNavRoute
import com.example.spreng.ui.home.HomeScreen
import com.example.spreng.ui.home.InfoScreen
import com.example.spreng.ui.home.RankingScreen
import com.example.spreng.ui.home.RevisionScreen
import com.example.spreng.ui.home.SettingScreen


@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BaseBottomBar(
                navController,
                DefaultMainNavItemRepo.getItems()
            )
        }
    ) { innerPadding ->
        NavigationScreen(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Composable
private fun NavigationScreen(
    navController : NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DefaultMainNavItemRepo.getRoute(MainNavRoute.HOME),
        modifier = modifier,
    ) {
        composable(route = DefaultMainNavItemRepo.getRoute(MainNavRoute.HOME)) {
            HomeScreen()
        }

        composable(route = DefaultMainNavItemRepo.getRoute(MainNavRoute.REVISION)) {
            RevisionScreen()
        }

        composable(route = DefaultMainNavItemRepo.getRoute(MainNavRoute.RANKING)) {
            RankingScreen()
        }

        composable(route = DefaultMainNavItemRepo.getRoute(MainNavRoute.INFO)) {
            InfoScreen()
        }

        composable(route = DefaultMainNavItemRepo.getRoute(MainNavRoute.SETTING)) {
            SettingScreen()
        }
    }
}


@Composable
private fun BaseBottomBar(
    navController: NavHostController,
    homeNavItems: List<MainNavItem>,
    modifier: Modifier = Modifier
) {

    NavigationBar(
        modifier = modifier
    ) {

        homeNavItems.forEach { navItem ->

            NavigationBarItem(
                onClick = {
                    navController.navigate(navItem.route)
                },
                selected = navController.currentBackStackEntryAsState().value?.destination?.route == navItem.route,
                icon = {
                    Icon(
                        painter = painterResource(navItem.icon),
                        contentDescription = stringResource(navItem.contentDescription)
                    )
                }
            )

        }

    }
}


@Preview(showBackground = false)
@Composable
private fun Preview() {
    MainScreen()
}