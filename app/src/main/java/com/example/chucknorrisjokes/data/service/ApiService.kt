package com.example.chucknorrisjokes.data.service

import com.example.chucknorrisjokes.data.domain.model.Joke
import retrofit2.http.GET

interface ApiService {
    @GET("jokes/random")
    suspend fun getRandomJoke(): Joke
}