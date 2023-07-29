package com.example.taskmanager.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.taskmeneger.R
import com.example.taskmanager.data.local.Pref
import com.example.taskmeneger.databinding.FragmentProfileragmentBinding
import com.github.dhaval2404.imagepicker.ImagePicker

class Profileragment : Fragment() {

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val data = result.data
            binding.imgProfile.setImageURI(data?.data)
            pref.saveImage(data?.data.toString())

        }
    private lateinit var binding: FragmentProfileragmentBinding
    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProfileragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etName.setText(pref.getName())
        binding.etName.addTextChangedListener {
            pref.saveName(binding.etName.text.toString())
        }

        binding.imgProfile.setImageURI(pref.getImage().toString().toUri())
        binding.imgProfile.setOnClickListener {
            ImagePicker.with(this)
                .compress(1024)
                .maxResultSize(1080,
                    1080)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }
    }
}