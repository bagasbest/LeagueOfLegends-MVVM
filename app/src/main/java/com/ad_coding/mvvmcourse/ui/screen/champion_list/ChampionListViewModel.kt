package com.ad_coding.mvvmcourse.ui.screen.champion_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ad_coding.mvvmcourse.domain.model.ChampionListState
import com.ad_coding.mvvmcourse.domain.model.toChampionList
import com.ad_coding.mvvmcourse.domain.repository.ApiRepository
import com.skydoves.sandwich.ktor.statusCode
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChampionListViewModel @Inject constructor(
    private val apiRepository: ApiRepository
): ViewModel() {

    private val _state = MutableStateFlow(ChampionListState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            apiRepository.getAllChampion()
                .onSuccess {
                    _state.update {
                        Log.e("ViewModelSuccess", "Data received: ${data.champion.size} champions")
                        it.copy(
                            champions = data.champion.toChampionList()
                        )
                    }
                }
                .onError {
                    Log.e("ViewModelError", "API call failed. Response: $this")
                }
        }
    }

    fun onSearchTextChange(text: String) {
        _state.update {
            it.copy(
                searchText = text,
                filterChampions = it.champions.filter { champion ->
                    champion.name.contains(text, ignoreCase = true)
                }
            )
        }
    }
}