package com.challange.hilt.ui.di

import com.challange.hilt.ui.main.MainRepository
import com.challange.hilt.ui.main.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}

@Module
@InstallIn(ApplicationComponent::class)
object TasksRepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(): MainRepository {
        return MainRepositoryImpl()
    }
}
