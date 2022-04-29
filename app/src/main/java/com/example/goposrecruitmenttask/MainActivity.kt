package com.example.goposrecruitmenttask

import ItemAdapter
import android.os.Bundle
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.goposrecruitmenttask.databinding.ActivityMainBinding
import database.ItemEntity
import database.ObjectBox


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        ObjectBox.init(this)

        viewModel.start()
        viewModel.fetchDataFromDB()

        val itemList: ArrayList<ItemEntity> = ArrayList()
        val adapter = ItemAdapter(this, itemList)

        val listView: ListView = binding.itemList

        viewModel.populateView(adapter)
        listView.adapter = adapter
        setContentView(listView)
    }
}
