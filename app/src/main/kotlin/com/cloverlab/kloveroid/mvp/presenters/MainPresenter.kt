package com.cloverlab.kloveroid.mvp.presenters

import com.cloverlab.kloveroid.mvp.contracts.MainContract
import com.cloverlab.kloveroid.mvp.contracts.MainContract.View
import com.cloverlab.kloveroid.mvp.models.FakeModel
import com.cloverlab.kloveroid.usecases.CreateFakeUseCase
import com.devrapid.kotlinknifer.observable

/**
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
class MainPresenter(val fakeCase: CreateFakeUseCase): MainContract.Presenter() {
    override lateinit var view: View

    //region View implementation
    override fun init() {
        val request = CreateFakeUseCase.Requests(FakeModel("Jieyi", 19, "H"))
        fakeCase.execute(request, observable<FakeModel> { })
    }
    //endregion
}
