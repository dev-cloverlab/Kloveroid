package com.cloverlab.kloveroid.ui

import android.os.Bundle
import android.view.View
import com.cloverlab.kloveroid.internal.di.HasComponent
import com.trello.rxlifecycle.components.support.RxFragment

/**
 * Base fragment for collecting common methods here.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
abstract class BaseFragment: RxFragment() {
    protected var rootView: View? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.init()
    }

    /**
     * Get a use case component from a owner activity.
     *
     * @param componentType owner [BaseActivity] class name.
     * @param obj a use case parameter.
     * @return [UseCaseComponent].
     */
    protected fun <C> getComponent(componentType: Class<C>, obj: Any?): C {
        return componentType.cast((activity as HasComponent<*>).getComponent(obj))
    }

    /**
     * Initialize method.
     */
    abstract protected fun init()
}