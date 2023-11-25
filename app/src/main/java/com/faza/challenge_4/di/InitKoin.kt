package com.faza.challenge_4.di

import android.app.Application

class InitKoin: Application() {
    override fun onCreate() {
        super.onCreate()
        Koin.initKoin(this)
    }
}