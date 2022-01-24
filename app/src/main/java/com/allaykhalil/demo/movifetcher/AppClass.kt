package com.allaykhalil.demo.movifetcher

import android.app.Application
import com.allaykhalil.demo.movifetcher.managers.DataManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AppClass : Application() {
    @Inject
    lateinit var dataManager: DataManager
}