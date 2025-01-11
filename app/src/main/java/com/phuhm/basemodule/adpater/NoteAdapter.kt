package com.phuhm.basemodule.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.phuhm.basemodule.R
import com.phuhm.basemodule.data.model.Note
import com.phuhm.basemodule.databinding.ItemNoteBinding
import com.phuhm.basemodule.extensions.setOnSingleClickListener
import com.phuhm.basemodule.utils.DateUtils
import java.util.Date

class NoteAdapter(
    private val context: Context,
    private val onItemNoteClickListener: OnItemNoteClickListener
) : ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class NoteViewHolder(private val binding: ItemNoteBinding) : ViewHolder(binding.root) {
        fun onBind(note: Note) {
            binding.tvTitle.text = note.title
            binding.tvContents.text = note.notes
            binding.tvTimestamp.text = formatTimestamp(note)

            binding.main.setOnSingleClickListener {
                onItemNoteClickListener.onNoteItemClick(note)
            }

            binding.btnDelete.setOnSingleClickListener {
                onItemNoteClickListener.onNoteItemDeleteClick(note)
            }
        }
    }

    private fun formatTimestamp(note: Note): String {
        val pattern = "yyyy/MM/dd ${context.getString(R.string.txt_dot)} HH:mm"
        return DateUtils.formatDate(Date(note.timestamp), pattern)
    }

    class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemNoteClickListener {
        fun onNoteItemClick(note: Note)
        fun onNoteItemDeleteClick(note: Note)
    }
}