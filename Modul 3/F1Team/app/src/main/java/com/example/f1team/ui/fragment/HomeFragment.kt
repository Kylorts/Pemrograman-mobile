package com.example.f1team.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.f1team.databinding.FragmentHomeBinding
import com.example.f1team.ui.adapter.F1TeamAdapter
import com.example.f1team.viewmodel.F1TeamViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

class HomeFragment : Fragment() {

    private  var _binding: FragmentHomeBinding? = null
    private  val binding get() = _binding!!

    private  lateinit var  viewModel: F1TeamViewModel
    private lateinit var  adapter: F1TeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[F1TeamViewModel::class.java]

        adapter = F1TeamAdapter(
            onTeamClick = { team ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(team.link))
                startActivity(intent)
            },
            onDetailClick = {team ->
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(team)
                findNavController().navigate(action)
            }
        )

        binding.rvItem.layoutManager = LinearLayoutManager(requireContext())
        binding.rvItem.adapter = adapter

        viewModel.teamList.observe(viewLifecycleOwner) {team ->
            adapter.submitList(team)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}