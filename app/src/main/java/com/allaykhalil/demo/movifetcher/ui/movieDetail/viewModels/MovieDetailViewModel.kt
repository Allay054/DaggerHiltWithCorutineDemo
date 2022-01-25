package com.allaykhalil.demo.movifetcher.ui.movieDetail.viewModels


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.allaykhalil.demo.movifetcher.R
import com.allaykhalil.demo.movifetcher.model.base.State
import com.allaykhalil.demo.movifetcher.model.receiveModels.SingleMovieDetailData
import com.allaykhalil.demo.movifetcher.model.sendModels.GetSingleMovieDetailModel
import com.allaykhalil.demo.movifetcher.ui.base.BaseViewModel
import com.allaykhalil.demo.movifetcher.ui.movieDetail.MovieDetailNavigator
import com.allaykhalil.demo.movifetcher.ui.movieDetail.MovieDetailRepository
import com.allaykhalil.demo.movifetcher.utils.GlobalData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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
    var imageUrl = MutableLiveData("")
    val movieDetail = MutableLiveData<SingleMovieDetailData>()
    val observableMovieDetail = Observable()




    fun fetchFromServerClick() {

        val getSingleMovieDetailModel = GetSingleMovieDetailModel(
            GetSingleMovieDetailModel.DataParamModel(
                "6f2ef28c6423ac586d223e818a415794"
            )
        )
        viewModelScope.launch {
            makeApiCall(GlobalData.selectedMovieId!!, getSingleMovieDetailModel)
            // saveRecordsToDb()
        }
    }

    private suspend fun makeApiCall(id: Int, getSingleMovieDetailModel: GetSingleMovieDetailModel) {


        withContext(viewModelScope.coroutineContext) {
            getNavigator()?.showProgressBar()
            when (val request =
                movieDetailRepository.fetchMovieDetail(id, getSingleMovieDetailModel)) {
                is State.Success -> {
                    getNavigator()?.hideProgressBar()
                    request.wrapperData.let {

                        title.value = it.original_title.toString()
                        releaseDate.value = it.release_date.toString()
                        movieOverView.value = it.overview.toString()
                        imageUrl.value = it.poster_path.toString()

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
            releaseDate.value = "Release Date: " + it.release_date.toString()
            movieOverView.value = it.overview.toString()
            imageUrl.value = it.poster_path.toString()
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("avatar")
        fun loadImage(imageView: ImageView, imageURL: String?) {
            Glide.with(imageView.context)
                .setDefaultRequestOptions(
                    RequestOptions()
                )
                .load("http://image.tmdb.org/t/p/w185$imageURL")
                .placeholder(R.drawable.picture)
                .into(imageView)
        }
    }
}