package com.allaykhalil.demo.movifetcher.ui.movieDetail

import com.allaykhalil.demo.movifetcher.managers.DataManager
import com.allaykhalil.demo.movifetcher.model.base.State
import com.allaykhalil.demo.movifetcher.model.receiveModels.SingleMovieDetailData
import com.allaykhalil.demo.movifetcher.model.sendModels.GetSingleMovieDetailModel
import com.allaykhalil.demo.movifetcher.ui.base.BaseRepository
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(dataManager: DataManager) :
    BaseRepository(dataManager) {

    suspend fun fetchMovieDetail(
        movieId: Int,
        getSingleMovieDetailModel: GetSingleMovieDetailModel
    ): State<SingleMovieDetailData> {
        return makeApiCall {
            dataManager.getApiHelper().getMovieDetail(
                movieId.toString(),
                getSingleMovieDetailModel.dataParams.toQueryMap()
            )
        }
    }
}