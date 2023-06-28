package com.practice.fc_3_chapter5.list.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.practice.fc_3_chapter5.databinding.ItemImageBinding
import com.practice.fc_3_chapter5.list.ItemHandler
import com.practice.fc_3_chapter5.model.ImageItem
import com.practice.fc_3_chapter5.model.ListItem

class ImageViewHolder(
    private val binding : ItemImageBinding,
    private val itemHandler : ItemHandler? = null
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item : ListItem) {
        item as ImageItem
        binding.item = item
        binding.handler = itemHandler
    }
}