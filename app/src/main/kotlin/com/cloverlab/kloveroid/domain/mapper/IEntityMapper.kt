package com.cloverlab.kloveroid.domain.mapper

/**
 * Interface that transform between data layer and kotlin layer.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
interface IEntityMapper<M, E> {
    /**
     * Interaction of transforming from kotlin layer [M] model data to data layer [E] entity data.
     *
     * @param model kotlin layer model data.
     * @return data layer entity data.
     */
    fun transformFrom(model: M): E

    /**
     * Interaction of transforming from data layer [E] entity data to kotlin layer [M] model data.
     *
     * @param entity data layer entity data.
     * @return kotlin layer model data.
     */
    fun transformTo(entity: E): M
}
