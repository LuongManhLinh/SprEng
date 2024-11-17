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

enum class NavRanking {
    AllRank, Profile
}

interface MainNavItemRepo {
    fun getItems() : List<MainNavItem>
    fun getRoute(route: MainNavRoute) : String
}

enum class RevisionRoute {
    MISTAKE, VOCAB
}


object DefaultMainNavItemRepo : MainNavItemRepo {
    override fun getItems(): List<MainNavItem> {
        return listOf(
            MainNavItem(MainNavRoute.HOME.name, R.drawable.nav_ic_home, R.string.home_nav_item_home),
            MainNavItem(MainNavRoute.REVISION.name, R.drawable.nav_ic_revision, R.string.home_nav_item_revision),
            MainNavItem(MainNavRoute.RANKING.name, R.drawable.nav_ic_ranking, R.string.home_nav_item_ranking),
            MainNavItem(MainNavRoute.INFO.name, R.drawable.nav_ic_info, R.string.home_nav_item_info),
            MainNavItem(MainNavRoute.SETTING.name, R.drawable.nav_ic_setting, R.string.home_nav_item_setting),
        )
    }

    override fun getRoute(route: MainNavRoute): String {
        return route.name
    }

}

class RankCardItem (
    @DrawableRes val img: Int,
    @StringRes val nameRank: Int,
    @StringRes val descriptionRank: Int
)

fun getRankCardItem(): List<RankCardItem> {
    return listOf(
        RankCardItem(R.drawable.bronze, R.string.bronze_rank, R.string.description_bronze),
        RankCardItem(R.drawable.silver, R.string.silver_rank, R.string.description_silver),
        RankCardItem(R.drawable.gold, R.string.gold_rank, R.string.description_gold),
        RankCardItem(R.drawable.platinum, R.string.platinum_rank, R.string.description_platinum),
        RankCardItem(R.drawable.diamond, R.string.diamond_rank, R.string.description_diamond)
    )
}

