package com.asterisk.shoppingapp.ui

import com.asterisk.shoppingapp.data.entities.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClicked(item: ShoppingItem)
}