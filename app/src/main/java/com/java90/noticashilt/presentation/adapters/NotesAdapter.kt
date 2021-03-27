package com.java90.noticashilt.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.java90.core.data.Note
import com.java90.noticashilt.R
import com.java90.noticashilt.databinding.ItemNoteBinding

class NotesAdapter<T> : ListAdapter<T, RecyclerView.ViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
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
            is NoteViewHolder -> NoteViewHolder.viewType
            else -> throw ClassNotFoundException("the requested ${getItem(position)} is not part of the implementation of items adapter.")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is NoteViewHolder -> {
                val item = getItem(position) as Note
                holder.bind(item)
            }
            else -> { }
        }
    }

    class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            const val viewType: Int = R.layout.item_note
        }

        fun bind(item: Note) {

        }
    }
}

private class ItemDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return newItem == oldItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return when(oldItem) {
            is Note -> (oldItem as Note) == (newItem as Note)
            else -> false
        }
    }
}