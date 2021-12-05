package com.pedrogomez.postviewer.view.userslist.view.listadapter

import androidx.recyclerview.widget.DiffUtil
import com.pedrogomez.postviewer.models.db.UserTable

class TaskDiffCallback : DiffUtil.ItemCallback<UserTable>() {
    override fun areItemsTheSame(oldItem: UserTable, newItem: UserTable): Boolean {
        return oldItem.objectID == newItem.objectID
    }

    override fun areContentsTheSame(oldItem: UserTable, newItem: UserTable): Boolean {
        return oldItem == newItem
    }
}