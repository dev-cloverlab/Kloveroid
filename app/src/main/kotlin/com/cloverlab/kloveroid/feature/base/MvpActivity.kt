package com.cloverlab.kloveroid.feature.base

import android.os.Bundle
import com.cloverlab.kloveroid.mvp.presenters.BasePresenter
import com.cloverlab.kloveroid.mvp.views.IView

/**
 * MVP activity for providing the advanced pre setting.
 *
 * @author  Jieyi Wu
 * @since   2017/09/28
 */
abstract class MvpActivity<V : IView, P : BasePresenter<V>> : BaseActivity() {
    abstract var presenter: P

    //region Activity lifecycle
    override fun onContentChanged() {
        super.onContentChanged()
        presenter.create(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mvpOnCreate = {
            presenter.view = provideCurrentActivityView()
            presenter.init()
        }
        super.onCreate(savedInstanceState)
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