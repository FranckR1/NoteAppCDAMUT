package com.example.noteappcdamut

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NoteListActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var notes:MutableList<Note>
    lateinit var adapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_note_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        notes = mutableListOf<Note>()
        notes.add(Note("Note 1", "Ma premiére note"))
        notes.add(Note("Note 2", "Ma premiére note 2"))
        notes.add(Note("Note 3", "Ma premiére note 3"))
        notes.add(Note("Note 4", "Ma premiére note 4"))
        notes.add(Note("Note 5", "Ma premiére note 5"))
        notes.add(Note("Note 6", "Ma premiére note 6"))

        adapter = NoteAdapter(notes, this)

        val recyclerView = findViewById<RecyclerView>(R.id.notes_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


    }

    override fun onClick(view: View) {
        if (view.tag != null) {
            showNoteDetail(view.tag as Int)
        }
    }

    fun showNoteDetail(noteIndex: Int) {
        val note = notes[noteIndex]
        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE, note)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, noteIndex)
        startActivity(intent)
    }
}