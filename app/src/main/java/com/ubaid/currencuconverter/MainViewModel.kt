package com.ubaid.currencuconverter

import ResponseData
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel:ViewModel() {
    val liveData = MutableLiveData<ResponseData>()

    fun getData(curr1: String, curr2: String) {

        ApiClient.getClient().getResponse(curr1,curr2).enqueue(object : Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                if (response.isSuccessful){
                    val response = response.body()
                    liveData.postValue(response)

                }
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Log.d("onFailure","${t.message}")
            }

        })
    }
}