package com.asterisk.shoppingapp.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asterisk.shoppingapp.data.ShoppingRepository
import com.asterisk.shoppingapp.data.entities.ShoppingItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel() {

    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }


    fun delete(item: ShoppingItem) = viewModelScope.launch {
        repository.delete(item)
    }


    fun getAllShoppingItems() = repository.getAllShoppingItems()
}