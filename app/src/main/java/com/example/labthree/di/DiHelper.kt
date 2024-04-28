package com.example.labthree.di

import com.example.labthree.data.DataSource
import com.example.labthree.data.TestApiService
import com.example.labthree.data.api.RetrofitApiHelper
import com.example.labthree.ui.MainContract
import com.example.labthree.ui.MainPresenter

class DiHelper {
    companion object {
        private var mainPresenter: MainContract.Presenter? = null
        private var service: DataSource? = null
        private var retrofitHelper: RetrofitApiHelper? = null

        fun getPresenter(view: MainContract.View) : MainContract.Presenter {
            if (mainPresenter == null) {
                mainPresenter = MainPresenter(view)
            }
            return mainPresenter!!
        }

        fun getService(): DataSource {
            if (service == null) {
                service = TestApiService()
            }
            return service!!
        }

        fun getRetrofitHelper(): RetrofitApiHelper {
            if (retrofitHelper == null) {
                retrofitHelper = RetrofitApiHelper()
                retrofitHelper?.init()
            }
            return retrofitHelper!!
        }
    }
}