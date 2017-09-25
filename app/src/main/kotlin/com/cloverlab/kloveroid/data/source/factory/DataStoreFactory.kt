package com.cloverlab.kloveroid.data.source.factory

import android.content.Context
import com.cloverlab.kloveroid.data.source.CloudDataStore
import com.cloverlab.kloveroid.data.source.IDataStore
import com.cloverlab.kloveroid.data.source.LocalDataStore
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Factory that creates different implementations of [IDataStore].
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */

@Singleton
class DataStoreFactory @Inject internal constructor(private val context: Context) {
    fun createLocal(): IDataStore {
        return LocalDataStore()
    }

    fun createCloud(): IDataStore {
        return CloudDataStore()
    }
}
