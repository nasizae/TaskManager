package com.example.taskmanager.ui.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmeneger.databinding.ItemTaskBinding
import com.example.taskmanager.model.Task

class TaskAdapter(val onLongClick: (position: Int) -> Boolean) :
    RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    private val taskList = ArrayList<Task>()

    fun addTasks(tasks: List<Task>) {
        taskList.clear()
        taskList.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        return TaskHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TaskHolder(private var binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc

            itemView.setOnLongClickListener {
                onLongClick(adapterPosition)
            }
        }
    }
}