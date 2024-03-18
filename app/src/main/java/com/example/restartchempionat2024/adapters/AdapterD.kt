package com.example.restartchempionat2024.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ItemDestDetBinding
import com.example.restartchempionat2024.models.DestinationDetail
import com.example.restartchempionat2024.screens.SendAPackage

class AdapterD(): RecyclerView.Adapter<AdapterD.Holder>() {
    private var listD: MutableList<DestinationDetail> = ArrayList()

    class Holder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = ItemDestDetBinding.bind(item)

        fun bind(dest: DestinationDetail) {
            with(binding){
                iAddress.setText(dest.address)
                iCountry.setText(dest.stateCountry)
                iPhone.setText(dest.phone)
                iOthers.setText(dest.others)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dest_det, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = listD.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listD[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addDest(it: DestinationDetail){
        listD.add(it)
        notifyDataSetChanged()
    }
}