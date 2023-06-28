package com.practice.fc_3_chapter5

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.practice.fc_3_chapter5.model.ImageItem
import com.practice.fc_3_chapter5.model.ListItem
import com.practice.fc_3_chapter5.model.VideoItem
import com.practice.fc_3_chapter5.repository.SearchRepository
import io.reactivex.rxjava3.core.Observable

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var searchRepository: SearchRepository // mock data 설정 (임의의 데이터 넣어서 확인)

    @Mock
    lateinit var mockLoadingObserve : Observer<Boolean>

    @Mock
    lateinit var mockListObserver: Observer<List<ListItem>>

    private lateinit var viewModel: SearchViewModel // 실질적으로 viewModel을 테스트하는 것이기 때문에 mock data로 선언하면 안된다.

    // 테스트가 발생하기 전
    @Before
    fun setUp() {
        viewModel = SearchViewModel(searchRepository)
        viewModel.showLoading.observeForever(mockLoadingObserve)
        viewModel.listLiveData.observeForever(mockListObserver)
    }

    // 실제 테스트 되는 코드
    @Test
    fun searchNotEmpty() {
        Mockito.`when`(searchRepository.search(Mockito.anyString())).thenReturn(Observable.just(mockList()))
        viewModel.search("query")

        Mockito.verify(mockLoadingObserve, Mockito.times(1)).onChanged(true)
        Mockito.verify(mockListObserver, Mockito.times(1)).onChanged(Mockito.anyList())
        assertTrue(!viewModel.listLiveData.value.isNullOrEmpty())
    }

    @Test
    fun searchEmpty() {
        Mockito.`when`(searchRepository.search(Mockito.anyString())).thenReturn(Observable.just(
            emptyList()
        ))
        viewModel.search("qeury")

        Mockito.verify(mockLoadingObserve, Mockito.times(1)).onChanged(true)
        Mockito.verify(mockListObserver, Mockito.times(1)).onChanged(Mockito.anyList())
        assertTrue(viewModel.listLiveData.value.isNullOrEmpty())
    }

    private fun mockList() = listOf(
        ImageItem("thumbnailUrl", "collection", "siteName", "docUrl", Date(), false),
        ImageItem("thumbnailUrl1", "collection1", "siteName1", "docUrl", Date(), true),
        VideoItem("thumbnailUrl1", "title", 3, "author", Date(), false)
    )
}