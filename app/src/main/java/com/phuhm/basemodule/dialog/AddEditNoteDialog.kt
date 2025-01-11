package com.phuhm.basemodule.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import com.phuhm.basemodule.R
import com.phuhm.basemodule.base.BaseDialog
import com.phuhm.basemodule.data.model.Note
import com.phuhm.basemodule.databinding.DialogAddEditNoteBinding
import com.phuhm.basemodule.extensions.setOnSingleClickListener
import com.phuhm.basemodule.extensions.setupDialog
import com.phuhm.basemodule.utils.DateUtils

class AddEditNoteDialog(
    context: Context,
    private val noteEdit: Note? = null,
    private val block: (Note) -> Unit
) : BaseDialog<DialogAddEditNoteBinding>(context) {
    override fun inflateBinding(inflater: LayoutInflater): DialogAddEditNoteBinding {
        return DialogAddEditNoteBinding.inflate(layoutInflater)
    }

    override fun onStart() {
        super.onStart()
        setupDialog(isFullScreen = false, isCancelable = true, isCancelOnTouchOutside = true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        handleEvents()
    }

    private fun initViews() {
        initTitleHeader()
        initTitleButton()
        fillDataEditText()
    }

    private fun initTitleHeader() {
        val title = if (noteEdit == null) context.getString(R.string.txt_add_note) else context.getString(R.string.txt_edit_note)
        binding.tvTitle.text = title
    }

    private fun initTitleButton() {
        val title = if (noteEdit == null) context.getString(R.string.txt_add) else context.getString(R.string.txt_save)
        binding.btnProgress.text = title
    }

    private fun fillDataEditText() {
        if(noteEdit == null) {
            return
        }

        binding.edtTitle.setText(noteEdit.title)
        binding.edtNotes.setText(noteEdit.notes)
    }

    private fun handleEvents() {
        binding.btnProgress.setOnSingleClickListener {
            val id = noteEdit?.id ?: 0
            val title = binding.edtTitle.text.toString().trim()
            val notes = binding.edtNotes.text.toString().trim()
            val timestamp = noteEdit?.timestamp ?: DateUtils.getCurrentDate().time

            if(title.isEmpty() || notes.isEmpty()) {
                return@setOnSingleClickListener
            }

            val note = Note(id = id, title = title, notes = notes, timestamp = timestamp)
            block(note)
            dismiss()
        }
    }
}