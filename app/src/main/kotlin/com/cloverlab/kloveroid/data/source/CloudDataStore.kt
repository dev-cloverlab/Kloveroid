package com.cloverlab.kloveroid.data.source

import com.cloverlab.kloveroid.data.entities.FakeEntity
import com.cloverlab.kloveroid.internal.di.components.NetComponent
import com.cloverlab.kloveroid.mvp.models.FakeModel
import dagger.internal.Preconditions
import retrofit2.Retrofit
import rx.Observable
import javax.inject.Inject
import javax.inject.Named

/**
 * @author Jieyi Wu
 * @since 09/25/17
 */

class CloudDataStore @Inject constructor(): IDataStore {
    @Inject
    @Named("FakeHttp") internal var retrofit: Retrofit? = null

    init {
        NetComponent.Initializer.init().inject(this@CloudDataStore)
    }

    override fun createEntity(model: FakeModel): Observable<FakeEntity> {
        Preconditions.checkNotNull(model)

        return Observable.create { subscriber ->
            subscriber.onNext(FakeEntity("Test", 100, "F"))
            subscriber.onCompleted()
        }
    }
}
