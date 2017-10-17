package com.cloverlab.kloveroid.ui

import android.os.Bundle
import com.cloverlab.kloveroid.mvp.presenters.BasePresenter
import com.cloverlab.kloveroid.mvp.views.IView

/**
 * MVP activity for providing the advanced pre setting.
 *
 * @author Jieyi Wu
 * @since 09/28/17
 */
abstract class MvpActivity<V: IView, P: BasePresenter<V>>: BaseActivity() {
    abstract var presenter: P

    //region Activity lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.view = provideCurrentActivityView()
        presenter.create(this)
    }

    override fun onContentChanged() {
        super.onContentChanged()
        presenter.init()
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun onResume() {
        super.onResume()
        presenter.resume()
    }

    override fun onPause() {
        super.onPause()
        presenter.pause()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun onDestroy() {
        // After super.onDestroy() is executed, the presenter will be destroy. So the presenter should be
        // executed before super.onDestroy().
        presenter.destroy()
        super.onDestroy()
    }
    //endregion

    abstract fun provideCurrentActivityView(): V
}