package com.es1.backingquack

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.es1.core.model.BackingTrackData
import com.es1.core.model.RequestData
import com.es1.core.service.MidiPlayer
import com.es1.core.service.MidiDownloader
import java.io.File

class PlayingActivity : AppCompatActivity() {

    private lateinit var midiPlayer: MidiPlayer
    private lateinit var midiDownloader: MidiDownloader
    private lateinit var buttonPlayPause: ImageButton
    private lateinit var buttonStop: ImageButton
    private var midiFile: File? = null
    private var isPlaying: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_playing)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buttonPlayPause = findViewById(R.id.ibtn_play_pause)
        buttonStop = findViewById(R.id.ibtn_stop)

        midiPlayer = MidiPlayer(this)
        midiDownloader = MidiDownloader(this)


        val backingTrackData: BackingTrackData? = intent.getParcelableExtra("BACKING_TRACK_DATA")
        if (backingTrackData != null) {

            val chordList = backingTrackData.chords.split("-")
            val request = RequestData(
                chordProgressionList = chordList,
                root = backingTrackData.tom,
                bpm = backingTrackData.bpm
            )

            midiDownloader.downloadMidi(
                request,
                { file ->
                    midiFile = file
                    midiPlayer.init(file)
                },
                { throwable -> throwable.printStackTrace() })
        }

        buttonPlayPause.setOnClickListener {
            if (isPlaying) {
                midiPlayer.pause()
                updatePlayPauseButtonIcon(false)
            } else {
                midiPlayer.play()
                updatePlayPauseButtonIcon(true)
            }
            isPlaying = !isPlaying
        }

        buttonStop.setOnClickListener {
            midiPlayer.stop()
            midiFile?.let { midiPlayer.init(it) }
            updatePlayPauseButtonIcon(false)
            isPlaying = false
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

    }

    private fun updatePlayPauseButtonIcon(isPlaying: Boolean) {
        val icon = if (isPlaying) R.drawable.pause_icon else R.drawable.play_icon
        buttonPlayPause.setImageResource(icon)
    }

    override fun onDestroy() {
        super.onDestroy()
        midiPlayer.release()
    }

}