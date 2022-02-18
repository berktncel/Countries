package com.berkt.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berkt.countries.model.Country

class CountryViewModel: ViewModel() {
    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom() {

    }

}