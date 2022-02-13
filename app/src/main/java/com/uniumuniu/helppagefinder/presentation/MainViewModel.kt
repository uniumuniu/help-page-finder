package com.uniumuniu.helppagefinder.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uniumuniu.helppagefinder.core.Resource
import com.uniumuniu.helppagefinder.domain.use_case.FindHelpArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val findHelpArticlesUseCase: FindHelpArticlesUseCase
) : ViewModel() {

    private val tag: String = "MainViewModel"

    private val _searchQuery: MutableState<String> = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state: MutableState<FindArticlesState> = mutableStateOf(FindArticlesState())
    val state: State<FindArticlesState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    private fun findArticles(query: String) = findHelpArticlesUseCase(query).onEach { result ->
        when (result) {
            is Resource.Loading -> {
                Log.d(tag, "Fetching help articles from website...")
            }
            is Resource.Success -> {
                result.data?.let {
                    _state.value = FindArticlesState(it)
                }
            }
            is Resource.Error -> {
                result.message?.let {
                    _state.value = FindArticlesState(error = it)
                }

                _eventFlow.emit(UIEvent.ShowSnackbar(result.message ?: "Unknown error"))
            }
        }
    }.launchIn(viewModelScope)

    fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            findArticles(query = query)
        }
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String): UIEvent()
    }
}