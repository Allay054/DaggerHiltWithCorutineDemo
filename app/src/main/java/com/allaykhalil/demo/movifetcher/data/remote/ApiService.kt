package com.allaykhalil.demo.movifetcher.data.remote

import com.allaykhalil.demo.movifetcher.model.receiveModels.AllMoviesListData
import com.allaykhalil.demo.movifetcher.model.receiveModels.SingleMovieDetailData
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.QueryMap

/*
 * Created by Allay on 1/19/2022
 */

interface ApiService {
    @GET("discover/movie")
    suspend fun getAllMovies(
        @QueryMap params: HashMap<String, String>
    ): Response<AllMoviesListData>


    @GET("movie/{param}")
    suspend fun getMovieDetail(
        @Path("param") param: String,
        @QueryMap params: HashMap<String, String>
    ): Response<SingleMovieDetailData>

}