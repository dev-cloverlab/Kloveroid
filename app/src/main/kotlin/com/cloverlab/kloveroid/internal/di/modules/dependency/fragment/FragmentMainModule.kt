package com.cloverlab.kloveroid.internal.di.modules.dependency.fragment

import com.cloverlab.kloveroid.data.repositories.DataRepository
import com.cloverlab.kloveroid.domain.CreateFakeUseCase
import com.cloverlab.kloveroid.domain.executor.PostExecutionThread
import com.cloverlab.kloveroid.domain.executor.ThreadExecutor
import com.cloverlab.kloveroid.internal.di.annotations.scopes.PerFragment
import com.cloverlab.kloveroid.mvp.contracts.MainContract
import com.cloverlab.kloveroid.mvp.presenters.MainPresenter
import dagger.Module
import dagger.Provides

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
@Module
class FragmentMainModule {
    @Provides
    @PerFragment
    fun provideMainPresenter(usecase: CreateFakeUseCase): MainContract.Presenter = MainPresenter(usecase)

    @Provides
    @PerFragment
    fun provideUsecase(threadExecutor: ThreadExecutor,
                       postExecutionThread: PostExecutionThread,
                       repository: DataRepository): CreateFakeUseCase =
        CreateFakeUseCase(threadExecutor, postExecutionThread, repository)
}