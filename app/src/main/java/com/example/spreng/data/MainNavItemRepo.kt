package com.example.spreng.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.spreng.R



class MainNavItem(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int
)


enum class MainNavRoute {
    HOME, REVISION, RANKING, INFO, SETTING
}


interface MainNavItemRepo {
    fun getItems() : List<MainNavItem>
    fun getRoute(route: MainNavRoute) : String
}


object DefaultMainNavItemRepo : MainNavItemRepo {
    override fun getItems(): List<MainNavItem> {
        return listOf(
            MainNavItem(MainNavRoute.HOME.name, R.drawable.home_ic_home, R.string.home_nav_item_home),
            MainNavItem(MainNavRoute.REVISION.name, R.drawable.home_ic_revision, R.string.home_nav_item_revision),
            MainNavItem(MainNavRoute.RANKING.name, R.drawable.home_ic_ranking, R.string.home_nav_item_ranking),
            MainNavItem(MainNavRoute.INFO.name, R.drawable.home_ic_info, R.string.home_nav_item_info),
            MainNavItem(MainNavRoute.SETTING.name, R.drawable.home_ic_setting, R.string.home_nav_item_setting),
        )
    }

    override fun getRoute(route: MainNavRoute): String {
        return route.name
    }

}

