package com.example.spbustracker.ui.vehicles

import androidx.appcompat.widget.SearchView

class SearchVehiclesManager(
    private val searchView: SearchView,
    private val onSearchSubmit: (String) -> Unit
) {

    fun setupSearchView() {
        searchView.queryHint = "Ex.: 8200-10"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    onSearchSubmit(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
}
