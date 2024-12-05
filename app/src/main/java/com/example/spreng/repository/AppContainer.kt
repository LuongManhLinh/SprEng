package com.example.spreng.repository

import android.app.Application
import android.content.Context


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
