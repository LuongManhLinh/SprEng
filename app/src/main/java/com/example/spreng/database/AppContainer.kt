package com.example.spreng.database

import android.app.Application


class UserApplication : Application() {
    val database: InventoryDatabase by lazy { InventoryDatabase.getDatabase(this) }

    companion object {
        lateinit var instance: UserApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
