package com.example.goposrecruitmenttask

import ItemAdapter
import android.os.Bundle
import android.os.StrictMode
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.goposrecruitmenttask.databinding.ActivityMainBinding
import database.ItemEntity
import database.ObjectBox
import di.DaggerMainActivityComponent
import di.MainActivityModule

import di.MainActivityComponent

class MainActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val mainActivityComponent: MainActivityComponent = DaggerMainActivityComponent.builder()
            .mainActivityModule(MainActivityModule(this@MainActivity)).build()

        ObjectBox.init(mainActivityComponent.context)
        viewModel.startAll()

        val itemList: ArrayList<ItemEntity> = ArrayList()
        val adapter = ItemAdapter(mainActivityComponent.context, itemList)

        val listView: ListView = binding.itemList

        viewModel.populateView(adapter)
        listView.adapter = adapter
        setContentView(listView)
    }
}
