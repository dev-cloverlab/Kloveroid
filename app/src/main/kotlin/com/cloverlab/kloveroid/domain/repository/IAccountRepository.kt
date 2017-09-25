package com.cloverlab.kloveroid.domain.repository

import com.cloverlab.kloveroid.mvp.models.FakeModel
import rx.Observable

/**
 * Interface that represents a Repository for getting [FakeModel] related data.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */

interface IAccountRepository {
    /**
     * Get an [Observable] which will emit a [FakeModel].
     */
    fun CreateFakes(fakeModel: FakeModel): Observable<FakeModel>
}
