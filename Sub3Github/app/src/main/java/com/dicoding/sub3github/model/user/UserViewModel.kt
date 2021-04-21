package com.dicoding.sub3github.model.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.sub3github.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    val listUsers = MutableLiveData<ArrayList<User>>()
    fun setSearchs(query: String) {
        RetrofitServices.api_Instance
            .getSearchs(query)
            .enqueue(object : Callback<UserModel> {
                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
//                    response per item body
                    if (response.isSuccessful) {
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    Log.d("Gagal", t.message.toString())
                }
            })
    }

    fun getSearchs(): LiveData<ArrayList<User>> {
        return listUsers
    }
}