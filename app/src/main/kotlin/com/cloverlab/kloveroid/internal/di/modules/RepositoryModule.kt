package com.cloverlab.kloveroid.internal.di.modules

import android.content.Context
import com.cloverlab.kloveroid.internal.di.annotations.qualifiers.Local
import com.cloverlab.kloveroid.internal.di.annotations.qualifiers.Remote
import com.cloverlab.kloveroid.repository.repositories.IDataStore
import com.cloverlab.kloveroid.repository.repositories.source.CloudDataStore
import com.cloverlab.kloveroid.repository.repositories.source.LocalDataStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger module that provides the required objects of [taiwan.no1.app.ssfm.mvvm.models.data.repositories.DataRepository].
 *
 * @author  Jieyi Wu
 * @since   2017/09/28
 */
@Module
class RepositoryModule {
    @Provides
    @Singleton
    @Remote
    fun provideRemoteRepository(context: Context): IDataStore = CloudDataStore(context)

    @Provides
    @Singleton
    @Local
    fun provideLocalRepository(): IDataStore = LocalDataStore()
}