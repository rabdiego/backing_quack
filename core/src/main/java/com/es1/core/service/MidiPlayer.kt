package com.es1.core.service

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import java.io.File

class MidiPlayer(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null
    private var isLooping: Boolean = false

    fun play(file: File, loop: Boolean = false) {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(file.absolutePath)
            prepare()
            start()
            isLooping = loop
        }

        mediaPlayer?.setOnCompletionListener {
            if (isLooping) {
                it.start()
            } else {
                it.release()
                mediaPlayer = null
            }
        }
    }

    fun pause() {
        mediaPlayer?.pause()
    }

    fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}