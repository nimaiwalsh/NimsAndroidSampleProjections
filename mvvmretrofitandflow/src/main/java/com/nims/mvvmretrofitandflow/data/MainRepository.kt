package com.nims.mvvmretrofitandflow.data

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getPopularMovies() = flow {
        emit(NetworkResult.Loading(true))
        val response = apiService.getMostPopularMovies()
        emit(NetworkResult.Success(response.items))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

}