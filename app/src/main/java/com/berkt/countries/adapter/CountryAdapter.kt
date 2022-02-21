package com.berkt.countries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.berkt.countries.R
import com.berkt.countries.model.Country
import com.berkt.countries.util.downloadFromUrl
import com.berkt.countries.util.placeholderProgressBar
import com.berkt.countries.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(val countryList: ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(var view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.tvItemCountryName.text = countryList[position].countryName
        holder.view.tvItemCountryRegion.text = countryList[position].countryRegion

        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryUuid = countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.ivItemCountry.downloadFromUrl(countryList[position].imageUrl, placeholderProgressBar(holder.view.context))

    }

    override fun getItemCount(): Int = countryList.size

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}