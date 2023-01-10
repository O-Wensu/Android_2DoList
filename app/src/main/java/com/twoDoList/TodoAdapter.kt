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


class TodoAdapter(var list: MutableList<Todo>): RecyclerView.Adapter<TodoViewHolder>() {

/*    inner class TodoTextWatcher(var position: Int) : TextWatcher {

        override fun afterTextChanged(p0: Editable?) {
            //list.set(position, )
            println("p0: $p0 | position: $position | title: ${list[position].title}")
            //notifyDataSetChanged()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }
    }*/

    fun removeItem(position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = ItemTodoBinding.inflate(inflater,parent,false)
/*        binding.moreImageButton.setOnClickListener{
            Toast.makeText(this, "클릭", Toast.LENGTH_SHORT).show()}*/
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = list[position]

/*            todoEditText.setText(todo.title)
            //println("title: ${todo.title}, position: $position")
            todoEditText.addTextChangedListener(TodoTextWatcher(position))*/
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class TodoViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root) {
    private val mainActivity = MainActivity.getInstance()
    init {
        binding.moreImageButton.setOnClickListener {
            mainActivity?.showMoreView()
        }

        binding.completeCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                mainActivity?.delete()
            }
        }
    }
}