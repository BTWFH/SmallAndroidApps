package com.example.dessertclicker.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.dessertclicker.R
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.data.DessertUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    /**
     * Update the revenue and desserts sold and then advance
     * the UI element to the next dessert
     *
     * Essentially this is the function of the DessertViewModel class
     * that my UI will call to go to the next desert after the user
     * clicks on a dessert
     */
    fun updateDessert() {
        _uiState.update { currentState ->
            currentState.copy(
                revenue = currentState.revenue + currentState.currentDessertPrice,
                dessertsSold = currentState.dessertsSold.inc(),
            )
        }
        if (uiState.value.currentDessertIndex != dessertList.size - 1) {
            if (uiState.value.dessertsSold >= dessertList[uiState.value.currentDessertIndex].startProductionAmount) {
                _uiState.update { currentState ->
                    currentState.copy(
                        currentDessertIndex = currentState.currentDessertIndex.inc(),
                        currentDessertPrice = dessertList[currentState.currentDessertIndex].price,
                        currentDessertImageId = dessertList[currentState.currentDessertIndex].imageId
                    )
                }
            }
        }
    }

    /**
     * Share desserts sold information using ACTION_SEND intent
     */
    fun shareSoldDessertsInformation(intentContext: Context, dessertsSold: Int, revenue: Int) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                intentContext.getString(R.string.share_text, dessertsSold, revenue)
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)

        try {
            ContextCompat.startActivity(intentContext, shareIntent, null)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                intentContext,
                intentContext.getString(R.string.sharing_not_available),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}