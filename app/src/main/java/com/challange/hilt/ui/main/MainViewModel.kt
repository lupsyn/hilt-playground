package com.challange.hilt.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challange.hilt.ui.models.Challenge
import com.challange.hilt.ui.models.Result.Success
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val liveData = MutableLiveData<List<Challenge>>()

    val uiData: LiveData<List<Challenge>>
        get() {
            return liveData
        }

    init {
        viewModelScope.launch {
            repository.getChallenge().let { result ->
                if (result is Success<List<Challenge>>) {
                    liveData.value = result.data
                } else {
                    Timber.e("error handling not implemented yet")
                }
            }

        }
    }
}