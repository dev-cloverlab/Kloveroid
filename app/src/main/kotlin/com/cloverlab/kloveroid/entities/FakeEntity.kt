package com.cloverlab.kloveroid.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *
 * @author  Jieyi Wu
 * @since   2017/09/25
 */
@Parcelize
data class FakeEntity(
    val name: String,
    val age: Int,
    val sex: String
) : Parcelable
