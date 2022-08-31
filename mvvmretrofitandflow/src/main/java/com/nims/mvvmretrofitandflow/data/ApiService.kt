package com.nims.mvvmretrofitandflow.data

import retrofit2.http.GET

interface ApiService {
    @GET("API/MostPopularMovies/k_9v5jw2c1")
    suspend fun getMostPopularMovies() : MovieResponse
}