package com.melvin.compass.main.di

import android.content.Context
import com.melvin.compass.main.data.CompassCacheImpl
import com.melvin.compass.main.data.CompassRepositoryImpl
import com.melvin.compass.main.data.CompassService
import com.melvin.compass.main.domain.CompassCache
import com.melvin.compass.main.domain.CompassRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class CompassModule {

    @Provides
    fun providesCompassService(retrofit: Retrofit): CompassService =
        retrofit.create(CompassService::class.java)

    @Provides
    fun providesCompassRepository(@ApplicationContext context: Context): CompassCache =
        CompassCacheImpl(context)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class CompassBindsModule {

    @Binds
    abstract fun bindCompassRepository(repository: CompassRepositoryImpl): CompassRepository
}