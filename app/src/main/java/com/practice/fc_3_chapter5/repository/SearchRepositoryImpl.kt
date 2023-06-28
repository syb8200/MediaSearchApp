package com.practice.fc_3_chapter5.repository

import com.practice.fc_3_chapter5.SearchService
import com.practice.fc_3_chapter5.model.ListItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchRepositoryImpl(private val searchService: SearchService) : SearchRepository {
    override fun search(query: String): Observable<List<ListItem>> {
        return searchService.searchImage(query).zipWith(searchService.searchVideo(query)) { imageResult, videoResult ->
                mutableListOf<ListItem>().apply {
                    // 두개의 리스트 하나로 합치기 : allAll()
                    addAll(imageResult.documents)
                    addAll(videoResult.documents)
                }.sortedBy { it.dateTime }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}