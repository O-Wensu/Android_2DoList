package com.twoDoList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.twoDoList.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter
    init {
        instance = this
    }
    companion object {
        private var instance:MainActivity?=null
        fun getInstance(): MainActivity? {
            return instance
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        binding.addFloatingActionButton.setOnClickListener {
            add()
        }

        binding.allTodoCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "모든 Todo 보기를 클릭했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun initRecyclerView() {
        todoAdapter = TodoAdapter(mutableListOf())

        binding.todoRecyclerView.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        }
        Thread {
            val list = AppDatabase.getInstance(this)?.todoDao()?.getAll() ?: emptyList()
            todoAdapter.list.addAll(list)
            runOnUiThread { todoAdapter.notifyDataSetChanged() }
        }.start()
    }
/*
    private fun addUpdateTodo() {
        Thread {
            val todo = Todo("공부하기", "오늘")
            todoAdapter.list.add(0, todo)
            runOnUiThread { todoAdapter.notifyDataSetChanged() }
        }.start()
    }*/

    private fun add() {
        val text = ""
        val deadLine = "오늘"
        val todo = Todo(text, deadLine)

        Thread {
            AppDatabase.getInstance(this)?.todoDao()?.insert(todo)
            runOnUiThread{
                Toast.makeText(this, "데이터를 추가했습니다.", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    fun showMoreView() {
        Toast.makeText(this, "더보기를 클릭했습니다", Toast.LENGTH_SHORT).show()
    }

    fun delete() {
        Toast.makeText(this, "todo를 완료했습니다.", Toast.LENGTH_SHORT).show()
    }

    fun showAll() {
        Toast.makeText(this, "모두 보기를 클릭했습니다.", Toast.LENGTH_SHORT).show()
    }
}