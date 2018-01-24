package com.cloverlab.kloveroid.internal.di.annotations.scopes

import javax.inject.Scope

/**
 * A scoping annotation to permit objects whose lifetime should depend to the life of the application to be
 * memorized in the correct component.
 *
 * @author  Jieyi Wu
 * @since   2017/08/16
 */
@Scope
@Retention
@MustBeDocumented
annotation class LocalData