package com.cloverlab.kloveroid.ui

import com.cloverlab.kloveroid.mvp.presenters.IPresenter

/**
 * Base activity for collecting all common methods here.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
abstract class MvpActivity<P: IPresenter>: BaseActivity() {
    abstract var presenter: P
}