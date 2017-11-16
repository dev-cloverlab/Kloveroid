package com.cloverlab.kloveroid.ui.fragments.main

import com.cloverlab.kloveroid.mvp.contracts.MainContract
import com.cloverlab.kloveroid.mvp.models.FakeModel
import com.cloverlab.kloveroid.usecases.CreateFakeUseCase
import com.cloverlab.kloveroid.utilies.extension.execute
import com.devrapid.kotlinknifer.logw
import com.devrapid.kotlinknifer.observer

/**
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
class MainPresenter(private val fakeCase: CreateFakeUseCase) : MainContract.Presenter() {
    //region View implementation
    override fun init() {
        val request = CreateFakeUseCase.Requests(FakeModel("Jieyi", 19, "H"))
        fakeCase.execute(request, lifecycleProvider, observer { logw(it) })
        lifecycleProvider.execute(fakeCase, request) { onNext { logw(it) } }
        view.hideLoading()
    }
    //endregion
}
