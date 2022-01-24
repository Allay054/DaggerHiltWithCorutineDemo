package com.allaykhalil.demo.movifetcher.ui.movieDetail.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.allaykhalil.demo.movifetcher.model.base.State
import com.allaykhalil.demo.movifetcher.model.receiveModels.SingleMovieDetailData
import com.allaykhalil.demo.movifetcher.model.sendModels.GetSingleMovieDetailModel
import com.allaykhalil.demo.movifetcher.ui.base.BaseViewModel
import com.allaykhalil.demo.movifetcher.ui.movieDetail.MovieDetailNavigator
import com.allaykhalil.demo.movifetcher.ui.movieDetail.MovieDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(var movieDetailRepository: MovieDetailRepository) :

    BaseViewModel<MovieDetailNavigator>(movieDetailRepository.dataManager) {
    var title = MutableLiveData("")
    var releaseDate = MutableLiveData("")
    var movieOverView = MutableLiveData("")

    val movieDetail = MutableLiveData<SingleMovieDetailData>()
    val observableMovieDetail = Observable()


    /* fun fetchFromDbClick() {
         if (observableArrayList.size > 0)
             observableArrayList.clear()

         viewModelScope.launch {
             mainRepository.fetchContactsFromDb().run {
                 *//*
                * Add a small delay to reflect the changes on the UI
                *//*
                delay(5)
                if (this != null) {
                    if (this.isNotEmpty()) {
                        contactList.value = this
                    }
                }
            }
        }
    }*/

    fun fetchFromServerClick() {

        val getSingleMovieDetailModel = GetSingleMovieDetailModel(
            GetSingleMovieDetailModel.DataParamModel(
                "6f2ef28c6423ac586d223e818a415794"
            )
        )
        viewModelScope.launch {
            makeApiCall(550, getSingleMovieDetailModel)
            // saveRecordsToDb()
        }
    }

    private suspend fun makeApiCall(id: Int, getSingleMovieDetailModel: GetSingleMovieDetailModel) {
        /* if (contactList. > 0)
             contactList.clear()*/

        withContext(viewModelScope.coroutineContext) {
            getNavigator()?.showProgressBar()
            when (val request =
                movieDetailRepository.fetchMovieDetail(id, getSingleMovieDetailModel)) {
                is State.Success -> {
                    getNavigator()?.hideProgressBar()
                    request.wrapperData.let {
                        /* if (!it.resultsList.isNullOrEmpty()) {
                             movieDetail.value = it.resultsList!!
                         } else {
                             getNavigator()?.showSuccessMessage("No Data Found")
                         }*/
                        title.value = it.original_title.toString()
                        releaseDate.value = it.release_date.toString()
                        movieOverView.value = it.overview.toString()
                        getNavigator()?.showSuccessMessage(it.original_title.toString())
                    }
                }
                is State.Error -> {
                    getNavigator()?.showErrorMessage(request.responseError.errorMessage)
                    getNavigator()?.hideProgressBar()

                    resetData()
                }
            }
        }
    }

    private fun resetData() {
        /*
        * Function to reset data back to View in case Api call fails
        */
        movieDetail.value?.let {
            //observableMovieDetail.addObserver(it)
            title.value = it.title.toString()
            releaseDate.value = it.release_date.toString()
            movieOverView.value = it.overview.toString()
        }
    }

    /*private fun saveRecordsToDb() {
        contactList.value?.let {
            if (it.isNotEmpty()) {
                viewModelScope.launch(Dispatchers.IO) {
                    dataManager.getDbHelper().deleteAllContacts()
                    dataManager.getDbHelper().insertContacts(it)
                }
            }
        }
    }*/
}