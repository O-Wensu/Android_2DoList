package com.twoDoList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.twoDoList.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dummyList = mutableListOf(
            Todo("밥 먹기", "오늘"),
            Todo("밥 먹기", "오늘"),
            Todo("밥 먹기", "오늘")
        )

        todoAdapter = TodoAdapter(dummyList)
        binding.todoRecyclerView.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
/*            val dividerItemDecoration = DividerItemDecoration(applicationContext,LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)*/
        }
    }
}