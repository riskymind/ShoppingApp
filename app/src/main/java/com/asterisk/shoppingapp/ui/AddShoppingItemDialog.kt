package com.asterisk.shoppingapp.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.asterisk.shoppingapp.R
import com.asterisk.shoppingapp.data.entities.ShoppingItem
import kotlinx.android.synthetic.main.shopping_dialog.*

class AddShoppingItemDialog(context: Context, private val addDialogListener: AddDialogListener) :
    AppCompatDialog(context) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopping_dialog)

        tv_add.setOnClickListener {
            val name = et_name.text.toString()
            val amount = et_amount.text.toString()

            if (name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Provide all  information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        tv_cancel.setOnClickListener { cancel() }
    }
}