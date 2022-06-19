package com.example.todoapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.TimePicker
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.time.Clock
import java.util.*

const val DB_NAME="todo.db"
class TaskActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var myCalendar: Calendar
    private val labels= arrayListOf("Personal","Business","Insurance","School","College","Shopping","Event")
    lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    lateinit var timeSetListener:TimePickerDialog.OnTimeSetListener
    lateinit var date_edt:TextInputEditText
    lateinit var time_edt:TextInputEditText
    lateinit var spinner: Spinner
    val db by lazy {
        AppDatabase.getDatabase(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        date_edt=findViewById(R.id.date_edt)
        time_edt=findViewById(R.id.time_edt)
        date_edt.setOnClickListener(this)
        time_edt.setOnClickListener(this)
        spinner=findViewById(R.id.spinner)
        setUpSpinner()

    }

    private fun setUpSpinner() {
        val adapter=ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,labels)
        labels.sort()
        spinner.adapter=adapter

    }

    override fun onClick(v: View) {
        when(v.id)
        {
            R.id.date_edt->
            {
                setListener()
            }
            R.id.time_edt->
            {
                setTime()
            }
        }
    }

    private fun setTime() {
        myCalendar= Calendar.getInstance()
        timeSetListener=TimePickerDialog.OnTimeSetListener (){ _: TimePicker, hour:Int, min:Int->
            myCalendar.set(Calendar.HOUR_OF_DAY,hour)
            myCalendar.set(Calendar.MINUTE,min)

            updateTime()
        }
        val timePickerDialog=TimePickerDialog(this,timeSetListener,myCalendar.get(Calendar.HOUR_OF_DAY),
            myCalendar.get(Calendar.MINUTE),false)


        timePickerDialog.show()

    }

    private fun updateTime() {
        val myformat="h:mm a"
        val sdf=SimpleDateFormat(myformat)
        time_edt.setText(sdf.format(myCalendar.time))


    }

    private fun setListener() {
        myCalendar= Calendar.getInstance()
        dateSetListener=DatePickerDialog.OnDateSetListener { _: DatePicker, year:Int, month:Int, day:Int->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,day)
            updateDate()
        }
        val datePickerDialog=DatePickerDialog(this,dateSetListener,myCalendar.get(Calendar.YEAR),
        myCalendar.get(Calendar.MONTH),
        myCalendar.get(Calendar.DAY_OF_MONTH))
        datePickerDialog.datePicker.minDate=System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun updateDate() {
        val myformat="EEE, dd MM yyyy"
        val sdf=SimpleDateFormat(myformat)
        date_edt.setText(sdf.format(myCalendar.time))
        time_edt.visibility=View.VISIBLE
    }
}