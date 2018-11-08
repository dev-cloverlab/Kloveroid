package com.cloverlab.kloveroid.feature.base

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.trello.rxlifecycle3.components.support.RxFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Base fragment for collecting common methods here.
 *
 * @author  Jieyi Wu
 * @since   2017/09/25
 */
abstract class BaseFragment : RxFragment(), HasSupportFragmentInjector {
    /** From an activity for providing to children searchFragments. */
    @Inject lateinit var childFragmentInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>
    protected val appContext: Context by lazy { activity!!.applicationContext }
    protected var rootView: View? = null

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Keep the instance data.
        retainInstance = true
        // FIXED: https://www.zybuluo.com/kimo/note/255244
        rootView ?: let { rootView = inflater.inflate(provideInflateView(), null) }
        val parent: ViewGroup? = rootView?.parent as ViewGroup?
        parent?.removeView(rootView)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(savedInstanceState)
    }
    //endregion

    /**
     * Providing the fragment injector([Fragment]) for this children of searchFragments.
     *
     * @return a [supportFragmentInjector] for children of this fragment.
     */
    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> = childFragmentInjector

    /**
     * Initialize method.
     *
     * @param savedInstanceState before status.
     */
    protected abstract fun init(savedInstanceState: Bundle?)

    /**
     * Set the view for inflating.
     *
     * @return [LayoutRes] layout xml.
     */
    @LayoutRes
    protected abstract fun provideInflateView(): Int
}
