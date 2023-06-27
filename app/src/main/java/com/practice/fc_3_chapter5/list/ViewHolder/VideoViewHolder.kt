package com.practice.fc_3_chapter5.list.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.practice.fc_3_chapter5.databinding.ItemVideoBinding
import com.practice.fc_3_chapter5.model.ListItem
import com.practice.fc_3_chapter5.model.VideoItem

class VideoViewHolder(private val binding : ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item : ListItem) {
        item as VideoItem
        binding.item = item
    }
}