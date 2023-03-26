package com.goalapp.goal.recyclerView

import android.view.View

interface OnListItemClickListener {
    fun onItemClick(holder: Adapter.ViewHolder?, view: View?, position: Int)
}