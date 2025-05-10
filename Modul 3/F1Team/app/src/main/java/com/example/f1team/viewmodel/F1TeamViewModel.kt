package com.example.f1team.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.f1team.R
import com.example.f1team.model.F1Team

class F1TeamViewModel(application: Application) : AndroidViewModel(application)  {
    private val _teamList = MutableLiveData<List<F1Team>>()
    val teamList: LiveData<List<F1Team>> get() = _teamList

    init {
        val name = application.resources.getStringArray(R.array.data_name).toList()
        val base = application.resources.getStringArray(R.array.data_base).toList()
        val teamChief = application.resources.getStringArray(R.array.data_chief).toList()
        val chassis = application.resources.getStringArray(R.array.data_chassis).toList()
        val champion = application.resources.getStringArray(R.array.data_champion).toList()
        val desc = application.resources.getStringArray(R.array.data_desc).toList()
        val link = application.resources.getStringArray(R.array.data_link).toList()
        val image = application.resources.getStringArray(R.array.data_image).toList()
        val imageDetail = application.resources.getStringArray(R.array.data_image_detail).toList()

        val f1Team = mutableListOf<F1Team>()

        for(i in name.indices) {
            f1Team.add(
                F1Team(
                    name = name[i],
                    base = base[i],
                    teamChief = teamChief[i],
                    chassis = chassis[i],
                    worldChampion = champion[i],
                    desc = desc[i],
                    link = link[i],
                    imageUrl = image[i],
                    imageUrlDetail = imageDetail[i]
                )
            )
        }

        _teamList.value = f1Team
    }
}