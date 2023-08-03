package com.example.taskmanager.ui.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmeneger.R
import com.example.taskmanager.ui.task.adapter.TaskAdapter
import com.example.taskmeneger.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val taskAdapter = TaskAdapter(this::onLongClick, this::onClickTask)

    private fun onClickTask(bundle: Bundle) {
        findNavController().navigate(R.id.taskFragment, bundle)
    }

    private fun onLongClick(taskId: Int): Boolean {
        val builder = AlertDialog.Builder(this.requireContext())
        builder.setTitle(getString(R.string.delete))
        builder.setMessage(getString(R.string.question))
        builder.setNeutralButton(getString(R.string.cancel)) { _, _ ->
        }
        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            val list = App.database.taskDao().getAll()
            val taskListItem = list[taskId]
            App.database.taskDao().delete(taskListItem)
            findNavController().navigate(R.id.navigation_home)
        }
        builder.show()
        return true
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNotes.adapter = taskAdapter
        val data = App.database.taskDao().getAll()
        taskAdapter.addTasks(data)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}