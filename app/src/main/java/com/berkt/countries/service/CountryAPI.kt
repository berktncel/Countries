package com.berkt.countries.service

import com.berkt.countries.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

    //GET, POST
    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries(): Single<List<Country>>

}