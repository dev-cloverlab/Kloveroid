package com.cloverlab.kloveroid.feature.main

import com.cloverlab.kloveroid.entities.FakeEntity
import com.cloverlab.kloveroid.mvp.contracts.MainContract
import com.cloverlab.kloveroid.usecases.CreateFakeUseCase
import com.devrapid.kotlinknifer.logw

/**
 *
 * @author  Jieyi Wu
 * @since   2017/09/25
 */
class MainPresenter(private val fakeCase: CreateFakeUseCase) : MainContract.Presenter() {
    //region View implementation
    override fun init() {
        val request = CreateFakeUseCase.Requests(FakeEntity("Jieyi", 19, "H"))
        fakeCase.execute(request, lifecycleProvider) { onNext { logw(it) } }
//        lifecycleProvider.execute(fakeCase, request) { onNext { logw(it) } }
        view.hideLoading()
    }
    //endregion
}
