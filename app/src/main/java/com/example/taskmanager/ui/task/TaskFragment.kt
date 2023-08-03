package com.example.taskmanager.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmeneger.databinding.FragmentTaskBinding
import com.example.taskmanager.model.Task

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        task = arguments?.getSerializable(TASK_KEY) as Task?
        if (task == null) {
            onSave()
        } else {
            onUpdate()
        }
    }

    private fun onUpdate() {
        binding.etTitle.setText(task?.title)
        binding.etDesc.setText(task?.desc)
        binding.btnAdd.text = "update"
        binding.btnAdd.setOnClickListener {
            val data = task?.copy(
                title = binding.etTitle.text.toString(), desc = binding.etDesc.text.toString())
            App.database.taskDao().upDate(data!!)
            findNavController().navigateUp()
        }
    }

    private fun onSave() {
        binding.btnAdd.setOnClickListener {
            if (binding.etTitle.text.toString().isNotEmpty()) {
                val data =
                    Task(title = binding.etTitle.text.toString(),
                        desc = binding.etDesc.text.toString())
                App.database.taskDao().insert(data)
                findNavController().navigateUp()
            }
            else{
                Toast.makeText(context,"заголовок не должен быть пустым",Toast.LENGTH_LONG).show()
                binding.etTitle.error="null"
                return@setOnClickListener
            }
        }
    }

    companion object {
        val TASK_KEY = "task_key"
    }
}