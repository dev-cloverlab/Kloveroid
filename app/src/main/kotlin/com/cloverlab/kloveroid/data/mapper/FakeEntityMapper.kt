package com.cloverlab.kloveroid.data.mapper

import com.cloverlab.kloveroid.data.entities.FakeEntity
import com.cloverlab.kloveroid.domain.mapper.IEntityMapper
import com.cloverlab.kloveroid.mvp.models.FakeModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Mapper class used to transform between [FakeModel] (in the kotlin layer) and [FakeEntity]
 * (in the data layer).
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
@Singleton
class FakeEntityMapper @Inject internal constructor(): IEntityMapper<FakeModel, FakeEntity> {
    override fun transformFrom(model: FakeModel): FakeEntity {
        return FakeEntity(model.name, model.age, model.sex)
    }

    override fun transformTo(entity: FakeEntity): FakeModel {
        return FakeModel(entity.name!!, entity.age, entity.sex!!)
    }
}
