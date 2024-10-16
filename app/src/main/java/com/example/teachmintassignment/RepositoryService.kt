package com.example.teachmintassignment

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1
    ): Response<GitHubResponse>

    companion object {
        private const val BASE_URL = "https://api.github.com/"

        fun create(): RepositoryService {
            val retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build()

            return retrofit.create(RepositoryService::class.java)
        }
    }
}
