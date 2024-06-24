package com.es1.core.service

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import java.io.File

class MidiPlayer(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null
    private var isLooping: Boolean = false


    fun init(file: File) {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(file.absolutePath)
            isLooping = true
            prepare()
        }
    }
    fun play() {
       mediaPlayer?.start()
    }

    fun pause() {
        mediaPlayer?.pause()
    }

    fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.reset()
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
    fun isPlaying(): Boolean {
        return mediaPlayer?.isPlaying ?: false
    }

    fun isInitialized(): Boolean {
        return mediaPlayer != null
    }
}