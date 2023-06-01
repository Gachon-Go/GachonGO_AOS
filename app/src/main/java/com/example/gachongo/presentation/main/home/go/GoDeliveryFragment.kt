package com.example.gachongo.presentation.main.home.go

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gachongo.api.DeliveryService
import com.example.gachongo.api.DeliveryView
import com.example.gachongo.data.response.ResponseDeliveryDto
import com.example.gachongo.presentation.main.write.GoWriteActivity
import com.example.gachongo.presentation.main.write.WantWriteActivity
import com.example.gachongo.util.binding.BindingFragment
import com.example.gachongo.util.getUserJwt
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.FragmentGoDeliveryBinding

class GoDeliveryFragment :
    BindingFragment<FragmentGoDeliveryBinding>(R.layout.fragment_go_delivery),
    View.OnClickListener,
    DeliveryView {

    private var isFabMenuOpen = false
    private var goDeliveryAdapter: GoDeliveryAdapter? = null
    private lateinit var deliveryService: DeliveryService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFabButtons()

        deliveryService = DeliveryService(this)
        deliveryService.getItemList(getUserJwt(requireContext()), 0, 10)
    }

    private fun initAdapter(result: MutableList<ResponseDeliveryDto.Result>) {
        goDeliveryAdapter = GoDeliveryAdapter(result)
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
                val intent = Intent(getActivity(), WantWriteActivity::class.java)
                startActivity(intent)
            }

            R.id.fab_menu_go -> {
                val intent = Intent(getActivity(), GoWriteActivity::class.java)
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

    override fun onGetDeliveryResultSuccess(result: MutableList<ResponseDeliveryDto.Result>) {
        initAdapter(result)
    }

    override fun onGetDeliveryResultFailure(message: String) {
        Log.d("error", "페이지 정보 조회 실패 $message")
    }
}
