package com.cloverlab.kloveroid.internal.di.modules

import android.app.Activity
import com.cloverlab.kloveroid.internal.di.annotations.PerActivity
import com.cloverlab.kloveroid.mvp.contracts.MainContract
import com.cloverlab.kloveroid.mvp.presenters.MainPresenter
import dagger.Module
import dagger.Provides

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
@Module
class ActivityModule(var activity: Activity) {
    @Provides
    @PerActivity
    fun activity(): Activity = this.activity

    @Provides
    @PerActivity
    fun provideMainPresenter(mainPresenter: MainPresenter): MainContract.Presenter = mainPresenter
}