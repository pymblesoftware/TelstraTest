package com.pymblesoftware.telstratest.network

import android.util.Log
import com.pymblesoftware.telstratest.datamodel.TitleData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.Response

/**
 * Created by regan on 14/2/18.
 */

object NetworkWrapper {


    val TAG = "RR:"

    var token : String?= null
    var access_token_exp : Int? = null
    var refreshToken : String?= null        // TODO: when also updating this, also update in the application object
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val repository = RepositoryProvider.provideRepository()
//    val requestQueue = mutableListOf<QueuedRequest>()
    var refreshingToken = false




    fun sucessfulResponseCode(response: Response<*>): Boolean = response.code() >= 200 && response.code() <= 299
    fun sucessfulResponseCode(code: Int): Boolean = code >= 200 && code <= 299



    fun fetch( successHandler : (data: TitleData)-> Unit ) {

        compositeDisposable.add(
                repository.fetch()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({ result ->
                            if (result != null) {
                                Log.d("RR:", "Result ${result}")
                                successHandler(result)
                            }


                        }, { error ->

                            if( error is HttpException && !sucessfulResponseCode(error.code() )) {
//                                handleCommonErrors(error.code())
                            }

//                            if( !recursed ) {
//                                handleExpiredTokens(error, { fetch(successHandler, errorHandler, true) }, {})
//                            }

//                            Log.d(MainActivity.TAG, "Error: ${error}")
//                            error.printStackTrace()
//                            errorHandler(error.message!!)
                        })
        )
    }

}