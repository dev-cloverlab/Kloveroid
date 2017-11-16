package com.cloverlab.kloveroid.mvp.contracts

import com.cloverlab.kloveroid.mvp.presenters.BasePresenter
import com.cloverlab.kloveroid.mvp.presenters.IPresenter
import com.cloverlab.kloveroid.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
interface MainContract {
    abstract class Presenter : BasePresenter<MainContract.View>()

    interface View : IView
}