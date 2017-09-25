package com.cloverlab.kloveroid.ui.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import com.cloverlab.kloveroid.R
import com.cloverlab.kloveroid.internal.di.HasComponent
import com.cloverlab.kloveroid.internal.di.components.UseCaseComponent
import com.cloverlab.kloveroid.ui.BaseActivity
import com.cloverlab.kloveroid.ui.fragments.MainFragment

/**
 * @author Jieyi Wu
 * @since 09/25/17
 */
class MainActivity: BaseActivity(), HasComponent<UseCaseComponent> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment(savedInstanceState)
    }

    override fun getComponent(obj: Any?): UseCaseComponent =
        UseCaseComponent.Initializer.init(getApplicationComponent(), this)

    fun initFragment(savedInstanceState: Bundle?) {
        //apply background bitmap if we have one

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, getFragment(), false, null, null)
        }
    }

    fun getFragment(): Fragment {
        return when (-1) {
            R.layout.fragment_main -> {
                val fragment = MainFragment.newInstance("")
                fragment
            }
            else -> MainFragment.newInstance("")
        }
    }
}
