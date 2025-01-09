package com.phuhm.basemodule.adpater

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.phuhm.basemodule.data.model.Todo
import com.phuhm.basemodule.databinding.ItemTodoBinding

class TodoAdapter : ListAdapter<Todo, TodoAdapter.TodoViewHolder>(TodoDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = getItem(position)
        holder.onBind(todo)
    }

    inner class TodoViewHolder(private val binding: ItemTodoBinding) : ViewHolder(binding.root) {
        fun onBind(todo: Todo) {
            binding.tvTitle.isSelected = true
            binding.tvTitle.text = todo.title
            binding.ivStateCompleted.isSelected = todo.completed
        }
    }

    class TodoDiffCallback : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }
}