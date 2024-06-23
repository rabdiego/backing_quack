package com.es1.core.service

import com.es1.core.model.BackingTrackData
import com.es1.core.model.RequestData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/generate/")
    fun generateMidi(@Body request: RequestData): Call<ResponseBody>
}