package com.example.gachongo.presentation.main.home.want

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gachongo.api.DeliveryService
import com.example.gachongo.api.OrderService
import com.example.gachongo.api.OrderView
import com.example.gachongo.api.write.OrderWriteView
import com.example.gachongo.data.response.ResponseOrderDto
import com.example.gachongo.presentation.main.write.GoWriteActivity
import com.example.gachongo.presentation.main.write.WantWriteActivity
import com.example.gachongo.util.binding.BindingFragment
import com.example.gachongo.util.getUserJwt
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.FragmentWantDeliveryBinding

class WantDeliveryFragment :
    BindingFragment<FragmentWantDeliveryBinding>(R.layout.fragment_want_delivery),View.OnClickListener,
    OrderView {

    private var isFabMenuOpen = false
    private var wantDeliveryAdapter: WantDeliveryAdapter? = null
    private lateinit var orderService: OrderService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFabButtons()

        orderService = OrderService(this)
        orderService.getItemList(getUserJwt(requireContext()), 0, 10)
    }

    private fun initAdapter(result: MutableList<ResponseOrderDto.Result>) {
        wantDeliveryAdapter = WantDeliveryAdapter(result)
        binding.rvWantDelivery.adapter = wantDeliveryAdapter
        binding.rvWantDelivery.layoutManager = LinearLayoutManager(requireContext())
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

    override fun onGetOrderResultSuccess(result: MutableList<ResponseOrderDto.Result>) {
        initAdapter(result)
    }

    override fun onGetOrderResultFailure(message: String) {
        TODO("Not yet implemented")
    }
}
