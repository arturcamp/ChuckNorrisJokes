package com.example.chucknorrisjokes.data.repository

import com.example.chucknorrisjokes.data.domain.model.Joke
import com.example.chucknorrisjokes.data.service.RetrofitInstance

class Repository {

    private val api = RetrofitInstance.api

    suspend fun getRandomJoke(): Joke {
        return api.getRandomJoke()
    }
}
