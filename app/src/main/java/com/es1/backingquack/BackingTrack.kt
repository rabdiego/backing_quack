package com.es1.backingquack

import java.io.File
import org.jfugue.midi.MidiFileManager
import org.jfugue.pattern.Pattern
import org.jfugue.theory.ChordProgression

class BackingTrack(val chordProgressionList: List <String>, val root: String, val bpm: Int) {
    private fun convertChordProgressionString(chordProgressionList: List <String>): String {
        var finalString: String = ""

        for (chordProgression in chordProgressionList) {
            val chords: List <String> = chordProgression.split(" ")
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
        val file = File("/home/diego/Documents/kotlinstudies/src/pattern.midi")
        MidiFileManager.savePatternToMidi(pattern, file)
    }
}