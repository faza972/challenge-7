package com.faza.challenge_4.di

import android.content.Context
import com.faza.challenge_4.repository.AppDatabase
import com.faza.challenge_4.repository.CartRepository
import com.faza.challenge_4.viewModel.CartViewModel
import com.faza.challenge_4.viewModel.DetailViewModel
import com.faza.challenge_4.viewModel.LoginViewModel
import com.faza.challenge_4.viewModel.ProfileViewModel
import com.faza.challenge_4.viewModel.registerViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

object Koin {
    private val viewModelModule = module {
        viewModel { CartViewModel(get()) }
        viewModel { DetailViewModel(get()) }
        viewModel { LoginViewModel(get()) }
        viewModel { ProfileViewModel(get()) }
        viewModel { registerViewModel(get()) }
    }
    private val repositoryModule = module {
        single { CartRepository(get()) }
    }
    private val localModule = module {
        single { AppDatabase.getInstance(androidContext()) }
        single { get<AppDatabase>().cartDao }
    }
    val moduleList: List<Module> = listOf(
        viewModelModule,
        repositoryModule,
        localModule
    )
    fun initKoin(context: Context) {
        startKoin{
            androidContext(context)
            modules(moduleList)
        }
    }
}