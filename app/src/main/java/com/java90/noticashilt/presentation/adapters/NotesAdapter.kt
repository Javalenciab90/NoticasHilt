package com.java90.noticashilt.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.java90.core.domain.models.Note
import com.java90.noticashilt.R
import com.java90.noticashilt.databinding.ItemNoteBinding

class NotesAdapter : ListAdapter<Note, NotesAdapter.NoteViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            NoteViewHolder.viewType -> {
                val viewBinding = ItemNoteBinding.inflate(inflater, parent, false)
                NoteViewHolder(viewBinding)
            }
            else ->  throw ClassNotFoundException("the requested $viewType is not part of the implementation of items adapter.")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Note -> NoteViewHolder.viewType
            else -> throw ClassNotFoundException("the requested ${getItem(position)} is not part of the implementation of items adapter.")
        }
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = getItem(position) as Note
        holder.bind(item)
    }

    class NoteViewHolder(binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            const val viewType: Int = R.layout.item_note
        }

        fun bind(item: Note) {

        }
    }
}

private class ItemDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return newItem == oldItem
    }
}