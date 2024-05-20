package com.engie.eea_tech_interview.di

import android.content.Context
import com.engie.data.api.MovieApiService
import com.engie.data.genre.GenreMapper
import com.engie.data.genre.GenreRepositoryImpl
import com.engie.data.movie.MovieMapper
import com.engie.data.movie.MovieRepositoryImpl
import com.engie.data.util.CoroutineDispatcherProvider
import com.engie.data.util.DefaultCoroutineDispatcherProvider
import com.engie.data.util.RequestLogInterceptor
import com.engie.domain.genre.GenreRepository
import com.engie.domain.movie.MovieRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import org.koin.android.ext.koin.androidContext


val networkModule = module {

    // Retrofit
    single<MovieApiService> {
        Retrofit.Builder()
            .baseUrl(get<String>(named("base_url")))
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(getOkHttpClient(androidContext()))
            .build()
            .create(MovieApiService::class.java)
    }

    // Moshi
    factory {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}

val repositoryModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(
            api = get(),
            dispatcher = get(),
            mapper = get()
        )
    }

    // Genre
    single<GenreRepository> {
        GenreRepositoryImpl(
            dispatcher = get(),
            api = get(),
            mapper = get()
        )
    }


}

val commonDataModule = module {

    // Coroutines
    single<CoroutineDispatcherProvider> { DefaultCoroutineDispatcherProvider() }

    // Mappers
    factory { MovieMapper() }
    factory { GenreMapper() }

}

val dataModule = arrayListOf(networkModule, repositoryModule, commonDataModule)

fun getOkHttpClient(context: Context): OkHttpClient =
        provideHttpClient(context)


const val READ_TIME_OUT: Long = 30
const val WRITE_TIME_OUT: Long = 10
const val CONNECT_TIME_OUT: Long = 10
const val KEEP_ALIVE_DURATION = 5L
const val CACHE_SIZE: Long = 10 * 1024 * 1024 // 10MB
fun provideHttpClient(context: Context): OkHttpClient {

    val protocols = ArrayList<Protocol>()
    protocols.add(Protocol.HTTP_1_1)

    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    val builder = OkHttpClient().newBuilder()
        .connectionPool(ConnectionPool(0, KEEP_ALIVE_DURATION, TimeUnit.MINUTES))
        .protocols(protocols)
        .addInterceptor(RequestLogInterceptor())
        .addInterceptor(interceptor)
        .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        .cache(Cache(context.cacheDir, CACHE_SIZE))

    return builder.build()
}
