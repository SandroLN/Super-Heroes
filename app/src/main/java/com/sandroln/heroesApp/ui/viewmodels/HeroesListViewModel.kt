package com.sandroln.heroesApp.ui.viewmodels

import androidx.lifecycle.*
import com.sandroln.heroesApp.common.Resource
import com.sandroln.heroesApp.domain.usecase.GetHeroesUseCase
import com.sandroln.heroesApp.domain.usecase.LoadDataUseCase
import com.sandroln.heroesApp.ui.HeroesState
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeroesListViewModel @Inject constructor(
    private val getHeroesUseCase: GetHeroesUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<HeroesState>(HeroesState())
    val uiState: LiveData<HeroesState> = Transformations.map(_uiState) { state ->
        val newHeroes = state.heroes.filter {
            it.name.contains(state.query.toString(),true)
        }
        state.copy(heroes = newHeroes)
    }


    fun getHeroesList() {
        viewModelScope.launch {
            getHeroesUseCase.invoke().collect() { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { heroes ->
                            _uiState.postValue(HeroesState(isLoading = false, heroes = heroes))
                        }
                    }
                    is Resource.Loading -> {
                        result.isLoading.let { loading ->
                            _uiState.value = (HeroesState(isLoading = loading))
                        }

                    }
                    is Resource.Error -> {
                        result.message?.let { message ->
                            _uiState.postValue(HeroesState(message = message))
                        }
                    }
                }

            }
        }
    }

    fun updateListOnQuery(query: String?) {
        _uiState.postValue(_uiState.value?.copy(query = query))
    }

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }
}