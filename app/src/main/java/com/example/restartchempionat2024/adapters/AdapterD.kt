package com.example.restartchempionat2024.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ItemDestDetBinding
import com.example.restartchempionat2024.models.DestinationDetail
import com.example.restartchempionat2024.objects.UserData
import com.example.restartchempionat2024.screens.SendAPackage

class AdapterD(private val listener: SendAPackage): RecyclerView.Adapter<AdapterD.Holder>() {
    private var listD: MutableList<DestinationDetail> = ArrayList()

    class Holder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = ItemDestDetBinding.bind(item)

        fun bind(d: DestinationDetail, position: Int) {
            with(binding){
                iAddress.setText(d.address)
                iCountry.setText(d.stateCountry)
                iPhone.setText(d.phone)
                iOthers.setText(d.others)
                iAddress.addTextChangedListener {
                    transmitter(position)
                }
                iCountry.addTextChangedListener {
                    transmitter(position)
                }
                iPhone.addTextChangedListener {
                    transmitter(position)
                }
                iOthers.addTextChangedListener {
                    transmitter(position)
                }
            }
        }

        /** При изменении полей сразу данные заносятся в лист пунктов доставки в UserData */
        fun transmitter(p: Int){
            with(binding) {
                val address = iAddress.text.toString()
                val country = iCountry.text.toString()
                val phone = iPhone.text.toString()
                val other = iOthers.text.toString()
                UserData.lastDestDet[p] = DestinationDetail(0, address, country, phone, other)
                if(address != "" && country != "" && phone != ""){
                    UserData.lastDestDetIsNotEmpty = true
                } else {
                    UserData.lastDestDetIsNotEmpty = false
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dest_det, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = listD.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listD[position], position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addDest(it: DestinationDetail){
        listD.add(it)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearList(){
        listD.clear()
        notifyDataSetChanged()
    }

    interface Listener {
        fun setDestinationDetail(d: DestinationDetail, p: Int)
    }
}