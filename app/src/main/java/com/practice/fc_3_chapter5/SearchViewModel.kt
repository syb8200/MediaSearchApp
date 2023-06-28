package com.practice.fc_3_chapter5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practice.fc_3_chapter5.model.ImageItem
import com.practice.fc_3_chapter5.model.ListItem
import com.practice.fc_3_chapter5.model.VideoItem
import com.practice.fc_3_chapter5.repository.SearchRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private val _listLiveData = MutableLiveData<List<ListItem>>()
    val listLiveData : LiveData<List<ListItem>> get() = _listLiveData

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading : LiveData<Boolean> get() = _showLoading

    // 다수의 Observable을 한 번에 폐기하고 싶을 때 CompositeDisposable 사용
    private var disposable : CompositeDisposable? = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
        disposable = null
    }

    fun search(query : String) {
        disposable?.add(searchRepository.search(query)
            .doOnSubscribe { _showLoading.value = true }
            .doOnTerminate { _showLoading.value = false}
            .subscribe({list ->
                _listLiveData.value = list  // 성공
            }, {
                _listLiveData.value = emptyList()  // 실패
            })
        )
    }

    fun toggleFavorite(item: ListItem) {
        // SearchFragment 안에서도 클릭을 했을 때 하트의 상태가 변하기 때문에 livedata를 업데이트 시켜줘야 한다.
        _listLiveData.value = _listLiveData.value?.map {
            if (it == item) {
                when(it) {
                    is ImageItem -> {
                        it.copy(isFavorite = !item.isFavorite)
                    }
                    is VideoItem -> {
                        it.copy(isFavorite = !item.isFavorite)
                    }
                    else -> {
                        it
                    }
                }.also { changeItem ->
                    if (Common.favoritesList.contains(item)) {
                        Common.favoritesList.remove(item)
                    } else {
                        Common.favoritesList.add(changeItem)
                    }
                }
            } else {
                it
            }
        }
    }

    // ViewModelFactory는 ViewModel을 통해 전달되는 인자가 있을 때 사용된다.
    // ViewModelProvider.Factory를 확장 -> create 메소드 override
    class SearchViewModelFactory(private val searchRepository: SearchRepository) : ViewModelProvider.Factory {
        override fun <T: ViewModel> create(modelClass: Class<T>): T {
            return SearchViewModel(searchRepository) as T
        }
    }
}