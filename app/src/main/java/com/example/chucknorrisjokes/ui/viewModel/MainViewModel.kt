package com.example.chucknorrisjokes.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisjokes.data.repository.Repository
import kotlinx.coroutines.launch


sealed class JokeState {
    object Loading : JokeState()
    object Done : JokeState()
    data class Error(val error: Exception) : JokeState()
}

interface JokeActions {
    fun fetchJoke()
}

class MainViewModel : ViewModel(), JokeActions {
    private val repository = Repository()

    private val _joke = MutableLiveData<String>()
    val joke: LiveData<String> get() = _joke

    private val _loadingState = MutableLiveData<JokeState>(JokeState.Loading)
    val loadingState = _loadingState as LiveData<JokeState>

    override fun fetchJoke() {
        setState()
        viewModelScope.launch {
            try {
                val response = repository.getRandomJoke()
                _joke.value = response.value
                setState(JokeState.Done)
            } catch (e: Exception) {
                setState(JokeState.Error(error = e))
            }
        }
    }

    private fun setState(state: JokeState = JokeState.Loading) {
        _loadingState.value = state
    }
}