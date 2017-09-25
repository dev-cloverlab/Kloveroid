package com.cloverlab.kloveroid.mvp.contracts

import com.cloverlab.kloveroid.mvp.presenters.IPresenter
import com.cloverlab.kloveroid.mvp.views.IFragmentView
import com.cloverlab.kloveroid.mvp.views.IView

/**
 * This specifies the contract between the [IPresenter] and the [IView].
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
interface MainContract {
    interface Presenter: IPresenter<View>

    interface View: IView, IFragmentView
}