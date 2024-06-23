package com.es1.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class BackingTrackData(
    val chords: String,
    val tom: String,
    val bpm: Int
) : Parcelable