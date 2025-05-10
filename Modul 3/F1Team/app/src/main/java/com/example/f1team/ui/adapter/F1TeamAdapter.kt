package com.example.f1team.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.f1team.databinding.ItemF1Binding
import com.example.f1team.model.F1Team

class F1TeamAdapter (
    private val onTeamClick: (F1Team) -> Unit,
    private val onDetailClick: (F1Team) -> Unit

) : RecyclerView.Adapter<F1TeamAdapter.F1TeamViewHolder>() {

    private  val teamList = mutableListOf<F1Team>()

    fun submitList(list : List<F1Team>){
        teamList.clear()
        teamList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): F1TeamAdapter.F1TeamViewHolder {
        val binding = ItemF1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return F1TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: F1TeamAdapter.F1TeamViewHolder, position: Int) {
        holder.bind(teamList[position])
    }

    override fun getItemCount(): Int = teamList.count()

    inner class F1TeamViewHolder(private val binding: ItemF1Binding) : RecyclerView.ViewHolder(binding.root){
        fun bind(team: F1Team) {
            binding.itemName.text = team.name
            binding.itemDesc.text = team.desc
            Glide.with(binding.root.context)
                .load(team.imageUrl)
                .transform(CenterCrop(), RoundedCorners(24))
                .into(binding.itemImageDetail)

            binding.btnTeam.setOnClickListener {onTeamClick(team)}
            binding.btnDetail.setOnClickListener {onDetailClick(team)}
        }
    }

}