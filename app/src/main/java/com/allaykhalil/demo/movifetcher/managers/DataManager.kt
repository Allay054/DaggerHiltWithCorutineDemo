package com.allaykhalil.demo.movifetcher.managers

//import com.allaykhalil.demo.movifetcher.data.prefs.PreferencesHelper
import com.allaykhalil.demo.movifetcher.data.remote.ApiService
import com.allaykhalil.demo.movifetcher.utils.ResourceProvider

interface DataManager {
    fun getApiHelper(): ApiService
    fun getResourceManager(): ResourceProvider
   //fun getPreferencesHelper(): PreferencesHelper
    /*fun getDbHelper(): DbHelper*/
}