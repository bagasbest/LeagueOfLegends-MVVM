package com.ad_coding.mvvmcourse.data.di

import android.content.Context
import com.ad_coding.mvvmcourse.data.repository.ApiRepositoryImpl
import com.ad_coding.mvvmcourse.domain.repository.ApiRepository
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // 1. Provide the ChuckerInterceptor
    @Provides
    @Singleton
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor =
        ChuckerInterceptor.Builder(context).build()

    // 2. Update HttpClient provider to use the interceptor
    @Provides
    @Singleton
    fun provideHttpClient(chuckerInterceptor: ChuckerInterceptor): HttpClient =
        HttpClient(OkHttp) { // Use the OkHttp engine factory
            engine {
                // Add the Chucker interceptor to the underlying OkHttp client
                addInterceptor(chuckerInterceptor)
            }

            defaultRequest {
                url {
                    protocol = io.ktor.http.URLProtocol.HTTPS
                    host = "ddragon.leagueoflegends.com"
                    path("cdn/15.18.1/data/en_US/")
                }
                header(HttpHeaders.ContentType, "application/json")
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }

    @Provides
    @Singleton
    fun provideApiRepository(httpClient: HttpClient): ApiRepository =
        ApiRepositoryImpl(httpClient = httpClient)
}