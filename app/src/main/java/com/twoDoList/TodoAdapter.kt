package com.twoDoList

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.twoDoList.databinding.ItemTodoBinding

class TodoAdapter(var list: MutableList<Todo>) : RecyclerView.Adapter<TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = ItemTodoBinding.inflate(inflater, parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            todoEditText.setText(list[position].title)
            val todo = list[position]
            holder.setData(todo)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
    private val mainActivity = MainActivity.getInstance()
    var mTodo: Todo? = null

    init {
        binding.moreImageButton.setOnClickListener {
            mainActivity?.showMoreView()
        }

        binding.completeCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.completeCheckBox.isChecked = false
                mainActivity?.delete(mTodo!!)
            }
        }
        binding.todoEditText.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {
                mainActivity?.edit(mTodo!!, binding.todoEditText.text.toString())
            }
            true
        }
    }

    fun setData(todo: Todo) {
        this.mTodo = todo
    }

}
