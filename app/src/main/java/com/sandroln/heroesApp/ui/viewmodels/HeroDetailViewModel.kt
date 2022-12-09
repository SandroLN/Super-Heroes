package com.sandroln.heroesApp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandroln.heroesApp.common.Resource
import com.sandroln.heroesApp.domain.usecase.GetHeroUseCase
import com.sandroln.heroesApp.ui.Action
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeroDetailViewModel @Inject constructor(
    private val getHeroUseCase: GetHeroUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<Action>()
    val uiState: LiveData<Action> = _uiState

    fun getHeroById(id: Long) {
        viewModelScope.launch {
            getHeroUseCase.invoke(id).collect() { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { hero ->
                            _uiState.postValue(Action.Success(item = hero))
                        }
                    }
                    is Resource.Loading -> {
                        result.isLoading.let { loading ->
                            _uiState.postValue(Action.Loading(loading = loading))
                        }
                    }
                    is Resource.Error -> {
                        result.message?.let { message ->
                            _uiState.postValue(Action.Error(message = message))
                        }
                    }
                }
            }
        }
    }
}