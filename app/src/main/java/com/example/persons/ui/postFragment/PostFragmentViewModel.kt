package com.example.persons.ui.postFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.persons.base.BaseViewModel
import com.example.persons.common.enums.Status
import com.example.persons.model.PostsItemResponse
import com.example.persons.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostFragmentViewModel @Inject constructor(private val repository: UserRepository): BaseViewModel() {

    private  val _post = MutableLiveData<PostsItemResponse>()
    val post : LiveData<PostsItemResponse> = _post

    private var _statusData = MutableLiveData<Status>()
    val statusData: LiveData<Status> = _statusData


    fun getPost(id: Int) {

        GlobalScope.launch {
            repository.getPosts(id)
                .catch {
                    _statusData.postValue(Status.ERROR)
                }
                .collect {
                    when (it.status) {
                        Status.LOADING -> {
                            _statusData.postValue(Status.LOADING)
                        }
                        Status.SUCCESS -> {
                            _post.postValue(it.data!!.body()!!)
                            _statusData.postValue(Status.SUCCESS)
                        }
                        Status.ERROR -> {
                            _statusData.postValue(Status.ERROR)
                        }
                    }
                }
        }
    }
}