package com.ad_coding.mvvmcourse.ui.screen.champion_detail

import android.util.Log
import androidx.core.graphics.values
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ad_coding.mvvmcourse.domain.model.ChampionModel
import com.ad_coding.mvvmcourse.domain.repository.ApiRepository
import com.ad_coding.mvvmcourse.ui.util.ChampionDetails
import com.skydoves.sandwich.ktor.statusCode
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChampionDetailsViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _champion = MutableStateFlow<ChampionModel?>(null)
    val champion = _champion.asStateFlow()

    init {
        val args = savedStateHandle.toRoute<ChampionDetails>()
        getChampionByName(args.name)
    }

    private fun getChampionByName(name: String) {
        viewModelScope.launch {
            apiRepository.getChampionByName(name)
                .onSuccess {
                    // --- SUCCESS LOG ---
                    val championData = this.data.data.values.firstOrNull()
                    _champion.value = championData
                    Log.e("ChampionDetailsVM", "Successfully fetched champion: ${championData?.name}")
                }
                .onError {
                    // --- SERVER ERROR LOG ---
                    // This block executes for HTTP error codes (e.g., 404, 500)
                    Log.e("ChampionDetailsVM", "API Error: [${this.statusCode}] ${this.message()}")
                    // You could also set an error state for the UI here
                }
                .onException {
                    // --- NETWORK EXCEPTION LOG ---
                    // This block executes for network exceptions (e.g., no internet, timeout)
                    Log.e("ChampionDetailsVM", "API Exception: ${this.throwable.message}")
                    // You could also set an error state for the UI here
                }
        }
    }
}