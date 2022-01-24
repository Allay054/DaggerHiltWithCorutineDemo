package com.allaykhalil.demo.movifetcher.ui.main

import com.allaykhalil.demo.movifetcher.managers.DataManager
import com.allaykhalil.demo.movifetcher.model.receiveModels.AllMoviesListData
import com.allaykhalil.demo.movifetcher.model.base.ResponsePacket
import com.allaykhalil.demo.movifetcher.model.base.State
import com.allaykhalil.demo.movifetcher.model.sendModels.GetAllMoviesSendModel
import com.allaykhalil.demo.movifetcher.ui.base.BaseRepository
import javax.inject.Inject

class MainRepository @Inject constructor(dataManager: DataManager) :
    BaseRepository(dataManager) {

    suspend fun fetchMoviesList(getAllMoviesSendModel: GetAllMoviesSendModel): State<AllMoviesListData> {
        return makeApiCall {
            dataManager.getApiHelper().getAllMovies(getAllMoviesSendModel.dataParams.toQueryMap())
        }
    }


}