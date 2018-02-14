package com.pymblesoftware.telstratest.network

import android.util.Log
import com.pymblesoftware.telstratest.datamodel.TitleData
import io.reactivex.Observer
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * Created by regan on 14/2/18.
 */

class Repository(val apiService: ApiService): io.reactivex.Observable<TitleData>() {
        override fun subscribeActual(observer: Observer<in TitleData>?) {
        }


        fun userAgent() = "TelstraTest"

        fun fetch(): io.reactivex.Observable<TitleData> {
//            val requestBody = RequestBody.create(MediaType.parse("application/json"), json )
            return apiService.fetch( userAgent())
        }

}