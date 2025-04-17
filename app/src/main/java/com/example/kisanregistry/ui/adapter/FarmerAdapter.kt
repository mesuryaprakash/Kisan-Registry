package com.example.kisanregistry.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kisanregistry.R
import com.example.kisanregistry.data.model.farmer

class FarmerAdapter(
    private var farmerList: List<farmer>,
    private val onDelete: (farmer) -> Unit,
    private val onEdit: (farmer) -> Unit
) : RecyclerView.Adapter<FarmerAdapter.FarmerViewHolder>() {

    inner class FarmerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.farmerName)
        val villageText: TextView = itemView.findViewById(R.id.farmerVillage)

        init {
            itemView.setOnClickListener {
                onEdit(farmerList[adapterPosition])
            }
            itemView.setOnLongClickListener {
                onDelete(farmerList[adapterPosition])
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_farmer, parent, false)
        return FarmerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FarmerViewHolder, position: Int) {
        val farmer = farmerList[position]
        holder.nameText.text = farmer.name
        holder.villageText.text = farmer.village
    }

    override fun getItemCount() = farmerList.size

    fun updateData(newList: List<farmer>) {
        farmerList = newList
        notifyDataSetChanged()
    }
}
