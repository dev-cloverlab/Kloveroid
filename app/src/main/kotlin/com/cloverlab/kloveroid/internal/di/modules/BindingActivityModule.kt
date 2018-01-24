package com.cloverlab.kloveroid.internal.di.modules

import com.cloverlab.kloveroid.internal.di.annotations.scopes.PerActivity
import com.cloverlab.kloveroid.feature.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Dagger module that provides each of [Activity] in this central module during the activity lifecycle.
 *
 * @author  Jieyi Wu
 * @since   2017/09/28
 */
@Module
abstract class BindingActivityModule {
    /**
     * ContributesAndroidInjector is including fragment injector. Only putting FragmentBindModule in modules array,
     * the children fragment can obtain the parent's fragment injector.
     */
    @PerActivity
    @ContributesAndroidInjector(modules = [BindingFragmentModule::class])
    abstract fun contributeMainActivityInjector(): MainActivity
}