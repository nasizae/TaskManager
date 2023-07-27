package com.example.taskmeneger.ui.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskmeneger.data.local.Pref
import com.example.taskmeneger.R
import com.example.taskmeneger.databinding.FragmentOnBoardingBinding
import com.example.taskmeneger.ui.onBoarding.onBoardingAdapter.onBoardingAdapter

class OnBoardingFragment : Fragment() {

        private lateinit var binding: FragmentOnBoardingBinding
        private val pref:Pref by lazy {
            Pref(requireContext())
        }
        private val adapter=onBoardingAdapter(this::onClick)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentOnBoardingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter=adapter
        binding.indicator.setViewPager(binding.viewPager)
    }
    private fun onClick(){
        pref.onOnBoardingShowed()
        findNavController().navigateUp()
    }
}