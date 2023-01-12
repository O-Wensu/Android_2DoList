package com.twoDoList

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.twoDoList.databinding.ItemTodoBinding
import java.util.*


class TodoAdapter(var list: MutableList<Todo>) : RecyclerView.Adapter<TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = ItemTodoBinding.inflate(inflater, parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = list[position]
            holder.setData(todo)
/*            todoEditText.setText(todo.title)
            //println("title: ${todo.title}, position: $position")
            todoEditText.addTextChangedListener(TodoTextWatcher(position))*/
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
    }

    fun setData(todo: Todo) {
        this.mTodo = todo
    }
}