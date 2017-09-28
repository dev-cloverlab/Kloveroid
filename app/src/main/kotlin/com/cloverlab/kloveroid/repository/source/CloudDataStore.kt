package com.cloverlab.kloveroid.repository.source

import android.content.Context
import com.cloverlab.kloveroid.internal.di.components.NetComponent
import com.cloverlab.kloveroid.mvp.models.FakeModel
import dagger.internal.Preconditions
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

/**
 * @author Jieyi Wu
 * @since 09/25/17
 */
class CloudDataStore constructor(private val context: Context): IDataStore {
    @field:[Inject Named("FakeHttp")] lateinit var retrofit: Retrofit

    init {
        NetComponent.Initializer.init().inject(this@CloudDataStore)
    }

    override fun createEntity(model: FakeModel): Observable<FakeModel> {
        Preconditions.checkNotNull(model)

        return Observable.create { subscriber ->
            subscriber.onNext(FakeModel("Test", 100, "F"))
            subscriber.onCompleted()
        }
    }
}