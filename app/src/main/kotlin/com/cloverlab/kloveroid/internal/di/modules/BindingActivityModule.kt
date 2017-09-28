package com.cloverlab.kloveroid.internal.di.modules

import com.cloverlab.kloveroid.internal.di.annotations.scopes.PerActivity
import com.cloverlab.kloveroid.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Dagger module that provides each of [Activity] in this central module during the activity lifecycle.
 *
 * @author  jieyi
 * @since   9/28/17
 */
@Module
abstract class BindingActivityModule {
    /**
     * ContributesAndroidInjector is including fragment injector. Only putting FragmentBindModule in modules array,
     * the children fragment can obtain the parent's fragment injector.
     */
    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(BindingFragmentModule::class))
    abstract fun contributeMainActivityInjector(): MainActivity
}