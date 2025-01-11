package com.phuhm.basemodule.screen

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.phuhm.basemodule.R
import com.phuhm.basemodule.adpater.NoteAdapter
import com.phuhm.basemodule.base.BaseActivity
import com.phuhm.basemodule.data.model.Note
import com.phuhm.basemodule.data.model.RoomDatabaseResult
import com.phuhm.basemodule.databinding.ActivityRoomBinding
import com.phuhm.basemodule.dialog.AddEditNoteDialog
import com.phuhm.basemodule.dialog.FailedDialog
import com.phuhm.basemodule.dialog.SuccessDialog
import com.phuhm.basemodule.event.RoomEvents
import com.phuhm.basemodule.extensions.setNavigationBarColor
import com.phuhm.basemodule.extensions.setStatusBarColor
import com.phuhm.basemodule.extensions.setOnSingleClickListener
import com.phuhm.basemodule.viewmodel.RoomViewModel
import kotlinx.coroutines.launch

class RoomActivity : BaseActivity<ActivityRoomBinding>(), NoteAdapter.OnItemNoteClickListener {
    private val roomViewModel: RoomViewModel by viewModels { RoomViewModel.Factory }
    private var noteAdapter: NoteAdapter? = null

    override fun inflateBinding(inflater: LayoutInflater): ActivityRoomBinding {
        return ActivityRoomBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        observeViewModels()
        handleEvents()
    }

    private fun initViews() {
        initToolbar()
        setStatusBarColor(R.color.primaryColor)
        setNavigationBarColor(R.color.primaryColor)
        initRecyclerViewNotes()
    }

    private fun initToolbar() {
        binding.includeToolbar.tvTitle.text = getString(R.string.txt_room)
    }

    private fun initRecyclerViewNotes() {
        noteAdapter = NoteAdapter(this, this)
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(this@RoomActivity)
            adapter = noteAdapter
        }
    }

    private fun observeViewModels() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                roomViewModel.notes.collect {
                    noteAdapter?.submitList(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                roomViewModel.insertResult.collect { result ->
                    when (result) {
                        is RoomDatabaseResult.Success -> {
                            showSuccessDialog()
                        }

                        is RoomDatabaseResult.Failure -> {
                            showFailedDialog()
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                roomViewModel.deleteResult.collect { result ->
                    when (result) {
                        is RoomDatabaseResult.Success -> {
                            showRestoreNoteSnackBar()
                        }

                        is RoomDatabaseResult.Failure -> {
                            showFailedDialog()
                        }
                    }
                }
            }
        }
    }

    private fun showRestoreNoteSnackBar() {
        Snackbar.make(
            findViewById(android.R.id.content),
            getString(R.string.txt_note_deleted), Snackbar.LENGTH_LONG
        ).apply {
            setActionTextColor(ContextCompat.getColor(this@RoomActivity, R.color.onPrimaryColor))
            setAction(getString(R.string.txt_undo)) {
                roomViewModel.onEvent(RoomEvents.OnRestoreNote)
            }
            show()
        }
    }

    private fun showSuccessDialog() {
        val dialog = SuccessDialog(this)
        dialog.show()
    }

    private fun showFailedDialog() {
        val dialog = FailedDialog(this)
        dialog.show()
    }

    private fun handleEvents() {
        binding.includeToolbar.btnBack.setOnSingleClickListener {
            finish()
        }

        binding.fbAdd.setOnSingleClickListener {
            showAddNoteDialog()
        }
    }

    private fun showAddNoteDialog() {
        val dialog = AddEditNoteDialog(this, block = ::insertNote)
        dialog.show()
    }

    private fun insertNote(note: Note) {
        roomViewModel.onEvent(RoomEvents.OnInsertNote(note))
    }

    override fun onNoteItemClick(note: Note) {
        showEditNoteDialog(note)
    }

    private fun showEditNoteDialog(note: Note) {
        val dialog = AddEditNoteDialog(this, note, ::insertNote)
        dialog.show()
    }

    override fun onNoteItemDeleteClick(note: Note) {
        roomViewModel.onEvent(RoomEvents.OnDeleteNote(note))
    }
}