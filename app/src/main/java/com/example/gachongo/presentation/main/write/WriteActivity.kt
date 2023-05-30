package com.example.gachongo.presentation.main.write

import android.app.TimePickerDialog
import android.os.Bundle
import com.example.gachongo.api.write.DeliveryWriteService
import com.example.gachongo.api.write.DeliveryWriteView
import com.example.gachongo.data.request.RequestDeliveryDto
import com.example.gachongo.util.binding.BindingActivity
import com.example.gachongo.util.extension.showToast
import com.example.gachongo.util.getUserJwt
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.ActivityWriteBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class WriteActivity :
    BindingActivity<ActivityWriteBinding>(R.layout.activity_write), DeliveryWriteView {
    private lateinit var content: String
    private lateinit var time: String
    private lateinit var title: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTimePickerBtnClickListener()
        initWriteFinishBtnClickLisnter()
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

    private fun initWriteFinishBtnClickLisnter() {
        binding.btnWriteFinish.setOnClickListener() {
            content = binding.etWriteContent.text.toString()
            time = binding.btnWriteTime.text.toString()
            title = binding.etWriteTitle.text.toString()

            postDelivery()
            finish()
        }
    }

    private fun postDelivery() {
        val jwt: String = getUserJwt(this)

        val deliveryWriteService = DeliveryWriteService(this)
        deliveryWriteService.postDelivery(jwt, body = RequestDeliveryDto(content, time, title))
    }

    override fun onGetDeliveryWriteResultSuccess() {
        showToast("글 작성이 완료되었습니다.")
    }

    override fun onGetDeliveryWriteResultFailure(messageDigest: String) {
        TODO("Not yet implemented")
    }
}
