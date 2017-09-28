package com.cloverlab.kloveroid.ui

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cloverlab.kloveroid.mvp.presenters.IPresenter
import com.cloverlab.kloveroid.mvp.views.IView
import com.trello.rxlifecycle2.components.support.RxFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Base fragment for collecting common methods here.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
abstract class BaseFragment<in V: IView, P: IPresenter<V>>: RxFragment(), HasSupportFragmentInjector {
    /** From an activity for providing to children searchFragments. */
    @Inject lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>
    protected val appContext: Context by lazy { activity.applicationContext }
    protected var rootView: View? = null
    abstract var presenter: P

    //region Fragment lifecycle
    /** Perform injection here before M, L (API 22) and below because this is not yet available at L. */
    @SuppressWarnings("deprecation")
    override fun onAttach(activity: Activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            AndroidSupportInjection.inject(this)
        }
        super.onAttach(activity)
    }

    /** Perform injection here for M (API 23) due to deprecation of onAttach(Activity). */
    override fun onAttach(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AndroidSupportInjection.inject(this)
        }
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Keep the instance data.
        retainInstance = true
        // FIXED: https://www.zybuluo.com/kimo/note/255244
        rootView ?: let { rootView = inflater.inflate(provideInflateView(), null) }
        val parent: ViewGroup? = rootView?.parent as ViewGroup?
        parent?.removeView(rootView)

        presenter.init()

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(savedInstanceState)
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        presenter.resume()
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        presenter.pause()
    }

    @CallSuper
    override fun onDestroy() {
        // After super.onDestroy() is executed, the presenter will be destroy. So the presenter should be
        // executed before super.onDestroy().
        presenter.resume()
        super.onDestroy()
    }
    //endregion

    /**
     * Providing the fragment injector([Fragment]) for this children of searchFragments.
     *
     * @return a [supportFragmentInjector] for children of this fragment.
     */
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = childFragmentInjector

    /**
     * Initialize method.
     *
     * @param savedInstanceState before status.
     */
    abstract protected fun init(savedInstanceState: Bundle?)

    /**
     * Set the view for inflating.
     *
     * @return [LayoutRes] layout xml.
     */
    @LayoutRes
    abstract protected fun provideInflateView(): Int
}