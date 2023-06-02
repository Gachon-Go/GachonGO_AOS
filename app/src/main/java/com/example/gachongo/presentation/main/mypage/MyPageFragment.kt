package com.example.gachongo.presentation.main.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.gachongo.api.MyPageService
import com.example.gachongo.api.MyPageView
import com.example.gachongo.data.MyPageResponseResult
import com.example.gachongo.util.getUserJwt
import com.example.gachongo.util.getUserNickname
import com.example.gachongo.util.getUserPoint
import com.example.gachongo.util.getUserProfileImg
import com.example.gachongo.util.saveUserNickname
import com.example.gachongo_aos.databinding.FragmentMyPageBinding
import java.text.DecimalFormat

class MyPageFragment : Fragment(), MyPageView {
    private lateinit var binding: FragmentMyPageBinding
    private var buildingName = arrayListOf(
        "가천관", "비전타워", "법과대학", "공과대학1", "공과대학2", "바이오나노대학",
        "산학협력관2", "한의과대학", "예술체육대학1", "에술체육대학2", "글로벌센터",
        "중앙도서관", "전자정보도서관", "대학원/평생교육원", "교육대학원", "산학협력관",
        "바이오나노연구원", "학생회관", "학생생활관", "AI관",
    )
    private var adapter = ActHistoryAdapter(buildingName)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)

        initView()

        binding.myPageEditBtn.setOnClickListener {
            startActivity(Intent(requireContext(), MyPageEditActivity::class.java))
        }

        binding.myPagePointManageBtn.setOnClickListener {
            startActivity(Intent(requireContext(), PointManageActivity::class.java))
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.myPageNicknameTv.text = getUserNickname(requireContext())
        binding.myPagePointTv.text = getUserPoint(requireContext()).toString()
        Glide.with(this).load(getUserProfileImg(requireContext())).into(binding.myPageProfileIv)
    }

    private fun initView() {
        // recyclerview adapter
        binding.myPageActivityHistoryRv.adapter = adapter
        Glide.with(requireContext()).load(getUserProfileImg(requireContext()))
            .into(binding.myPageProfileIv)

        getUserInfo()
    }

    private fun getUserInfo() {
        val myPageService = MyPageService(this)
        myPageService.getMyPage(getUserJwt(requireContext()))
    }

    override fun onGetMyPageSuccess(result: MyPageResponseResult) {
        saveUserNickname(requireContext(), result.nickname)

        binding.myPageNicknameTv.text = result.nickname
        binding.myPagePointTv.text = DecimalFormat("#,###").format(result.point).toString()
        binding.myPageInfoOrderCountTv.text = result.orderNum.toString()
        binding.myPageInfoDeliveryCountTv.text = result.deliveryNum.toString()
        binding.myPageInfoPostCountTv.text = result.postNum.toString()
    }

    override fun onGetMyPageFailure(message: String) {
        Log.d("GachonLog #마이페이지 사용자 정보 요청 오류", message)
    }
}
