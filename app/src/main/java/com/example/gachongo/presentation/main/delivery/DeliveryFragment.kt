package com.example.gachongo.presentation.main.delivery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gachongo_aos.databinding.FragmentDeliveryBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DeliveryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeliveryFragment : Fragment() {
    private lateinit var binding: FragmentDeliveryBinding
    private val ACCESS_FINE_LOCATION = 1000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeliveryBinding.inflate(inflater, container, false)
        return binding.root
    }

}