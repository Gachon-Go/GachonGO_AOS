package com.example.gachongo.presentation.main.delivery

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.gachongo.api.OthersPositionService
import com.example.gachongo.api.OthersPositionView
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.FragmentDeliveryBinding
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DeliveryFragment : Fragment(), OnMapReadyCallback, OthersPositionView {
    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    private lateinit var binding: FragmentDeliveryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeliveryBinding.inflate(inflater, container, false)

        // 위치권한 얻어오기
        if (!hasLocationPermission()) requestLocationPermission()
        else initMapView()

        // 다른 사용자들의 위치들을 받아와요
        lifecycleScope.launch {
            getOthersPosition()
            delay(5000)
        }

        return binding.root
    }

    private fun initMapView() {
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.delivery_naver_map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.delivery_naver_map, it).commit()
            }

        // fragment의 getMapAsync() 메서드로 OnMapReadyCallback 콜백을 등록하면 비동기로 NaverMap 객체를 얻을 수 있다.
        mapFragment.getMapAsync(this)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.locationSource = locationSource

        // 현재 위치 버튼 기능
        naverMap.uiSettings.isLocationButtonEnabled = true
        // 위치를 추적하면서 카메라도 따라 움직인다.
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
    }

    private fun hasLocationPermission(): Boolean {
        val permission = Manifest.permission.ACCESS_FINE_LOCATION
        val result = ContextCompat.checkSelfPermission(requireContext(), permission)
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        val permission = Manifest.permission.ACCESS_FINE_LOCATION

        val locationPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission granted, handle accordingly
                enableLocationTracking()
            } else {
                // Permission denied, handle accordingly
                disableLocationTracking()
            }
        }

        locationPermissionLauncher.launch(permission)
    }

    private fun enableLocationTracking() {
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
    }

    private fun disableLocationTracking() {
        naverMap.locationTrackingMode = LocationTrackingMode.None
    }

    private fun getOthersPosition() {
        val othersPositionService = OthersPositionService(this)
        othersPositionService.getOthersPosition(purpose, postId)
    }

    override fun onGetOthersPositionResultSuccess() {

    }

    override fun onGetOthersPositionResultFailure(message: String) {

    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

}