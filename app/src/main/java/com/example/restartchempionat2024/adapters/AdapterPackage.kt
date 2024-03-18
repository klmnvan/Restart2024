package com.example.restartchempionat2024.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ItemCheckBinding
import com.example.restartchempionat2024.databinding.ItemDestDetBinding
import com.example.restartchempionat2024.models.DestinationDetail
import com.example.restartchempionat2024.objects.UserData
import com.example.restartchempionat2024.screens.SendAPackage

class AdapterPackage: RecyclerView.Adapter<AdapterPackage.Holder>() {
    private var list: MutableList<DestinationDetail> = ArrayList()

    class Holder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = ItemCheckBinding.bind(item)

        fun bind(d: DestinationDetail, position: Int) {
            with(binding){
                tCountry.text = "${d.address}, ${d.stateCountry}"
                tPhone.text = "${d.phone}"
                tOther.text = "${d.others}"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_check, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position], position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addInList(it: DestinationDetail){
        list.add(it)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearList(){
        list.clear()
        notifyDataSetChanged()
    }

    interface Listener {
        fun setDestinationDetail(d: DestinationDetail, p: Int)
    }
}