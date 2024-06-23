package com.es1.backingquack

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.es1.core.model.BackingTrackData
import com.google.android.material.slider.Slider

class BPMActivity : AppCompatActivity() {

    private var backingTrackData: BackingTrackData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bpm)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        backingTrackData = intent.getParcelableExtra("BACKING_TRACK_DATA")


        val slider: Slider = findViewById(R.id.slider)

        val generatedButton: Button = findViewById(R.id.bpm_button)
        Toast.makeText(this, "CHORD selecionado: ${intent.getStringExtra("CHORD")}", Toast.LENGTH_SHORT).show()
        generatedButton.setOnClickListener {
            val selectedBpm = slider.value.toInt()

            val newBackingTrackData = backingTrackData?.copy(bpm = selectedBpm)
            val intent = Intent(this@BPMActivity, PlayingActivity::class.java)
            intent.putExtra("BACKING_TRACK_DATA", newBackingTrackData)
            startActivity(intent)
        }
    }
}