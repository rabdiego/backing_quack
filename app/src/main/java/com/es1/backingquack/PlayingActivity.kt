package com.es1.backingquack

import android.os.Bundle
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
    private lateinit var toggleButton: ToggleButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_playing)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        midiPlayer = MidiPlayer(this)
        toggleButton = findViewById(R.id.play_pause_bttn)


        val backingTrackData: BackingTrackData? = intent.getParcelableExtra("BACKING_TRACK_DATA")
        if (backingTrackData != null) {

            val chordList = backingTrackData.chords.split("-")
            val request = RequestData(
                chordProgressionList = chordList,
                root = backingTrackData.tom,
                bpm = backingTrackData.bpm
            )
            val midiDownloader = MidiDownloader(this)

            midiDownloader.downloadMidi(
                request,
                { midiFile ->
                    midiPlayer.play(midiFile, loop = true)
                    playPauseButton(midiFile)
                },
                { throwable -> throwable.printStackTrace() })


        }

    }

    private fun playPauseButton(backingTrack: File) {
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                midiPlayer.pause()
            } else {
                midiPlayer.play(backingTrack, loop = true)
            }
        }
    }
}