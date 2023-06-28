package com.practice.fc_3_chapter5.list

import com.practice.fc_3_chapter5.model.ListItem

interface ItemHandler {
    fun onClickFavorite(item: ListItem)
}