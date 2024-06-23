package com.es1.backingquack

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.es1.core.model.BackingTrackData

class TonsActivity : AppCompatActivity() {
    private var backingTrackData: BackingTrackData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tons)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        backingTrackData = intent.getParcelableExtra("BACKING_TRACK_DATA")



        val tonsButtons = mapOf(
            R.id.iv_tom1_c to "C",
            R.id.iv_tom2_csus to "C#",
            R.id.iv_tom3_d to "D",
            R.id.iv_tom4_dsus to "D#",
            R.id.iv_tom5_mie to "E",
            R.id.iv_tom6_faf to "F",
            R.id.iv_tom7_fasus to "F#",
            R.id.iv_tom8_sol_g to "G",
            R.id.iv_tom9_sol_sus to "G#",
            R.id.iv_tom10_la to "A",
            R.id.iv_tom11_lasus to "A#",
            R.id.iv_tom12_si to "B"
        )
        tonsButtons.forEach { (buttonId,selectedTom) ->
            findViewById<ImageButton>(buttonId).setOnClickListener {
                val newBackingTrackData = backingTrackData?.copy(tom = selectedTom)
                val intent = Intent(this, BPMActivity::class.java)
                intent.putExtra("BACKING_TRACK_DATA", newBackingTrackData)
                startActivity(intent)
            }
        }
    }

}