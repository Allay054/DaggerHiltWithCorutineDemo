package com.allaykhalil.demo.movifetcher.ui.base

import com.allaykhalil.demo.movifetcher.R
import com.allaykhalil.demo.movifetcher.managers.DataManager
import com.allaykhalil.demo.movifetcher.model.base.ResponseError
import com.allaykhalil.demo.movifetcher.model.base.State
import com.allaykhalil.demo.movifetcher.utils.NetworkUtils
import com.allaykhalil.demo.movifetcher.utils.StatusCodes
import retrofit2.Response

open class BaseRepository(val dataManager: DataManager) {

    suspend fun <T : Any> makeApiCall(
        apiCall: suspend () -> Response<T>,
    ): State<T> {
        return verifySessionAndMakeApiCall(apiCall, false)
    }

    protected open fun isNetworkNotAvailable(): Boolean {
        var isConnected = true
        if (!NetworkUtils.isNetworkConnected(dataManager.getResourceManager().getContext())) {
            isConnected = false
        }
        return !isConnected
    }

    private suspend fun <T : Any> verifySessionAndMakeApiCall(
        call: suspend () -> Response<T>,
        verifySession: Boolean = true,
    ): State<T> {
        if (isNetworkNotAvailable()) {
            return State.Error(
                ResponseError(
                    StatusCodes.INTERNET_NOT_AVAILABLE, dataManager.getResourceManager().getString(
                        R.string.internet_error
                    )
                )
            )
        }

        return apiOutput(call)

    }

    private suspend fun <T : Any> apiOutput(
        call: suspend () -> Response<T>,
    ): State<T> {
        val response = call.invoke()
        return if (response.isSuccessful)
            State.Success(response.body()!!)
        else {
            State.Error(ResponseError(response.code(), response.message()))
        }
    }
}