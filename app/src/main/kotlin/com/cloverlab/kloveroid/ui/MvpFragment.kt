package com.cloverlab.kloveroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cloverlab.kloveroid.mvp.presenters.BasePresenter
import com.cloverlab.kloveroid.mvp.views.IView

/**
 * Base fragment for providing the advanced pre setting.
 *
 * @author Jieyi Wu
 * @since 09/28/17
 */
abstract class MvpFragment<V : IView, P : BasePresenter<V>> : BaseFragment() {
    abstract var presenter: P

    //region Fragment lifecycle
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.create(this)
        presenter.view = provideCurrentFragmentView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun onPause() {
        super.onPause()
        presenter.pause()
    }

    override fun onDestroy() {
        // After super.onDestroy() is executed, the presenter will be destroy. So the presenter should be
        // executed before super.onDestroy().
        presenter.resume()
        super.onDestroy()
    }
    //endregion

    abstract fun provideCurrentFragmentView(): V
}