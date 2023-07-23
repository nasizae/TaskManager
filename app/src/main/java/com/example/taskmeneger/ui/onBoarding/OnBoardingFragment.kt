package com.example.taskmeneger.ui.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskmeneger.R
import com.example.taskmeneger.databinding.FragmentDashboardBinding
import com.example.taskmeneger.databinding.FragmentOnBoardingBinding
import com.example.taskmeneger.ui.onBoarding.onBoardingAdapter.onBoardingAdapter
import me.relex.circleindicator.CircleIndicator

class OnBoardingFragment : Fragment() {

        private lateinit var binding: FragmentOnBoardingBinding
        private lateinit var indicator: CircleIndicator
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
        findNavController().navigate(R.id.navigation_home)
    }
}