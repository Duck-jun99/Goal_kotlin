package com.goalapp.goal.widget

class WidgetItem(var _id: Int, var content: String) {

    fun get_id(): Int {
        return _id
    }

    fun set_id(_id: Int) {
        this._id = _id
    }
}