package com.cloverlab.kloveroid.feature.main

import android.os.Bundle
import com.cloverlab.kloveroid.R
import com.cloverlab.kloveroid.feature.base.MvpFragment
import com.cloverlab.kloveroid.mvp.contracts.MainContract.Presenter
import com.cloverlab.kloveroid.mvp.contracts.MainContract.View
import dagger.internal.Preconditions
import kotlinx.android.synthetic.main.fragment_main.tv_show
import javax.inject.Inject

/**
 * @author  Jieyi Wu
 * @since   2017/09/25
 */
class MainFragment : MvpFragment<View, Presenter>(), View {
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private val ARG_PARAM_: String = "param_"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of fragment BlankFragment.
         */
        fun newInstance(arg1: String) = MainFragment().apply {
            arguments = bundleOf(arrayOf<Pair<String, Any?>>(Pair(ARG_PARAM_, arg1)))
        }
    }

    @Inject override lateinit var presenter: Presenter
    // The fragment initialization parameters.
    private val arg1 by lazy { arguments?.getString(ARG_PARAM_).orEmpty() }

    //endregion

    //region Base fragment
    override fun init(savedInstanceState: Bundle?) {
//        btn_test.setOnClickListener { RxBus.get().post("test") }
        tv_show.text = "Hello World!!"
    }

    override fun provideInflateView() = R.layout.fragment_main

    override fun provideCurrentFragmentView() = this
    //endregion

    //region Presenter implements
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showRetry() {
    }

    override fun hideRetry() {
    }

    override fun showError(message: String) {
        Preconditions.checkNotNull(message)
    }
    //endregion
}
