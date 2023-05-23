package com.example.gachongo.presentation.main.write

import android.app.TimePickerDialog
import android.os.Bundle
import com.example.gachongo.api.DeliveryWriteService
import com.example.gachongo.api.DeliveryWriteView
import com.example.gachongo.data.RequestDeliveryWrite
import com.example.gachongo.util.binding.BindingActivity
import com.example.gachongo.util.extension.showToast
import com.example.gachongo.util.getUserJwt
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.ActivityWriteBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class WriteActivity :
    BindingActivity<ActivityWriteBinding>(R.layout.activity_write),
    DeliveryWriteView {
//    private lateinit var content: String
//    private lateinit var time: String
//    private lateinit var title: String

    var content: String = "빨리 와"
    var time: String = " "
    var title: String = "배달 ㄱ"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initWriteFinishBtnClickLisnter()

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

            writeDeliveryPost()
            finish()
        }
    }

    private fun writeDeliveryPost() {
        val jwt: String = getUserJwt(this)
        val requestDeliveryWrite = RequestDeliveryWrite(content, time, title)

        val deliveryWriteService = DeliveryWriteService(this)
        deliveryWriteService.deliveryWrite(jwt, requestDeliveryWrite)
    }

    override fun onGetDeliveryWriteResultSuccess(result: String) {
        showToast("게시글 작성 완료되었습니다.")
    }

    override fun onGetDeliveryWriteResultFailure(messageDigest: String) {
        showToast(messageDigest)
    }
}
