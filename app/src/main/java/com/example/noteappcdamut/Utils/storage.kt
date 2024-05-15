package com.example.noteappcdamut.Utils

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.example.noteappcdamut.Note
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.UUID

fun persistNote(context: Context, note: Note) {
    if(TextUtils.isEmpty(note.filename)) {
        note.filename = UUID.randomUUID().toString()+".note"
    }

    val fileOutput= context.openFileOutput(note.filename, Context.MODE_PRIVATE)
    val outputStream = ObjectOutputStream(fileOutput)

    outputStream.writeObject(note)
    outputStream.close()
}

private fun loadNote(context: Context, filename: String) : Note {
    val fileInput = context.openFileInput(filename)
    val inputStream = ObjectInputStream(fileInput)
    val note = inputStream.readObject() as Note
    inputStream.close()
    return note
}
fun loadNotes(context: Context) : MutableList<Note> {
    val notes = mutableListOf<Note>()
    val notesDir = context.filesDir
    for (filename in notesDir.list()!!) {
        try {
            val note = loadNote(context, filename)
            notes.add(note)
            Log.i("Exeption", "Je suis dans le load Note")
        } catch (e : Exception) {
            Log.e("Exeption", "Erreur du load Note", e)
        }
    }
    return notes
}

fun deleteNote(context: Context, note: Note) {
    context.deleteFile(
        note.filename
    )

}