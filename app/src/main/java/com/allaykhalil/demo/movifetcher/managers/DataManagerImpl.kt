package com.allaykhalil.demo.movifetcher.managers

/*import com.allaykhalil.demo.movifetcher.data.prefs.PreferencesHelper*/
import com.allaykhalil.demo.movifetcher.data.remote.ApiService
import com.allaykhalil.demo.movifetcher.utils.ResourceProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManagerImpl @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val appService: ApiService
    /*,
    private val preferencesHelper: PreferencesHelper,*/
    /*  private val dbHelper: DbHelper*/
) : DataManager {

    override fun getResourceManager(): ResourceProvider {
        return resourceProvider
    }

    override fun getApiHelper(): ApiService {
        return appService
    }

    /*override fun getDbHelper(): DbHelper {
        return dbHelper
    }
*/
  /*  override fun getPreferencesHelper(): PreferencesHelper {
       return  preferencesHelper
    }*/
}