package com.cloverlab.kloveroid.internal.di.modules

import android.app.Application
import android.content.Context
import com.cloverlab.kloveroid.App
import com.cloverlab.kloveroid.data.executor.JobExecutor
import com.cloverlab.kloveroid.data.repositiry.AccountDataRepository
import com.cloverlab.kloveroid.domain.executor.PostExecutionThread
import com.cloverlab.kloveroid.domain.executor.ThreadExecutor
import com.cloverlab.kloveroid.domain.repository.IAccountRepository
import com.cloverlab.kloveroid.utilies.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideApplication(app: App): Application = app

    @Provides
    @Singleton
    fun provideAppContext(app: App): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideAccountRepository(accountDataRepository: AccountDataRepository): IAccountRepository =
        accountDataRepository

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread = uiThread

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor
}
