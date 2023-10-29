package com.levid.documentsapp.ui.document

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levid.documentsapp.data.remote.dtos.DocumentDto
import com.levid.documentsapp.data.repositories.DocumentsRepository
import com.levid.documentsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class DocumentsListState(
    val isLoading: Boolean = false,
    val documentsList: List<DocumentDto> = emptyList(),
    val error: String = ""
)
@HiltViewModel
class DocumentViewModel @Inject constructor(
    private val documentRepository: DocumentsRepository
): ViewModel() {
    private var _state = MutableStateFlow(DocumentsListState())
    val state: StateFlow<DocumentsListState> = _state.asStateFlow()
    init {
        documentRepository.getCoins().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = DocumentsListState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = DocumentsListState(documentsList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = DocumentsListState(error = result.message ?: "Error desconocido")
                }
            }
        }.launchIn(viewModelScope)
    }
}