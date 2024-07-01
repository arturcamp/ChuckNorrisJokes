package com.example.chucknorrisjokes

import android.app.Application
import com.example.chucknorrisjokes.data.repository.Repository
import com.example.chucknorrisjokes.ui.viewModel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModule = module {
            //single { Repository() }
            viewModel { MainViewModel() }
        }

        startKoin {
            //androidLogger() // Use Android Logger
            androidContext(this@AppApplication) // Provide Android context
            modules(appModule) // Load your Koin modules
        }
    }
}
