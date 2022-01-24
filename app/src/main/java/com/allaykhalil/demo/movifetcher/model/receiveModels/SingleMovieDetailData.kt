package com.allaykhalil.demo.movifetcher.model.receiveModels

import com.google.gson.annotations.SerializedName

/**
 * Created by Allay on 1/24/2022
 */
data class SingleMovieDetailData(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdrop_path: String? = null,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: String? = null,
    @SerializedName("budget")
    val budget: String? = null,
    @SerializedName("genres")
    val genresList: ArrayList<GenresData>? = null,
    @SerializedName("homepage")
    val homepage: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("imdb_id")
    val imdb_id: String? = null,
    @SerializedName("original_language")
    val original_language: String? = null,
    @SerializedName("original_title")
    val original_title: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: String? = null,
    @SerializedName("poster_path")
    val poster_path: String? = null,
    @SerializedName("release_date")
    val release_date: String? = null,
    @SerializedName("revenue")
    val revenue: String? = null,
    @SerializedName("runtime")
    val runtime: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("tagline")
    val tagline: String? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val vote_average: Double? = null,
    @SerializedName("vote_count")
    val vote_count: Int? = null,
    @SerializedName("production_companies")
    val productionCompaniesList: ArrayList<ProductionCompanies>? = null,
    @SerializedName("production_countries")
    val productionCountriesList: ArrayList<ProductionCountries>? = null,
    @SerializedName("spoken_languages")
    val spokenLanguagesList: ArrayList<SpokenLanguages>? = null,

    )

data class GenresData(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
)

data class ProductionCompanies(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("logo_path")
    val logo_path: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("origin_country")
    val origin_country: String? = null
)

data class ProductionCountries(
    @SerializedName("iso_3166_1")
    val iso_3166_1: String? = null,
    @SerializedName("name")
    val name: String? = null
)

data class SpokenLanguages(
    @SerializedName("english_name")
    val english_name: String? = null,
    @SerializedName("iso_639_1")
    val iso_639_1: String? = null,
    @SerializedName("name")
    val name: String? = null
)