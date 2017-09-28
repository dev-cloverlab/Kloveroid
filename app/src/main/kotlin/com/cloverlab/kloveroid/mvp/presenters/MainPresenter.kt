package com.cloverlab.kloveroid.mvp.presenters

import com.cloverlab.kloveroid.mvp.contracts.MainContract
import com.cloverlab.kloveroid.mvp.models.FakeModel
import com.cloverlab.kloveroid.usecases.CreateFakeUseCase
import com.devrapid.kotlinknifer.observable
import dagger.internal.Preconditions

/**
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
class MainPresenter(val fakeCase: CreateFakeUseCase): MainContract.Presenter {
    private lateinit var view: MainContract.View

    //region View implementation
    override fun setView(view: MainContract.View) {
        Preconditions.checkNotNull(view)

        this.view = view
    }

    override fun init() {
        val request = CreateFakeUseCase.Requests(FakeModel("Jieyi", 19, "H"))
        fakeCase.execute(request, observable<FakeModel> { })
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
    }
    //endregion
}
