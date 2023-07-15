package com.example.taskmeneger.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.taskmeneger.Adapter.TaskAdapter
import com.example.taskmeneger.R
import com.example.taskmeneger.databinding.FragmentHomeBinding
import com.example.taskmeneger.model.Task
import com.example.taskmeneger.ui.Task.TaskFragment.Companion.RESULT_KEY
import com.example.taskmeneger.ui.Task.TaskFragment.Companion.RESULT_REQUEST_KEY

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private  val taskAdapter=TaskAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNotes.adapter=taskAdapter
            setFragmentResultListener(RESULT_REQUEST_KEY,{
                requestKey, bundle ->
                val data=bundle.getSerializable(RESULT_KEY) as Task
                taskAdapter.addTask(data)
            })

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}