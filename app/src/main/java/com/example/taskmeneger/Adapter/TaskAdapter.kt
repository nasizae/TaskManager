package com.example.taskmeneger.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.taskmeneger.databinding.ItemTaskBinding
import com.example.taskmeneger.model.Task

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

        private val taskList=ArrayList<Task>()

    fun addTask(task: Task){
        taskList.add(0,task)
        notifyItemChanged(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        return TaskHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TaskHolder(private var binding: ItemTaskBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(task:Task){
            binding.tvTitle.text=task.title
            binding.tvDesc.text=task.desc
        }
    }
}