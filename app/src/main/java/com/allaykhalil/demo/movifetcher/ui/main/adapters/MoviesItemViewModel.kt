package com.allaykhalil.demo.movifetcher.ui.main.adapters

import androidx.lifecycle.MutableLiveData
import com.allaykhalil.demo.movifetcher.model.receiveModels.MoviesList


class MoviesItemViewModel(model: MoviesList) {
    val name = MutableLiveData(model.original_title)
    val releaseDate = MutableLiveData("Release Date : " + model.release_date)
    val imagePath = MutableLiveData(model.poster_path)

   
}

