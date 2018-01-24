package com.cloverlab.kloveroid.internal.di.modules

import com.cloverlab.kloveroid.internal.di.annotations.scopes.PerFragment
import com.cloverlab.kloveroid.internal.di.modules.dependency.fragment.FragmentMainModule
import com.cloverlab.kloveroid.ui.fragments.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * A base component upon which [Fragment] components may depend. Fragment-level components should extend this component.
 * Lifecycle is shorter than [PerActivity].
 *
 * @author  Jieyi Wu
 * @since   2017/09/28
 */
@Module
abstract class BindingFragmentModule {
    @PerFragment
    @ContributesAndroidInjector(modules = [FragmentMainModule::class])
    abstract fun contributeMainFragmentInjector(): MainFragment
}