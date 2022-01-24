package com.allaykhalil.demo.movifetcher.ui.main.viewModels

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.allaykhalil.demo.movifetcher.model.base.State
import com.allaykhalil.demo.movifetcher.model.receiveModels.MoviesList
import com.allaykhalil.demo.movifetcher.model.sendModels.GetAllMoviesSendModel
import com.allaykhalil.demo.movifetcher.ui.base.BaseViewModel
import com.allaykhalil.demo.movifetcher.ui.main.MainNavigator
import com.allaykhalil.demo.movifetcher.ui.main.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var mainRepository: MainRepository) :
    BaseViewModel<MainNavigator>(mainRepository.dataManager) {
    val allMoviesList = MutableLiveData<ArrayList<MoviesList>>()
    val observableArrayList = ObservableArrayList<MoviesList>()


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

        val getAllMoviesSendModel = GetAllMoviesSendModel(
            GetAllMoviesSendModel.DataParamModel(
                "6f2ef28c6423ac586d223e818a415794"
            )
        )
        viewModelScope.launch {
            makeApiCall(getAllMoviesSendModel)
            // saveRecordsToDb()
        }
    }

    private suspend fun makeApiCall(getAllMoviesSendModel: GetAllMoviesSendModel) {
       /* if (contactList. > 0)
            contactList.clear()*/

        withContext(viewModelScope.coroutineContext) {
            getNavigator()?.showProgressBar()
            when (val request = mainRepository.fetchMoviesList(getAllMoviesSendModel)) {
                is State.Success -> {
                    getNavigator()?.hideProgressBar()
                    request.wrapperData.let {
                        if (!it.resultsList.isNullOrEmpty()) {
                            allMoviesList.value = it.resultsList!!
                        } else {
                            getNavigator()?.showSuccessMessage("No Data Found")
                        }
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
        allMoviesList.value?.let {
            if (it.isNotEmpty()) {
                observableArrayList.addAll(it)
            }
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