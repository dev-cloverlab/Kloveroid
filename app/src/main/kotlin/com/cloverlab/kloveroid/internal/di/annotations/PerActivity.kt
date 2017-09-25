package com.cloverlab.kloveroid.internal.di.annotations

import javax.inject.Scope

/**
 * A scoping annotation to permit objects whose lifetime should conform to the life of the activity to be
 * memorized in the correct component.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
@Scope
@Retention
annotation class PerActivity