package com.pymblesoftware.telstratest.network

import android.util.Log
import com.pymblesoftware.telstratest.datamodel.TitleData
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers

/**
 * Created by regan on 14/2/18.
 */
interface ApiService {


    //  https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json

    companion object Factory {
        fun create(): ApiService {

            // Default code that was/is working.
            val serverUrl = "https://dl.dropboxusercontent.com"
            Log.d("RR:","*** web service call server base url:${serverUrl}")
            val baseUrlStr = serverUrl

            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .baseUrl(baseUrlStr)
                    .build()

            return retrofit.create(ApiService::class.java);

        }
    }


    @Headers(  "Content-Type:application/json", "Accept:application/json" )
    @retrofit2.http.POST("/s/2iodh4vg0eortkl/facts.json")
    fun fetch(@Header("User-Agent") agent: String) : io.reactivex.Observable<TitleData>


}