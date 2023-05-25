package com.example.gachongo.presentation.main.home.go

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gachongo_aos.databinding.FragmentGoDeliveryBinding

class GoDeliveryFragment : Fragment() {
    private var _binding: FragmentGoDeliveryBinding? = null
    private val binding: FragmentGoDeliveryBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentGoDeliveryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvGoDelivery.adapter = GoDeliveryAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
