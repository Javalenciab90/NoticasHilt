package com.java90.noticashilt.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.java90.core.domain.models.Note
import com.java90.noticashilt.R
import com.java90.noticashilt.databinding.ItemNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class NotesAdapter(private val onItemClickListener : ((Long) -> Unit)): ListAdapter<Note, NotesAdapter.NoteViewHolder>(ItemDiffCallback()) {

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
        holder.itemView.setOnClickListener { onItemClickListener.invoke(item.id)}
        holder.bind(item)
    }

    class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            const val viewType: Int = R.layout.item_note
        }

        fun bind(item: Note) {
            with(binding) {
                noteTitle.text = item.title
                noteContent.text = item.content
                wordCount.text = "words: ${item.wordCount}"

                val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
                val resultDate = Date(item.updateTime)
                noteDate.text = "Last updated ${sdf.format(resultDate)}"
            }
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