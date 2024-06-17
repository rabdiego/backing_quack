package com.es1.backingquack

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.es1.core.BackingTrackData

@Suppress("DEPRECATION")
class SelectChordActivity : AppCompatActivity() {
    private lateinit var spinner : Spinner
    private var backingTrackData: BackingTrackData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_select_chord)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        backingTrackData = intent.getParcelableExtra("BACKING_TRACK_DATA")


        spinner = findViewById(R.id.spinnerChord)
        val listItems = listOf(
            "",
            "I-IV-V", "I-V-vi-IV",
            "ii-V-I", "I-vi-IV-V",
            "vi-IV-I-V", "I-vi-ii-V",
            "I-IV-I-V", "I-vi-iii-IV",
            "I-V-IV", "I-iii-IV-V")

        var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listItems)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()


                if (selectedItem.isNotEmpty()){
                    Toast.makeText(this@SelectChordActivity, "You've selected $selectedItem", Toast.LENGTH_SHORT).show()

                    val newBackingTrackData = backingTrackData?.copy(chords = selectedItem)
                    val intent = Intent(this@SelectChordActivity, TonsActivity::class.java)
                    intent.putExtra("BACKING_TRACK_DATA", newBackingTrackData)
                    startActivity(intent)
                }


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
}