package com.cloverlab.kloveroid.internal.di.annotations.scopes

import javax.inject.Scope

/**
 * A scoping annotation to permit objects whose lifetime should conform to the life of the activity to be
 * memorized in the correct component.
 *
 * @author  Jieyi Wu
 * @since   2017/06/08
 */
@Scope
@Retention
@MustBeDocumented
annotation class PerActivity