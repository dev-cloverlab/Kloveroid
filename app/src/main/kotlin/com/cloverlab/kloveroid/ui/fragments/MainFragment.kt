package com.cloverlab.kloveroid.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cloverlab.kloveroid.R
import com.cloverlab.kloveroid.mvp.contracts.MainContract
import com.cloverlab.kloveroid.ui.BaseFragment
import com.hwangjr.rxbus.RxBus
import dagger.internal.Preconditions
import kotlinx.android.synthetic.main.fragment_main.btn_test
import kotlinx.android.synthetic.main.fragment_main.tv_show
import javax.inject.Inject

/**
 * @author Jieyi Wu
 * @since 09/25/17
 */
class MainFragment: BaseFragment<MainContract.Presenter>(), MainContract.View {
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private val ARG_PARAM_: String = "param_"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of fragment BlankFragment.
         */
        fun newInstance(arg1: String): MainFragment {
            val fragment = MainFragment()
            val bundle = Bundle()
            bundle.putString(ARG_PARAM_, arg1)
            fragment.arguments = bundle

            return fragment
        }
    }

    @Inject override lateinit var presenter: MainContract.Presenter
    // The fragment initialization parameters.
    private var arg1: String? = null

    //region Fragment lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the arguments from the bundle here.
        arg1 = arguments?.getString(MainFragment.ARG_PARAM_)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        presenter.view = this

        return rootView
    }
    //endregion

    //region Base fragment
    override fun init(savedInstanceState: Bundle?) {
        btn_test.setOnClickListener { RxBus.get().post("test") }
        tv_show.text = "Hello World!!"
    }

    override fun provideInflateView(): Int = R.layout.fragment_main
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
