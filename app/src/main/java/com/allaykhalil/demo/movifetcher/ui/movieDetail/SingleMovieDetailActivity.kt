package com.allaykhalil.demo.movifetcher.ui.movieDetail

import android.content.Intent
import androidx.activity.viewModels
import com.allaykhalil.demo.movifetcher.R
import com.allaykhalil.demo.movifetcher.BR
import com.allaykhalil.demo.movifetcher.databinding.ActivityMovieDetailBinding
import com.allaykhalil.demo.movifetcher.ui.base.BaseActivity
import com.allaykhalil.demo.movifetcher.ui.main.MainActivity
import com.allaykhalil.demo.movifetcher.ui.movieDetail.viewModels.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleMovieDetailActivity :
    BaseActivity<MovieDetailViewModel, ActivityMovieDetailBinding>(R.layout.activity_movie_detail),
    MovieDetailNavigator {

    override val viewModel: MovieDetailViewModel by viewModels()
    override fun getBindingVariable() = BR.viewModel

    override fun initUi() {
        viewModel.setNavigator(this)
        viewModel.fetchFromServerClick()


    }



    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@SingleMovieDetailActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}