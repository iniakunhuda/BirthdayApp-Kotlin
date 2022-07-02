package com.subdigistudio.birthdaycard

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.subdigistudio.birthdaycard.data.Birthday
import java.text.SimpleDateFormat
import java.util.*

class BirthdayAdapter(private val list: List<Birthday>) :
        RecyclerView.Adapter<BirthdayAdapter.BirthdayViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(
        onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class BirthdayViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.birthday_item, parent, false)) {

        private var birthdayName: TextView? = null
        private var birthdayDate: TextView? = null
        private var birthdayPict: ImageView? = null
        private var currentData: Birthday? = null

        init {
            birthdayName = itemView.findViewById(R.id.item_txt_title)
            birthdayDate = itemView.findViewById(R.id.item_txt_subtitle)
            birthdayPict = itemView.findViewById(R.id.item_image)
        }


        fun bind(birthday: Birthday) {
            currentData = birthday

            birthdayName?.text = birthday.name
            birthdayDate?.text = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.ENGLISH).format(birthday.date)

            if (birthday.image != null) {
                birthdayPict?.setImageResource(birthday.image)
            } else {
                birthdayPict?.setImageResource(R.drawable.birthday_icon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BirthdayViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BirthdayViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BirthdayViewHolder, position: Int) {
        val birthday: Birthday = list[position]
        holder.bind(birthday)
        holder.itemView.setOnClickListener {
            onItemClickCallback
                .onItemClick(birthday)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickCallback {
        fun onItemClick(data: Birthday)
    }

}