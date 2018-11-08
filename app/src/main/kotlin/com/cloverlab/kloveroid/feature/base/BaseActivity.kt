package com.cloverlab.kloveroid.feature.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.cloverlab.kloveroid.utilies.Navigator
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import dagger.internal.Preconditions
import javax.inject.Inject

/**
 * Base activity for collecting all common methods here.
 *
 * @author  Jieyi Wu
 * @since   2017/09/25
 */
abstract class BaseActivity : RxAppCompatActivity(),
                              HasFragmentInjector,
                              HasSupportFragmentInjector {
    /** For providing to support searchFragments. */
    @Inject lateinit var supportFragmentInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>
    /** For providing to searchFragments. */
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<android.app.Fragment>
    @Inject lateinit var navigator: Navigator
    protected var mvpOnCreate: (() -> Unit)? = null

    // Register it in the parent class that it will be not reflected.
    protected var busEvent = object {
//        @Subscribe(tags = [Tag(RxbusTag.NAVIGATOR)])
//        fun test(test: String) {
//            logw()
//        }
    }

    //region Activity lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        mvpOnCreate?.invoke()

        // Register RxBus.
//        RxBus.get().register(busEvent)
        init(savedInstanceState)
    }

    //endregion

    abstract fun init(savedInstanceState: Bundle?)

    @LayoutRes
    abstract fun provideLayoutId(): Int

    /**
     * Providing the fragment injector([Fragment]) for the searchFragments.
     *
     * @return a [supportFragmentInjector] for children of this fragment.
     */
    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> = supportFragmentInjector

    /**
     * Providing the fragment injector([android.app.Fragment]) for the searchFragments.
     *
     * @return a [fragmentInjector] for children of this fragment.
     */
    override fun fragmentInjector(): AndroidInjector<android.app.Fragment> = fragmentInjector

    /**
     * Adds a [Fragment] to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    fun addFragment(
        containerViewId: Int,
        fragment: androidx.fragment.app.Fragment,
        needBack: Boolean,
        sharedElement: View?,
        shareElementName: String?
    ) {
        Preconditions.checkNotNull(containerViewId)
        Preconditions.checkNotNull(fragment)
        Preconditions.checkNotNull(needBack)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerViewId, fragment, fragment.javaClass.name)

        if (null != sharedElement && null != shareElementName) {
            fragmentTransaction.addSharedElement(sharedElement, shareElementName)
        }

        // https://developer.android.com/training/implementing-navigation/temporal.html#back-fragments
        if (needBack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.name)
        }

        fragmentTransaction.commit()
    }
}
