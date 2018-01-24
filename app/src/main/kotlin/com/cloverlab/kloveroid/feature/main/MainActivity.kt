package com.cloverlab.kloveroid.feature.main

import android.os.Bundle
import android.support.v4.app.Fragment
import com.cloverlab.kloveroid.R
import com.cloverlab.kloveroid.ui.BaseActivity
import com.cloverlab.kloveroid.ui.fragments.main.MainFragment

/**
 * @author  Jieyi Wu
 * @since   2017/09/25
 */
class MainActivity : BaseActivity() {
    override fun init(savedInstanceState: Bundle?) {
        //apply background bitmap if we have one
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, getFragment(), false, null, null)
        }
    }

    override fun provideLayoutId(): Int = R.layout.activity_main

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
