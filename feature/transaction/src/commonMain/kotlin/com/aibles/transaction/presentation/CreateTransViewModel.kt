package com.aibles.transaction.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent


class CreateTransViewModel: ScreenModel, KoinComponent {
    private val _createTransUiState = MutableStateFlow(CreateTransUiState())
    val createTransUiState: StateFlow<CreateTransUiState> = _createTransUiState.asStateFlow()

    fun onDateChange(text: String){
        _createTransUiState.value = createTransUiState.value.copy(
            date = text
        )
    }

    fun onAmountChange(text: String){
        _createTransUiState.value = createTransUiState.value.copy(
            amount = text
        )
    }

    fun onCategoryChange(text: String){
        _createTransUiState.value = createTransUiState.value.copy(
            category = text
        )
    }

    fun onAccountChange(text: String){
        _createTransUiState.value = createTransUiState.value.copy(
            account = text
        )
    }

    fun onNoteChange(text: String){
        _createTransUiState.value = createTransUiState.value.copy(
            note = text
        )
    }
}