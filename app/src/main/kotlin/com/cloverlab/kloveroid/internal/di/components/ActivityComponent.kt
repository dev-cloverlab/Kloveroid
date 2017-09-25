package com.cloverlab.kloveroid.internal.di.components

import com.cloverlab.kloveroid.internal.di.annotations.PerActivity
import com.cloverlab.kloveroid.internal.di.modules.ActivityModule
import dagger.Component

/**
 * @author Jieyi Wu
 * @since 09/25/17
 */
@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent
