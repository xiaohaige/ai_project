package com.empedocles.travelapp.di

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.empedocles.travelapp.data.local.TripDatabase
import com.empedocles.travelapp.data.mock.MockTravelApiService
import com.empedocles.travelapp.data.remote.LoggingInterceptor
import com.empedocles.travelapp.data.remote.TravelApiService
import com.empedocles.travelapp.data.repository.AllTravelItemRepositoryImpl
import com.empedocles.travelapp.data.repository.BookMarkRepositoryImpl
import com.empedocles.travelapp.data.repository.SearchRepositoryImpl
import com.empedocles.travelapp.data.repository.SingleTravelItemRepositoryImpl
import com.empedocles.travelapp.domain.repository.AllTravelItemRepository
import com.empedocles.travelapp.domain.repository.BookMarkRepository
import com.empedocles.travelapp.domain.repository.SearchRepository
import com.empedocles.travelapp.domain.repository.SingleTravelItemRepository
import com.empedocles.travelapp.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private var isDebugMode = true // 可动态切换的调试模式开关

    @Singleton
    @Provides
    fun provideGsonConvertor(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideTripDatabase(app: Application): TripDatabase {
        return Room.databaseBuilder(
            app,
            TripDatabase::class.java,
            "trip.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: LoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(@ApplicationContext context: Context): LoggingInterceptor {
        return LoggingInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideService(
        retrofit: Retrofit,
        @ApplicationContext context: Context
    ): TravelApiService {
        Log.d("AppModule", "提供 TravelApiService，当前调试模式: $isDebugMode")
        return if (isDebugMode) {
            MockTravelApiService(context).apply {
                Log.d("AppModule", "使用 MockTravelApiService 进行调试")
            }
        } else {
            return retrofit.create(TravelApiService::class.java).apply {
                Log.d("AppModule", "使用真实 API 服务")
            }
        }
    }


    @Provides
    @Singleton
    fun provideAllTravelItemRepository(service: TravelApiService): AllTravelItemRepository {
        return AllTravelItemRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideSingleTravelItemRepository(service: TravelApiService): SingleTravelItemRepository {
        return SingleTravelItemRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideBookMarkRepository(service: TravelApiService): BookMarkRepository {
        return BookMarkRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(service: TravelApiService) : SearchRepository{
        return SearchRepositoryImpl(service)
    }
}