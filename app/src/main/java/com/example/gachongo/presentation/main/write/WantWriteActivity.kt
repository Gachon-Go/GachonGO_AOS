package com.example.gachongo.presentation.main.write

import android.app.TimePickerDialog
import android.os.Bundle
import com.example.gachongo.api.write.OrderWriteService
import com.example.gachongo.api.write.OrderWriteView
import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.request.RequestOrderDto
import com.example.gachongo.util.binding.BindingActivity
import com.example.gachongo.util.extension.showToast
import com.example.gachongo.util.getUserJwt
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.ActivityWantWriteBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class WantWriteActivity :
    BindingActivity<ActivityWantWriteBinding>(R.layout.activity_want_write),
    OrderWriteView {

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

            if (time == "예상 배달 시간을 입력해주세요.")
                showToast("예상 배달 시간을 입력해주세요.")
            else
                postOrder()
        }
    }

    private fun postOrder() {
        val jwt: String = getUserJwt(this)

        val orderWriteService = OrderWriteService(this)
        orderWriteService.postOrder(
            jwt,
            requestBody = RequestOrderDto(content, time, title),
        )
    }

    override fun onGetWantWriteResultSuccess(response: BaseResponse) {
        showToast("글 작성이 완료되었습니다.")
        finish()
    }

    override fun onGetWantWriteResultFailure(messageDigest: String) {
        TODO("Not yet implemented")
    }
}
