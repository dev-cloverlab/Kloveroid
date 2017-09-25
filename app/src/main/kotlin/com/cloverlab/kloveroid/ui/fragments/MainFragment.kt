package com.cloverlab.kloveroid.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.cloverlab.kloveroid.R
import com.cloverlab.kloveroid.internal.di.annotations.PerActivity
import com.cloverlab.kloveroid.internal.di.components.UseCaseComponent
import com.cloverlab.kloveroid.mvp.contracts.MainContract
import com.cloverlab.kloveroid.mvp.presenters.MainPresenter
import com.cloverlab.kloveroid.ui.BaseFragment
import com.hwangjr.rxbus.RxBus
import com.trello.rxlifecycle.android.FragmentEvent
import dagger.internal.Preconditions
import rx.Observable
import javax.inject.Inject

/**
 * @author Jieyi Wu
 * @since 09/25/17
 */

@PerActivity
class MainFragment: BaseFragment(), MainContract.View {
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private val ARG_PARAM_: String = "param_"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of fragment BlankFragment.
         */
        fun newInstance(arg1: String): MainFragment {
            val fragment: MainFragment = MainFragment()
            val bundle: Bundle = Bundle()
            bundle.putString(ARG_PARAM_, arg1)
            fragment.arguments = bundle

            return fragment
        }
    }

    @Inject
    lateinit var presenter: MainPresenter

    private val tvShow by bindView<TextView>(R.id.tv_show)
    private val btnTest by bindView<Button>(R.id.btn_test)

    // The fragment initialization parameters.
    private var arg1: String? = null

    //region Fragment lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the arguments from the bundle here.
        this.arg1 = arguments?.getString(MainFragment.ARG_PARAM_)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Avoid that an activity is deleted and get null pointer so inject the component here.
        this.getComponent(UseCaseComponent::class.java, null).inject(MainFragment@ this)
        // Keep the instance data.
        this.retainInstance = true

        // FIXED: https://www.zybuluo.com/kimo/note/255244
        if (null == rootView)
            rootView = inflater.inflate(R.layout.fragment_main, null)
        val parent: ViewGroup? = rootView?.parent as ViewGroup?
        parent?.removeView(rootView)

        this.presenter.setView(MainFragment@ this)
        this.presenter.init()

        return rootView
    }

    override fun onResume() {
        super.onResume()
        this.presenter.resume()
    }

    override fun onPause() {
        super.onPause()
        this.presenter.pause()
    }

    override fun onDestroy() {
        // After super.onDestroy() is executed, the presenter will be destroy. So the presenter should be
        // executed before super.onDestroy().
        this.presenter.destroy()
        super.onDestroy()
    }
    //endregion

    override fun init() {
        this.btnTest.setOnClickListener { RxBus.get().post("test") }

        this.tvShow.text = "Hello World!!"
    }

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

    override fun context(): Context = this.activity.applicationContext

    override fun fragmentLifecycle(): Observable<FragmentEvent> = this.lifecycle()
    //endregion
}
