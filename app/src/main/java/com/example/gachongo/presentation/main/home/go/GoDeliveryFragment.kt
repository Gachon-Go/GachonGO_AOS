package com.example.gachongo.presentation.main.home.go

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gachongo.presentation.main.WriteActivity
import com.example.gachongo.util.binding.BindingFragment
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.FragmentGoDeliveryBinding

class GoDeliveryFragment :
    BindingFragment<FragmentGoDeliveryBinding>(R.layout.fragment_go_delivery),
    View.OnClickListener {

    private var isFabMenuOpen = false
    private var goDeliveryAdapter: GoDeliveryAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        setupFabButtons()
    }

    private fun initAdapter() {
        goDeliveryAdapter = GoDeliveryAdapter()
        binding.rvGoDelivery.adapter = goDeliveryAdapter
        binding.rvGoDelivery.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupFabButtons() {
        binding.fabMenuMain.shrink()
        binding.fabMenuMain.setOnClickListener(this)
        binding.fabMenuWant.setOnClickListener(this)
        binding.fabMenuGo.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.fab_menu_main -> {
                if (isFabMenuOpen) {
                    collapseFAB()
                } else {
                    expandFAB()
                }
                isFabMenuOpen = !isFabMenuOpen
            }

            R.id.fab_menu_want -> {
            }

            R.id.fab_menu_go -> {
                val intent = Intent(getActivity(), WriteActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun expandFAB() {
        binding.fabMenuMain.extend()
        binding.fabMenuWant.show()
        binding.tvFabMenuWant.visibility = View.VISIBLE
        binding.fabMenuGo.show()
        binding.tvFabMenuGo.visibility = View.VISIBLE
    }

    private fun collapseFAB() {
        binding.fabMenuMain.shrink()
        binding.fabMenuWant.hide()
        binding.tvFabMenuWant.visibility = View.GONE
        binding.fabMenuGo.hide()
        binding.tvFabMenuGo.visibility = View.GONE
    }
}
