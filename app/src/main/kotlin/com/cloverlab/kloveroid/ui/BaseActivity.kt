package com.cloverlab.kloveroid.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.cloverlab.kloveroid.App
import com.cloverlab.kloveroid.internal.di.components.AppComponent
import com.cloverlab.kloveroid.internal.di.modules.ActivityModule
import com.cloverlab.kloveroid.utilies.AppLog
import com.hwangjr.rxbus.RxBus
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import com.touchin.constant.RxbusTag
import dagger.internal.Preconditions
import javax.inject.Inject

/**
 * Base activity for collecting all common methods here.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
open class BaseActivity: AppCompatActivity() {
    @Inject
    lateinit var navigator: Navigator

    // Register it in the parent class that it will be not reflected.
    protected var busEvent = object {
        @Subscribe(tags = arrayOf(Tag(RxbusTag.NAVIGATOR)))
        fun test(test: String) {
            AppLog.d(test)
        }
    }

    //region Activity lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.initialInjector()

        // Register RxBus.
        RxBus.get().register(this.busEvent)
    }

    override fun onDestroy() {
        super.onDestroy()

        // Unregister RxBus.
        RxBus.get().unregister(this.busEvent)
    }
    //endregion

    /**
     * Get an injector and inject [BaseActivity].
     */
    fun initialInjector() {
        this.getApplicationComponent().inject(BaseActivity@ this)
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

        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
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

    /**
     * Get a fragment from queue by the tag.
     *
     * @param tag [Fragment]'s tag.
     * @return [Fragment]
     */
    fun findFragmentByTag(tag: String): Fragment {
        return this.supportFragmentManager.findFragmentByTag(tag)
    }

    /**
     * Pop a [Fragment] from [getSupportFragmentManager].
     */
    fun popFragment() {
        this.supportFragmentManager.popBackStack()
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return [AppComponent]
     */
    protected fun getApplicationComponent(): AppComponent {
        return App.appComponent(application)
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return [ActivityModule]
     */
    protected fun getActivityModule(): ActivityModule {
        return ActivityModule(this)
    }
}
