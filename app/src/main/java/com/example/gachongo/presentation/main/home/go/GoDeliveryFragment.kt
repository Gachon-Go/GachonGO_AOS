package com.example.gachongo.presentation.main.home.go

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gachongo.util.binding.BindingFragment
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.FragmentGoDeliveryBinding

class GoDeliveryFragment :
    BindingFragment<FragmentGoDeliveryBinding>(R.layout.fragment_go_delivery) {

    private var goDeliveryAdapter: GoDeliveryAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
    }

    private fun initAdapter() {
        goDeliveryAdapter = GoDeliveryAdapter()
        binding.rvGoDelivery.adapter = goDeliveryAdapter
        binding.rvGoDelivery.layoutManager = LinearLayoutManager(requireContext())
    }
}
