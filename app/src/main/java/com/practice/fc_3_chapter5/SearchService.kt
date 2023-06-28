package com.practice.fc_3_chapter5

import com.practice.fc_3_chapter5.model.ImageListResponse
import com.practice.fc_3_chapter5.model.VideoListResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchService {
    @Headers("Authorization: KakaoAK aea7af9a20673b711c5491c83da0de93")
    @GET("image")
    fun searchImage(
        @Query("query") query: String
    ): Observable<ImageListResponse>

    @Headers("Authorization: KakaoAK aea7af9a20673b711c5491c83da0de93")
    @GET("vclip")
    fun searchVideo(
        @Query("query") query: String
    ): Observable<VideoListResponse>
}