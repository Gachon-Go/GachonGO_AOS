package com.example.gachongo.presentation.main.write

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import com.example.gachongo.api.write.DeliveryWriteService
import com.example.gachongo.api.write.DeliveryWriteView
import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.request.RequestDeliveryDto
import com.example.gachongo.util.binding.BindingActivity
import com.example.gachongo.util.extension.showToast
import com.example.gachongo.util.getUserJwt
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.ActivityGoWriteBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class GoWriteActivity :
    BindingActivity<ActivityGoWriteBinding>(R.layout.activity_go_write), DeliveryWriteView {
    private lateinit var content: String
    private lateinit var time: String
    private lateinit var title: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTimePickerBtnClickListener()
        initWriteFinishBtnClickListener()
    }

    private fun initTimePickerBtnClickListener() {
        binding.btnWriteTime.setOnClickListener {
            var cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                binding.btnWriteTime.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(
                this,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true,
            ).show()
        }
    }

    private fun initWriteFinishBtnClickListener() {
        binding.btnWriteFinish.setOnClickListener() {
            content = binding.etWriteContent.text.toString()
            time = binding.btnWriteTime.text.toString()
            title = binding.etWriteTitle.text.toString()

            if (time == "예상 배달 시간을 입력해주세요.") {
                showToast("예상 배달 시간을 입력해주세요.")
            } else {
                postDelivery()
            }
        }
    }

    private fun postDelivery() {
        val jwt: String = getUserJwt(this)

        val deliveryWriteService = DeliveryWriteService(this)
        deliveryWriteService.postDelivery(
            jwt,
            requestBody = RequestDeliveryDto(content, time, title),
        )
    }

    override fun onGetDeliveryWriteResultSuccess(response: BaseResponse) {
        Log.d("GachonLog #게시글", "배달해주세요: 게시글 작성 성공")
        showToast("글 작성이 완료되었습니다.")
        finish()
    }

    override fun onGetDeliveryWriteResultFailure(messageDigest: String) {
        Log.d("GachonLog #게시글", "배달해주세요: 게시글 작성 실패")
    }
}
