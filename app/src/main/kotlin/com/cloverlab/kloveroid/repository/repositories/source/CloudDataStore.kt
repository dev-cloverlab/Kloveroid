package com.cloverlab.kloveroid.repository.repositories.source

import android.content.Context
import com.cloverlab.kloveroid.entities.FakeEntity
import com.cloverlab.kloveroid.internal.di.components.NetComponent
import com.cloverlab.kloveroid.repository.remote.service.FakeService
import com.cloverlab.kloveroid.repository.repositories.IDataStore
import dagger.internal.Preconditions
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

/**
 * @author  Jieyi Wu
 * @since   2017/09/25
 */
class CloudDataStore constructor(private val context: Context) : IDataStore {
    @field:[Inject Named("FakeHttp")] lateinit var retrofit: FakeService

    init {
        NetComponent.Initializer.init().inject(this@CloudDataStore)
    }

    override fun createEntity(entity: FakeEntity): Observable<FakeEntity> {
        Preconditions.checkNotNull(entity)

        return Observable.create { emitter ->
            emitter.onNext(FakeEntity("Test", 100, "F"))
            emitter.onComplete()
        }
    }
}
