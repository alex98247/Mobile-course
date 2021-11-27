package com.itmo.mobile.di

import com.itmo.mobile.presenter.BookPresenter
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @Provides
    fun provideBookPresenter(): BookPresenter {
        return BookPresenter()
    }
}