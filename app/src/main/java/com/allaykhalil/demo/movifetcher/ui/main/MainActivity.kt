package com.allaykhalil.demo.movifetcher.ui.main

import androidx.activity.viewModels
import com.allaykhalil.demo.movifetcher.R
import com.allaykhalil.demo.movifetcher.BR
import com.allaykhalil.demo.movifetcher.databinding.ActivityMainBinding
import com.allaykhalil.demo.movifetcher.ui.base.BaseActivity
import com.allaykhalil.demo.movifetcher.ui.main.adapters.MoviesListAdapter
import com.allaykhalil.demo.movifetcher.ui.main.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main),
    MainNavigator {
    @Inject
    lateinit var adapter: MoviesListAdapter
    override val viewModel: MainViewModel by viewModels()
    override fun getBindingVariable() = BR.viewModel

    override fun initUi() {
        viewModel.setNavigator(this)
        bindings.rvContacts.adapter = adapter

        viewModel.allMoviesList.observe(this,
            {
                viewModel.observableArrayList.addAll(it)
            })
    }


}