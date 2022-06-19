package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
        val list= arrayListOf<TodoModel>()
        lateinit var toolbar:androidx.appcompat.widget.Toolbar
         lateinit var float_btn: FloatingActionButton
         val adapter=TodoAdapter(list)
         lateinit var rv:RecyclerView
         val db by lazy {
             AppDatabase.getDatabase(this)
         }
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            toolbar=findViewById(R.id.toolbar)
            setSupportActionBar(toolbar)
            rv=findViewById(R.id.todo_rv)
            float_btn=findViewById(R.id.float_btn)
            rv.apply {
                layoutManager=LinearLayoutManager(this@MainActivity)
                adapter=this@MainActivity.adapter
            }
            db.todoDao().getTask().observe(this, Observer {
                if(!it.isNullOrEmpty())
                    list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
            })
            // toolbar=findViewById(R.id.toolbar)
            float_btn.setOnClickListener {
                startActivity(Intent(this,TaskActivity::class.java))
            }

        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.main_menu,menu)
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when(item.itemId)
            {
                R.id.history->
                {
                    startActivity(Intent(this,TaskActivity::class.java))
                }
            }
            return super.onOptionsItemSelected(item)
        }
    }
