package com.cloverlab.kloveroid.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.cloverlab.kloveroid.internal.di.modules.ActivityModule
import com.cloverlab.kloveroid.utilies.AppLog
import com.hwangjr.rxbus.RxBus
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import com.touchin.constant.RxbusTag
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
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
 * @author Jieyi Wu
 * @since 09/25/17
 */
open class BaseActivity: RxAppCompatActivity(), HasFragmentInjector, HasSupportFragmentInjector {
    /** For providing to support searchFragments. */
    @Inject lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
    /** For providing to searchFragments. */
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<android.app.Fragment>
    @Inject lateinit var navigator: Navigator

    // Register it in the parent class that it will be not reflected.
    protected var busEvent = object {
        @Subscribe(tags = arrayOf(Tag(RxbusTag.NAVIGATOR)))
        fun test(test: String) {
            AppLog.d(test)
        }
    }

    //region Activity lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        // Register RxBus.
        RxBus.get().register(busEvent)
    }

    override fun onDestroy() {
        super.onDestroy()

        // Unregister RxBus.
        RxBus.get().unregister(busEvent)
    }
    //endregion

    /**
     * Providing the fragment injector([Fragment]) for the searchFragments.
     *
     * @return a [supportFragmentInjector] for children of this fragment.
     */
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector

    /**
     * Providing the fragment injector([android.app.Fragment]) for the searchFragments.
     *
     * @return a [fragmentInjector] for children of this fragment.
     */
    override fun fragmentInjector(): AndroidInjector<android.app.Fragment> = fragmentInjector

    /**
     * Get an Activity module for dependency injection.
     *
     * @return [ActivityModule]
     */
    protected fun getActivityModule(): ActivityModule {
        return ActivityModule(this)
    }

    /**
     * Adds a [Fragment] to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    fun addFragment(containerViewId: Int, fragment: Fragment, needBack: Boolean, sharedElement: View?,
                    shareElementName: String?) {
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
