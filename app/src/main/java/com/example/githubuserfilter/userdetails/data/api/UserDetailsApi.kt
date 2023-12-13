package com.example.githubuserfilter.userdetails.data.api

import com.example.githubuserfilter.userdetails.data.api.model.DetailsUserInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserDetailsApi {
    @GET("users/{username}")
    suspend fun getUserDetails(
        @Path("username") username: String
    ): Response<DetailsUserInfoDto>
}
