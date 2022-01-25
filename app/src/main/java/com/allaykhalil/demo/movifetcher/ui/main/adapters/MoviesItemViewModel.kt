package com.allaykhalil.demo.movifetcher.ui.main.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.allaykhalil.demo.movifetcher.R
import com.allaykhalil.demo.movifetcher.model.receiveModels.MoviesList
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class MoviesItemViewModel(model: MoviesList) {
    val name = MutableLiveData(model.original_title)
    val releaseDate = MutableLiveData("Release Date : " + model.release_date)
    val imagePath = MutableLiveData(model.poster_path)



    companion object {
        @JvmStatic
        @BindingAdapter("avatar")
        fun loadImage(imageView: ImageView, imageURL: String?) {
            Glide.with(imageView.context)
                .setDefaultRequestOptions(
                    RequestOptions()
                      /*  .circleCrop()*/
                )
                .load("http://image.tmdb.org/t/p/w185$imageURL")
                .placeholder(R.drawable.picture)
                .into(imageView)
        }
    }
}

