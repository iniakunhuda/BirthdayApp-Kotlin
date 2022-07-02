package com.subdigistudio.birthdaycard

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.subdigistudio.birthdaycard.data.Birthday
import com.subdigistudio.birthdaycard.data.birthdayList
import com.subdigistudio.birthdaycard.databinding.ActivityMainBinding
const val BIRTHDAY_ID = "birthday id"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.fab.setOnClickListener {
            val intent = Intent(this, BirthdayAddActivity::class.java).apply {
                putExtra(BIRTHDAY_ID, "0")
            }
            startActivity(intent)
        }


        // ADAPTER
        val birthdayAdapter = BirthdayAdapter(birthdayList(resources))
        birthdayAdapter.setOnItemClickCallback(object : BirthdayAdapter.OnItemClickCallback {
            override fun onItemClick(data: Birthday) {
                val manageDetailIntent = Intent(this@MainActivity, BirthdayDetailActivity::class.java)
                    .apply {
                        putExtra(BirthdayDetailActivity.EXTRA_NAME, data.name)
                        putExtra(BirthdayDetailActivity.EXTRA_DATE, data.date.toString())
                        putExtra(BirthdayDetailActivity.EXTRA_ID, data.id)
                    }
                startActivity(manageDetailIntent)
            }
        })
        binding.recyclerView.adapter = birthdayAdapter

        setContentView(binding.root)
    }

    private fun adapterOnClick(birthday: Birthday) {
        val intent = Intent(this, BirthdayDetailActivity::class.java)
        intent.putExtra(BIRTHDAY_ID, birthday.id)
        startActivity(intent)
    }

}