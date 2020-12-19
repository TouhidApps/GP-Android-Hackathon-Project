package com.touhidapps.hackathonproject.networkService

import android.util.Log
import com.google.gson.GsonBuilder
import com.touhidapps.hackathonproject.utils.App
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val TAG = "RetrofitClient"

    private var retrofit: Retrofit? = null
    private var retrofitCache: Retrofit? = null

    private const val CACHE_SIZE: Long = 5 * 1024 * 1024 // 5MB cache size
    private const val TIME_OUT: Long = 120
    private const val HEADER_CACHE_CONTROL = "Cache-Control"
    private const val HEADER_PRAGMA = "Pragma"

    private val gson = GsonBuilder().setLenient().create()

    fun instance(): RetrofitInterface? {
        if (retrofit == null) {

            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // verifies we are using RxJava2 for this API call
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(MyApiUrl.BASE_URL)
                .client(okHttpClient)
                .build()

        }
        return retrofit?.create(RetrofitInterface::class.java)
    } // instance

    fun instanceCache(): RetrofitInterface? {
        if (retrofitCache == null) {

            val okHttpClient = OkHttpClient.Builder()
                .cache(cache())
                .addInterceptor(httpLoggingInterceptor()) // used if network off OR on
                .addNetworkInterceptor(networkInterceptor()) // only used when network is on
                .addInterceptor(offlineInterceptor())
                .retryOnConnectionFailure(false)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build()

            retrofitCache = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // verifies we are using RxJava2 for this API call
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(MyApiUrl.BASE_URL)
                .client(okHttpClient)
                .build()

        }
        return retrofitCache?.create(RetrofitInterface::class.java)
    } // instanceCache


    private fun cache(): Cache {
        return Cache(
            File(App.app.cacheDir, "retrofitCache"),
            CACHE_SIZE
        )
    }

    /**
     * This interceptor will be called both if the network is available and if the network is not available
     *
     * @return Interceptor
     */
    private fun offlineInterceptor(): Interceptor {
        return Interceptor { chain ->
            Log.d(TAG, "offline interceptor: called.")
            var request: Request = chain.request()

            // prevent caching when network is on. For that we use the "networkInterceptor"
            if (!App.isNetworkAvailable()) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()
                request = request.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
    }


    /**
     * This interceptor will be called ONLY if the network is available
     *
     * @return Interceptor
     */
    private fun networkInterceptor(): Interceptor {
        return Interceptor { chain ->
            Log.d(TAG, "network interceptor: called.")
            val response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(5, TimeUnit.SECONDS)
                .build()
            response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {

        val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d(TAG, "httpLoggingInterceptor: $message")
            }
        })
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return httpLoggingInterceptor

    }

}