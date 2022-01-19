package com.asterisk.shoppingapp.ui.shoppinglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.asterisk.shoppingapp.R
import com.asterisk.shoppingapp.data.ShoppingRepository
import com.asterisk.shoppingapp.data.db.ShoppingDatabase
import com.asterisk.shoppingapp.data.entities.ShoppingItem
import com.asterisk.shoppingapp.ui.AddDialogListener
import com.asterisk.shoppingapp.ui.AddShoppingItemDialog
import com.asterisk.shoppingapp.ui.adapters.ShoppingAdapter
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity() {

//    override val kodein by kodein()
//    private val factory: ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory)[ShoppingViewModel::class.java]

        val adapter = ShoppingAdapter(listOf(), viewModel)

        rv_items.layoutManager = LinearLayoutManager(this)
        rv_items.adapter = adapter

        viewModel.getAllShoppingItems().observe(this) {
            adapter.items = it
            adapter.notifyDataSetChanged()
        }

        fab_add.setOnClickListener {
            AddShoppingItemDialog(this, object : AddDialogListener {
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }

    }
}