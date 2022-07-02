package com.subdigistudio.birthdaycard.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataSource(resources: Resources) {

    // SKIP BAGIAN INI DULU

    private val initBirthdayList = birthdayList(resources)
    private val birthdayLiveData = MutableLiveData(initBirthdayList)

    fun addBirthday(birthday: Birthday) {
        val currentList = birthdayLiveData.value
        if (currentList == null) {
            birthdayLiveData.postValue(listOf(birthday))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, birthday)
            birthdayLiveData.postValue(updatedList)
        }
    }

    fun removeBirthday(flower: Birthday) {
        val currentList = birthdayLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(flower)
            birthdayLiveData.postValue(updatedList)
        }
    }

    fun getBirthdayById(id: Long): Birthday? {
        birthdayLiveData.value?.let { flowers ->
            return flowers.firstOrNull{ it.id == id}
        }
        return null
    }

    fun getBirthdayList(): LiveData<List<Birthday>> {
        return birthdayLiveData
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}