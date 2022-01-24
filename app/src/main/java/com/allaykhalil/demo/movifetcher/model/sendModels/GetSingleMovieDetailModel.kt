package com.allaykhalil.demo.movifetcher.model.sendModels


data class GetSingleMovieDetailModel(val dataParams: DataParamModel) {


    class DataParamModel(
        private val apiToken: String


    ) {
        fun toQueryMap(): HashMap<String, String> {
            val params = HashMap<String, String>()
            params["api_key"] = apiToken

            return params
        }
    }
}