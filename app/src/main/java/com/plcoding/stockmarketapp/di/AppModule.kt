package com.plcoding.stockmarketapp.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.plcoding.stockmarketapp.data.remote.StockApi
import com.plcoding.stockmarketapp.data.room_db.StockDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
/*
A converter which uses Moshi for JSON.
Because Moshi is so flexible in the types it supports, this converter assumes that it can handle all types. If you are mixing JSON serialization with something else (such as protocol buffers), you must add this instance last to allow the other converters a chance to see their types
 */
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockApi(): StockApi {
        return Retrofit.Builder()
            .baseUrl(StockApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideStockDatabase(app: Application): StockDb {
        return Room.databaseBuilder(
            app,
            StockDb::class.java,
            "stockdb.db"
        ).build()
    }
}