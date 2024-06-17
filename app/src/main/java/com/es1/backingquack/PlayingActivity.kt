package com.es1.backingquack

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.es1.core.BackingTrack
import com.es1.core.BackingTrackData

class PlayingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_playing)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backingTrackData: BackingTrackData? = intent.getParcelableExtra("BACKING_TRACK_DATA")

//        if (backingTrackData != null) {
//            val chord = backingTrackData.chords
//            val bpm = backingTrackData.bpm
//            val tom = backingTrackData.tom
//
//            val chordList = chord.split("-")
//
//            val backingTrack = BackingTrack(chordList,tom, bpm, this)
//            backingTrack.build()
//
//        }

    }
}