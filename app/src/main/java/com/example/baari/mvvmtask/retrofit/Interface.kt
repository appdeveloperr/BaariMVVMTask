package com.example.baari.mvvmtask.retrofit

import com.example.baari.mvvmtask.retrofit.responce.ApiDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Interface {

    @GET("users")
    suspend fun getAllUsers(
        @Query("page") page: Int,
    ): ApiDataResponse

}