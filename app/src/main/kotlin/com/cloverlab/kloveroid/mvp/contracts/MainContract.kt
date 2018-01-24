package com.cloverlab.kloveroid.mvp.contracts

import com.cloverlab.kloveroid.mvp.presenters.BasePresenter
import com.cloverlab.kloveroid.mvp.presenters.IPresenter
import com.cloverlab.kloveroid.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author  Jieyi Wu
 * @since   2017/09/25
 */
interface MainContract {
    abstract class Presenter : BasePresenter<View>()

    interface View : IView
}