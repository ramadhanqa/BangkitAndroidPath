package com.dicoding.sub3github.model.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.sub3github.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    val user = MutableLiveData<DetailModel>()

    //set details
    fun setDetails(username: String) {
        RetrofitServices.api_Instance
            .getDetails(username)
            .enqueue(object : Callback<DetailModel> {
                override fun onResponse(call: Call<DetailModel>, response: Response<DetailModel>) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailModel>, t: Throwable) {
                    Log.d("Gagal", t.message.toString())
                }
            })
    }

    //    get details
    fun getDetailUser(): LiveData<DetailModel> {
        return user
    }
}