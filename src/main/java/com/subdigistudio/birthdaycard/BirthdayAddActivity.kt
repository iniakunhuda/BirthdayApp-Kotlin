package com.subdigistudio.birthdaycard

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.subdigistudio.birthdaycard.databinding.ActivityBirthdayAddBinding
import java.text.SimpleDateFormat
import java.util.*


class BirthdayAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityBirthdayAddBinding.inflate(layoutInflater)

        binding.inputDate.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")

            datePicker.addOnPositiveButtonClickListener {
                val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
                val date = dateFormatter.format(Date(it))
                binding.inputDate.setText(date)
            }
        }

        setTitle("Tambah Baru")
        setContentView(binding.root)
    }
}