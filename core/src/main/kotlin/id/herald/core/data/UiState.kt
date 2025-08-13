package id.herald.core.data

/** Created by Herald Santos on 04/12/2024. */

sealed class UiState<out T : Any?> {

    object Loading : UiState<Nothing>()

    data class Success<out T : Any>(val data: T) : UiState<T>()

    data class Error(val errorMessage: String) : UiState<Nothing>()
}