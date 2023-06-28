package com.practice.fc_3_chapter5

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS) // 연결 타임아웃
        .readTimeout(5, TimeUnit.SECONDS) // 읽기 타임아웃
        .writeTimeout(5, TimeUnit.SECONDS) // 쓰기 타임아웃
        .build()

    private val gson = GsonBuilder()
        .setLenient() // 입력한 값이 잘못된 형식이어도 오류가 발생X
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dapi.kakao.com/v2/search/")
        .addConverterFactory(GsonConverterFactory.create(gson)) // 파싱 등록
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // RxJava 사용위해 필요
        .client(okHttpClient)
        .build()

    val searchService : SearchService by lazy { retrofit.create(SearchService::class.java) }
}