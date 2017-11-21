package com.cloverlab.kloveroid.repository.source

import android.content.Context
import com.cloverlab.kloveroid.internal.di.components.NetComponent
import com.cloverlab.kloveroid.mvp.models.FakeModel
import com.cloverlab.kloveroid.repository.api.service.FakeService
import dagger.internal.Preconditions
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

/**
 * @author Jieyi Wu
 * @since 09/25/17
 */
class CloudDataStore constructor(private val context: Context) : IDataStore {
    @field:[Inject Named("FakeHttp")] lateinit var retrofit: FakeService

    init {
        NetComponent.Initializer.init().inject(this@CloudDataStore)
    }

    override fun createEntity(model: FakeModel): Observable<FakeModel> {
        Preconditions.checkNotNull(model)

        return Observable.create { emitter ->
            emitter.onNext(FakeModel("Test", 100, "F"))
            emitter.onComplete()
        }
    }
}
