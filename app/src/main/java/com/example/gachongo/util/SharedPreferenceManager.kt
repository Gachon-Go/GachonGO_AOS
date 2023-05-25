package com.example.gachongo.util

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

fun saveUserNickname(context: Context, nickname: String) {
    val spf = context.getSharedPreferences("nickname", AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()

    editor.putString("nickname", nickname)
    editor.apply()
}

fun getUserNickname(context: Context): String {
    val spf = context.getSharedPreferences("nickname", AppCompatActivity.MODE_PRIVATE)

    return spf.getString("nickname", "")!!
}

fun saveUserEmail(context: Context, email: String) {
    val spf = context.getSharedPreferences("email", AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()

    editor.putString("email", email)
    editor.apply()
}

fun getUserEmail(context: Context): String {
    val spf = context.getSharedPreferences("email", AppCompatActivity.MODE_PRIVATE)

    return spf.getString("email", "")!!
}

fun saveUserLoginProvider(context: Context, provider: String) {
    val spf = context.getSharedPreferences("provider", AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()

    editor.putString("provider", provider)
    editor.apply()
}

fun getUserLoginProvider(context: Context): String {
    val spf = context.getSharedPreferences("provider", AppCompatActivity.MODE_PRIVATE)

    return spf.getString("provider", "")!!
}

fun saveUserToken(context: Context, token: String) {
    val spf = context.getSharedPreferences("token", AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()

    editor.putString("token", token)
    editor.apply()
}

fun getUserToken(context: Context): String {
    val spf = context.getSharedPreferences("token", AppCompatActivity.MODE_PRIVATE)

    return spf.getString("token", "")!!
}

fun saveUserProfileImg(context: Context, profileImg: String) {
    val spf = context.getSharedPreferences("profileImg", AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()

    editor.putString("profileImg", profileImg)
    editor.apply()
}

fun getUserProfileImg(context: Context): String {
    val spf = context.getSharedPreferences("profileImg", AppCompatActivity.MODE_PRIVATE)

    return spf.getString("profileImg", "")!!
}

fun saveUserId(context: Context, id: Int) {
    val spf = context.getSharedPreferences("id", AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()

    editor.putInt("id", id)
    editor.apply()
}

fun getUserId(context: Context): Int {
    val spf = context.getSharedPreferences("id", AppCompatActivity.MODE_PRIVATE)

    return spf.getInt("id", 0)
}

fun saveUserJwt(context: Context, jwt: String) {
    val spf = context.getSharedPreferences("jwt", AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()

    editor.putString("jwt", "Bearer $jwt")
    editor.apply()
}

fun getUserJwt(context: Context): String {
    val spf = context.getSharedPreferences("jwt", AppCompatActivity.MODE_PRIVATE)

    return spf.getString("jwt", "")!!
}

fun saveUserLocationCheck(context: Context, isFirstCheck: Boolean) {
    val spf = context.getSharedPreferences("isFirstCheck", AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()

    editor.putBoolean("isFirstCheck", isFirstCheck)
    editor.apply()
}

fun getUserLocationCheck(context: Context): Boolean {
    val spf = context.getSharedPreferences("isFirstCheck", AppCompatActivity.MODE_PRIVATE)

    return spf.getBoolean("isFirstCheck", true)
}