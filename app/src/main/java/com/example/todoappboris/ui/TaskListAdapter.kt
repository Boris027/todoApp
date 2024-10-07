package com.example.todoappboris.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappboris.data.InMemoryTaskRepository
import com.example.todoappboris.data.Task
import com.example.todoappboris.databinding.TaskviewBinding

class TaskListAdapter private constructor():ListAdapter<Task,TaskListAdapter.TaskviewHolder>(TaskDiffCallback) {



    companion object{
        private var instance: TaskListAdapter?=null
        fun getInstance(): TaskListAdapter {
            if(instance==null){
                instance= TaskListAdapter()
            }
            return instance!!
        }
    }

    class TaskviewHolder(private val binding:TaskviewBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(task: Task){
            binding.title.text = task.title
            binding.body.text = task.body
            binding.swich.isChecked = task.completed

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskviewHolder {
        val binding:TaskviewBinding = TaskviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskviewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskviewHolder, position: Int) {
        holder.bind(getItem(position))

    }



    object TaskDiffCallback: DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem.body == newItem.body &&
                oldItem.title == newItem.title &&
                oldItem.completed == newItem.completed

    }


}