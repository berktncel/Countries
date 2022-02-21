package com.berkt.countries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.berkt.countries.R
import com.berkt.countries.util.downloadFromUrl
import com.berkt.countries.util.placeholderProgressBar
import com.berkt.countries.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country.*

class CountryFragment : Fragment() {

    private lateinit var viewModel: CountryViewModel
    private var countryUuid = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                tvCountryName.text = country.countryName
                tvCountryCapital.text = country.countryCapital
                tvCountryCurrency.text = country.countryCurrency
                tvCountryLanguage.text = country.countryLanguage
                tvCountryRegion.text = country.countryRegion
                context?.let {
                    ivCountry.downloadFromUrl(country.imageUrl, placeholderProgressBar(it))
                }

            }
        })
    }

}