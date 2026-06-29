package com.example.kapitalchallenge.architecture.data.net

import com.example.kapitalchallenge.architecture.data.disney.DisneyCharactersRespose
import com.example.kapitalchallenge.architecture.data.routes.DisneyCharactersRoutes
import retrofit2.http.GET
import retrofit2.http.Query

interface DisneyCharactersApiServices {
    @GET(DisneyCharactersRoutes.GET_CHARACTERS)
    suspend fun getDisneyCharacters(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 20
    ): DisneyCharactersRespose
}