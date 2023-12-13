package com.example.githubuserfilter.usersfilter.data.api

import com.example.githubuserfilter.core.data.api.PAGE_SIZE
import com.example.githubuserfilter.usersfilter.data.api.model.UserFilterResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FilterUsersApi {
    @GET("search/users")
    suspend fun filterUsers(
        @Query("q") keyword: String,
        @Query("page") page: Int,
        @Query("per_page") usersPerPage: Int = PAGE_SIZE
    ): Response<UserFilterResultDto>
}
