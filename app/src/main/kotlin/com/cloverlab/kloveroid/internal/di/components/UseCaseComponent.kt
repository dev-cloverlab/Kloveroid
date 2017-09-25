package com.cloverlab.kloveroid.internal.di.components

import android.support.v7.app.AppCompatActivity
import com.cloverlab.kloveroid.internal.di.annotations.PerActivity
import com.cloverlab.kloveroid.internal.di.modules.ActivityModule
import com.cloverlab.kloveroid.internal.di.modules.UseCaseModule
import com.cloverlab.kloveroid.ui.fragments.MainFragment
import dagger.Component

/**
 * @author Jieyi Wu
 * @since 09/25/17
 */
@PerActivity
@Component(dependencies = arrayOf(AppComponent::class),
    modules = arrayOf(ActivityModule::class, UseCaseModule::class))
interface UseCaseComponent: ActivityComponent {
    object Initializer {
        fun init(appComponent: AppComponent,
                 activity: AppCompatActivity,
                 obj: Any? = null): UseCaseComponent = DaggerUseCaseComponent.builder()
            .appComponent(appComponent)
            .useCaseModule(if (null == obj) UseCaseModule() else UseCaseModule(obj as String))
            .activityModule(ActivityModule(activity))
            .build()
    }

    /**
     * After injected a fragment, the presenter of the fragment should be provided in [ActivityModule].
     */

    fun inject(mainFragment: MainFragment)
}