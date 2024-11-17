package com.example.spreng.ui.navigation

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.spreng.R
import com.example.spreng.StudyActivity
import com.example.spreng.data.DefaultMainNavItemRepo
import com.example.spreng.data.MainNavItem
import com.example.spreng.data.MainNavRoute
import com.example.spreng.data.NavRanking
import com.example.spreng.ui.mainscreen.home.HomeScreen
import com.example.spreng.ui.mainscreen.info.InfoScreen
import com.example.spreng.ui.mainscreen.ranking.AllRankingScreen
import com.example.spreng.ui.mainscreen.ranking.RankingScreen
import com.example.spreng.ui.mainscreen.revision.RevisionScreen
import com.example.spreng.ui.mainscreen.setting.SettingScreen
import com.example.spreng.ui.studyscreen.BaseStudyScreen


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
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = DefaultMainNavItemRepo.getRoute(MainNavRoute.HOME),
        modifier = modifier,
    ) {
        composable(route = DefaultMainNavItemRepo.getRoute(MainNavRoute.HOME)) {
            HomeScreen(
                onLessonClicked = {
                    val studyActivityIntent = Intent(context, StudyActivity::class.java)
                    context.startActivity(studyActivityIntent)
                }
            )
        }

        composable(route = DefaultMainNavItemRepo.getRoute(MainNavRoute.REVISION)) {
            RevisionScreen()
        }

        composable(route = DefaultMainNavItemRepo.getRoute(MainNavRoute.RANKING)) {
            RankingScreen(navController)
        }

        composable(route = NavRanking.AllRank.name) {
            AllRankingScreen(navController)
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
        modifier = modifier,
        containerColor = Color(135, 183, 239),
    ) {
        homeNavItems.forEach { navItem ->
            val selected = navController.currentBackStackEntryAsState()
                .value?.destination?.route == navItem.route

            NavigationBarItem(
                onClick = {
                    navController.navigate(navItem.route)
                },
                selected = selected,
                icon = {
                    Icon(
                        painter = painterResource(navItem.icon),
                        contentDescription = stringResource(navItem.contentDescription),
                        modifier = if (selected) {
                            Modifier
                                .border(
                                    BorderStroke(
                                        dimensionResource(R.dimen.tiny),
                                        Color.Yellow
                                    ),
                                    shape = RoundedCornerShape(dimensionResource(R.dimen.small))
                                )
                                .padding(dimensionResource(R.dimen.small))
                        } else {
                            Modifier
                        }
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = Color.Black,
                    unselectedIconColor = Color.Black
                )
            )

        }
    }

    HorizontalDivider(
        thickness = dimensionResource(R.dimen.very_tiny),
        color = Color.Black
    )
}


@Preview(showBackground = false)
@Composable
private fun Preview() {
    MainScreen()
}