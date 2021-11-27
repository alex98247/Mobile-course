package com.itmo.mobile.di

import com.itmo.mobile.Task3Activity
import dagger.Component

@Component(modules = [StorageModule::class])
interface AppComponent {
    fun inject(activity: Task3Activity)
}