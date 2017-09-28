package com.cloverlab.kloveroid.internal.di.modules

import android.content.Context
import com.cloverlab.kloveroid.data.source.CloudDataStore
import com.cloverlab.kloveroid.data.source.IDataStore
import com.cloverlab.kloveroid.data.source.LocalDataStore
import com.cloverlab.kloveroid.internal.di.annotations.qualifiers.Local
import com.cloverlab.kloveroid.internal.di.annotations.qualifiers.Remote
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger module that provides the required objects of [taiwan.no1.app.ssfm.mvvm.models.data.repositories.DataRepository].
 *
 * @author  jieyi
 * @since   9/28/17
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