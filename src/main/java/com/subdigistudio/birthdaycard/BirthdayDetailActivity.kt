package com.subdigistudio.birthdaycard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.subdigistudio.birthdaycard.databinding.ActivityBirthdayDetailBinding
import com.subdigistudio.birthdaycard.databinding.ActivityMainBinding

class BirthdayDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "id"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_DATE = "extra_date"
        const val EXTRA_IMAGE = R.drawable.birthday_icon
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityBirthdayDetailBinding.inflate(layoutInflater)
        binding.btnMessage.setOnClickListener {
            val message = "Selamat ulang tahun! Bahagia selalu :)"
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = intent.getStringExtra(EXTRA_NAME)

        binding.detailName.setText(intent.getStringExtra(EXTRA_NAME))
        binding.detailDate.setText(intent.getStringExtra(EXTRA_DATE))

        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}