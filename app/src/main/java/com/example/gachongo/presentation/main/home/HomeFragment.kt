package com.example.gachongo.presentation.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gachongo.presentation.main.point.PointActivity
import com.example.gachongo.presentation.qr.QrActivity
import com.example.gachongo_aos.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPayClickListener()
        initGoDeliveryClickListener()
        initWantDeliveryClickListener()
        initPointClickListener()
    }

    private fun initPayClickListener() {
        binding.layoutHomePay.setOnClickListener {
            val intent = Intent(getActivity(), QrActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initGoDeliveryClickListener() {
        binding.btnGoDelivery
    }

    private fun initWantDeliveryClickListener() {
        binding.btnWantDelivery
    }

    private fun initPointClickListener() {
        binding.layoutHomePoint.setOnClickListener {
            val intent = Intent(getActivity(), PointActivity::class.java)
            startActivity(intent)
        }
    }
}
