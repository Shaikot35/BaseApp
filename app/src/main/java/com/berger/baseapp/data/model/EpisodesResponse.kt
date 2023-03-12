package com.berger.baseapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodesResponse(
    val info: InfoResponse,
    val results: List<EpisodesResultResponse>
) : Parcelable
