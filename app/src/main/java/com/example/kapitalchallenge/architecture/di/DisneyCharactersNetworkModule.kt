package com.example.kapitalchallenge.architecture.di

import com.example.kapitalchallenge.BuildConfig
import com.example.kapitalchallenge.architecture.data.local.DisneyCharacterDao
import com.example.kapitalchallenge.architecture.data.net.DisneyCharactersApiServices
import com.example.kapitalchallenge.architecture.data.repository.DisneyCharactersRepositoryImpl
import com.example.kapitalchallenge.architecture.domain.DisneyCharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DisneyCharactersNetworkModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideRepository(
        apiService: DisneyCharactersApiServices,
        dao: DisneyCharacterDao
    ): DisneyCharactersRepository {
        return DisneyCharactersRepositoryImpl(apiService, dao)
    }

    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ): DisneyCharactersApiServices {
        return retrofit.create(DisneyCharactersApiServices::class.java)
    }
}