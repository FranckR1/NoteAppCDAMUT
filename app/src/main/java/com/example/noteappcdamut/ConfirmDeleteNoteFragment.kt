package com.example.noteappcdamut

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ConfirmDeleteNoteFragment(val noteTitle: String="") : DialogFragment() {

    interface ConfirmDeleteDialogListener {
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()
    }

    var listener: ConfirmDeleteDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = activity?.let { AlertDialog.Builder(it) }
        builder?.setMessage("Etes vous sure de vouloir supprimer la note \"$noteTitle\" ?")
            ?.setPositiveButton(
                "Supprimer",
                DialogInterface.OnClickListener { dialog, id -> listener ?.onDialogPositiveClick() }
            )
            ?.setNegativeButton(
                "Annuler",
                DialogInterface.OnClickListener { dialog, id -> listener ?.onDialogNegativeClick() }
            )
        return builder!!.create()
    }
}