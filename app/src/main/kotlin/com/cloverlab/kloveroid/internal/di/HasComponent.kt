package com.cloverlab.kloveroid.internal.di

/**
 * Interface representing a contract for clients that contains a component for dependency injection.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */

interface HasComponent<out C> {
    fun getComponent(obj: Any?): C
}