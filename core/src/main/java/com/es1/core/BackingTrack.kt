package com.es1.core//package com.es1.backingquack

import android.content.Context
import java.io.File
import org.jfugue.midi.MidiFileManager
import org.jfugue.pattern.Pattern
import org.jfugue.theory.ChordProgression

class BackingTrack(
    private val chordProgressionList: List<String>,
    private val root: String,
    private val bpm: Int,
    private val context: Context
) {
    private fun convertChordProgressionString(chordProgressionList: List<String>): String {
        var finalString: String = ""

        for (chordProgression in chordProgressionList) {
            val chords: List<String> = chordProgression.split(" ")
            for (chord in chords) {
                for (i in 1..4) {
                    finalString += "$chord "
                }
            }
        }

        return finalString
    }

    fun build() {
        val chordProgression = this.convertChordProgressionString(this.chordProgressionList)

        val cp = ChordProgression(chordProgression).setKey(root)
        val pattern = Pattern(cp).setTempo(bpm)
        val file = File(context.filesDir, "output.mid")
        try {
            MidiFileManager.savePatternToMidi(pattern, file)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}