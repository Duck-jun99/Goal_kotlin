package com.goalapp.goal.recyclerView

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.goalapp.goal.R
import com.goalapp.goal.database.Todo

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>(), OnListItemClickListener {
    private val Big_Goal_list = ArrayList<String>()
    private val items = ArrayList<String>()
    var listener: OnListItemClickListener? = null
    var todos: MutableList<Todo> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        //ViewHolder viewHolder = new ViewHolder(itemView);
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val todo_item = todos[position]
        Log.e("어댑터에 들어가 있는 todos : ", todos.toString())
        viewHolder.tv_big.text = todo_item.big_Goal
        for (i in todos.indices) {
            try {
                if (todos[i].complete_time == null) {
                    viewHolder.comp_percent_line.visibility = View.INVISIBLE
                    val percent = (todo_item.stage.toDouble()
                            / todo_item.small_Goal_size.toDouble() * 100).toInt()
                    viewHolder.tv_small.text = "$percent%"
                    if (percent < 10) {
                        viewHolder.percent_line.setColorFilter(Color.rgb(209, 178, 255))
                    } else if (percent >= 10 && percent < 20) {
                        viewHolder.percent_line.setColorFilter(Color.rgb(181, 178, 255))
                    } else if (percent >= 20 && percent < 30) {
                        viewHolder.percent_line.setColorFilter(Color.rgb(178, 204, 255))
                    } else if (percent >= 30 && percent < 40) {
                        viewHolder.percent_line.setColorFilter(Color.rgb(178, 235, 244))
                    } else if (percent >= 40 && percent < 50) {
                        viewHolder.percent_line.setColorFilter(Color.rgb(183, 240, 177))
                    } else if (percent >= 50 && percent < 60) {
                        viewHolder.percent_line.setColorFilter(Color.rgb(206, 242, 121))
                    } else if (percent >= 60 && percent < 70) {
                        viewHolder.percent_line.setColorFilter(Color.rgb(250, 237, 125))
                    } else if (percent >= 70 && percent < 80) {
                        viewHolder.percent_line.setColorFilter(Color.rgb(255, 224, 140))
                    } else if (percent >= 80 && percent < 90) {
                        viewHolder.percent_line.setColorFilter(Color.rgb(255, 193, 158))
                    } else if (percent >= 90 && percent < 100) {
                        viewHolder.percent_line.setColorFilter(Color.rgb(255, 178, 217))
                    } else if (percent >= 100) {
                        viewHolder.percent_line.setColorFilter(Color.rgb(255, 167, 167))
                    }
                } else if (todos[i].complete_time != null) {
                    viewHolder.comp_percent_line.visibility = View.VISIBLE
                    viewHolder.percent_line.visibility = View.INVISIBLE
                    viewHolder.comp_percent_line.setColorFilter(Color.rgb(255, 167, 167))
                    viewHolder.tv_small.text = todos[position].complete_time
                }
            } catch (e: IndexOutOfBoundsException) {
                Log.e("인덱스 오류 ", "죄송합니다...")
                Toast.makeText(
                    viewHolder.tv_big.context,
                    "오류가 발생했습니다.\n개발자에게 문의해주시면 감사하겠습니다.\n죄송합니다ㅠㅠ",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun setItems(todos: MutableList<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnListItemClickListener?) {
        this.listener = listener
    }

    override fun onItemClick(holder: ViewHolder?, view: View?, position: Int) {
        if (listener != null) {
            listener!!.onItemClick(holder, view, position)
        }
    }

    fun addItem(item: Todo) {
        todos.add(item)
    }

    fun getItem(position: Int): Todo {
        return todos[position]
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        var tv_big: TextView
        var tv_small: TextView
        var percent_line: ImageView
        var comp_percent_line: ImageView
        var view: View? = null

        init {
            tv_big = itemView.findViewById(R.id.tv_big)
            tv_small = itemView.findViewById(R.id.tv_small)
            percent_line = itemView.findViewById(R.id.percent_line)
            comp_percent_line = itemView.findViewById(R.id.comp_percent_line)
            view.setOnClickListener { v: View? ->
                val position = itemViewType
                if (listener != null) {
                    listener!!.onItemClick(this@ViewHolder, v, position)
                }
            }
        }
    }
}