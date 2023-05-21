package com.example.gachongo.presentation.main.home.want

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gachongo.util.binding.BindingFragment
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.FragmentWantDeliveryBinding

class WantDeliveryFragment :
    BindingFragment<FragmentWantDeliveryBinding>(R.layout.fragment_want_delivery) {

    private var wantDeliveryAdapter: WantDeliveryAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
    }

    private fun initAdapter() {
        wantDeliveryAdapter = WantDeliveryAdapter()
        binding.rvWantDelivery.adapter = wantDeliveryAdapter
        binding.rvWantDelivery.layoutManager = LinearLayoutManager(requireContext())
    }
}
