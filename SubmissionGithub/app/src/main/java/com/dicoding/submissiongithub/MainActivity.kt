package com.dicoding.submissiongithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submissiongithub.network.RetroInstance
import com.dicoding.submissiongithub.network.RetroService
import retrofit.Callback
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var rvChars: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvChars = findViewById(R.id.rv_user)
        rvChars.setHasFixedSize(true)
        initRecylcerView()
        createData()
    }

    private fun initRecylcerView() {
        rvChars.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter

        }
    }

    private fun createData() {
//       val item = ArrayList<String>()
//       item.add("Java")
//       item.add("C++")
//       item.add("Android")
//       item.add("IOS")
//       item.add("PHP")
//       item.add("Kotlin")
//
//        recyclerViewAdapter.setListData(item)
//        recyclerViewAdapter.notifyDataSetChanged()
        val retrofit = RetroInstance.getRetroInstance().getDataFromAPI("a")
        retrofit.enqueue(object :retrofit2.Callback<RecyclerList>{
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if (response.isSuccessful){
                    recyclerViewAdapter.setListData(response.body()?.items!!)
                    recyclerViewAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Eror in getting data from API",Toast.LENGTH_SHORT).show()
            }
        })


    }
}