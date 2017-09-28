package com.cloverlab.kloveroid.mvp.presenters

import com.cloverlab.kloveroid.domain.CreateFakeUseCase
import com.cloverlab.kloveroid.mvp.contracts.MainContract
import dagger.internal.Preconditions

/**
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
class MainPresenter(val fakeCase: CreateFakeUseCase): MainContract.Presenter {
    private lateinit var view: MainContract.View

    //region Subscribers
//    private val fakeSubscriber = subscriber<FakeModel>().onCompleted { }.onError { }.onNext { }
    //endregion

    //region View implementation
    override fun setView(view: MainContract.View) {
        Preconditions.checkNotNull(view)

        this.view = view
    }

    override fun init() {
//        val request = CreateFakeUseCase.Requests(FakeModel("Jieyi", 19, "H"))
//        fakeCase.execute(request, fakeSubscriber)
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
    }
    //endregion
}
