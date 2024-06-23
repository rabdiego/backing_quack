package com.es1.core.model

data class RequestData(
    val chordProgressionList: List<String>,
    val root: String,
    val bpm: Int
)
