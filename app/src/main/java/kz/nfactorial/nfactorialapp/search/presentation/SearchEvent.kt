package kz.nfactorial.nfactorialapp.search.presentation

sealed interface SearchEvent {

    data class OnSearchChanged(val query: String) : SearchEvent
}