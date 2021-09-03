package com.frontic.seriesapp.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Singleton API Rest Client configuration.
 *
 * @author Christopher Paulino
 */
object ApiClient {

    private const val BASE_URL = "https://api.tvmaze.com/"

    /**
     * This function set the http client.
     *
     * @return ApiService interface with all the web services ready to be executed.
     */
    fun create(): ApiService {

        val gson = GsonBuilder().setLenient().create()

        //this Interceptor allows to log information in the logcat.
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
            level = HttpLoggingInterceptor.Level.BODY
        }

        //Http Client that contains relevant configuration to be applied on requests.
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(ApiService::class.java)
    }
}