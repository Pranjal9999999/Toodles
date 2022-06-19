package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class TodoAdapter(val list:List<TodoModel>):RecyclerView.Adapter<TodoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_item,parent,false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount()=list.size
}

class TodoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    fun bind(todoModel: TodoModel)
    {
        val show_title:TextView=itemView.findViewById(R.id.show_title)
        val show_task:TextView=itemView.findViewById(R.id.show_task)
        val show_category:TextView=itemView.findViewById(R.id.show_category)

        with(itemView)
        {
            show_title.text=todoModel.title
            show_category.text=todoModel.category
            show_task.text=todoModel.description
            updateTime(todoModel.time)
            updateTime(todoModel.date )

        }
    }
    private fun updateTime(time :Long)
    {
        val format="hh:mm a"
        val sdf= SimpleDateFormat(format)
        val text_time:TextView=itemView.findViewById(R.id.texttime)
        text_time.text=sdf.format(Date(time))
    }
    private fun updateDate(time :Long)
    {
        val format="EEE, d MMM yyyy"
        val sdf=SimpleDateFormat(format)
        val text_date:TextView=itemView.findViewById(R.id.textdate)
        text_date.text=sdf.format(Date(time))
    }

}
