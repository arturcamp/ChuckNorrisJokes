package com.example.chucknorrisjokes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.chucknorrisjokes.ui.theme.ChuckNorrisJokesTheme
import com.example.chucknorrisjokes.ui.viewModel.JokeActions
import com.example.chucknorrisjokes.ui.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChuckNorrisJokesTheme {
                Scaffold(
                    content = {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp, vertical = 32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            StartActivity(viewModel)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun StartActivity(viewModel: MainViewModel) {
    val joke by viewModel.joke.observeAsState("")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainButton(onClick = { viewModel.fetchJoke() })
        ShowJoke(joke = joke)
    }
}

@Composable
fun ShowJoke(joke: String) {
    if (joke.isNotBlank()) {
        Text(text = joke, modifier = Modifier.padding(top = 16.dp))
    }
}

@Composable
fun MainButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Click me")
    }
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChuckNorrisJokesTheme {
        StartActivity()
    }
}*/
