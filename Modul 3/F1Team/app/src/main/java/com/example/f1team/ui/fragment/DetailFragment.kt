package com.example.f1team.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.f1team.databinding.FragmentDetailBinding
import com.example.f1team.model.F1Team


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private  val binding get() = _binding!!

    private  var team: F1Team? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            team = DetailFragmentArgs.fromBundle(it).team
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        team?.let { team ->
            binding.itemNameDetail.text = team.name
            binding.itemBaseDetail.text = team.base
            binding.itemChiefDetail.text = team.teamChief
            binding.itemChassisDetail.text = team.chassis
            binding.itemWcDetail.text = team.worldChampion
            binding.itemDescDetail.text = team.desc

            Glide.with(requireContext())
                .load(team.imageUrlDetail)
                .into(binding.itemImageDetail)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}