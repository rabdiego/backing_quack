package com.es1.core.service;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import com.es1.core.model.BackingTrackData
import com.es1.core.model.RequestData
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.File;
import java.io.FileOutputStream;

class MidiDownloader(private val context: Context) {
    private val apiService: ApiService
    private val url = "http://10.0.2.2:8080/api/generate/"

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }


    fun downloadMidi(
        request: RequestData,
        onSuccess: (File) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        apiService.generateMidi(request).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    response.body()?.let { responseBody ->
                        val midiFile = File(context.getExternalFilesDir(null), "output.mid")
                        val fos = FileOutputStream(midiFile)
                        fos.write(responseBody.bytes())
                        fos.close()
                        onSuccess(midiFile)

                    }
                } else {
                    onFailure(Exception("Failed to download MIDI file: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                onFailure(t)
            }
        })


    }

    fun playMidiFile(file: File) {
        val mediaPlayer = MediaPlayer().apply {
            setDataSource(file.absolutePath)
            prepare()
            start()
        }

        mediaPlayer.setOnCompletionListener {
            it.release()
        }
    }
}