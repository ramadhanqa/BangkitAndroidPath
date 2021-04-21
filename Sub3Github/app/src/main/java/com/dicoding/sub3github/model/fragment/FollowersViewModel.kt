package com.dicoding.sub3github.model.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.sub3github.model.user.User
import com.dicoding.sub3github.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {
    val listUsers = MutableLiveData<ArrayList<User>>()
    fun setFollowers(query: String) {
        RetrofitServices.api_Instance
            .getFollowers(query)
            .enqueue(object : Callback<ArrayList<User>> {
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful) {
                        listUsers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Log.d("Gagal", t.message.toString())
                }

            })
    }

    fun getFollowers(): LiveData<ArrayList<User>> {
        return listUsers
    }
}