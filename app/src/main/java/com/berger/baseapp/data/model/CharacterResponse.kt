package com.berger.baseapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by merttoptas on 10.03.2022
 */

@Parcelize
data class CharacterResponse(
    val info: InfoResponse,
    val results: List<Result>
) : Parcelable