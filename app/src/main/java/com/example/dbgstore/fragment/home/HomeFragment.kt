package com.example.dbgstore.fragment.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dbgstore.data.GameData
import com.example.dbgstore.data.Games
import com.example.dbgstore.databinding.FragmentHomeBinding
import com.example.dbgstore.topup.TopUpActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<Games>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        if (activity != null && context != null) {
            with(binding) {
                rvListGame.setHasFixedSize(true)
                list.addAll(GameData.listData)
                showGrid()

                rvListGame.layoutManager = GridLayoutManager(requireActivity(), 2)
                val gridGameAdapter = GridGameAdapter(list)
                rvListGame.adapter = gridGameAdapter

                gridGameAdapter.setOnItemClickCallback(object : GridGameAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: Games) {
                        startActivity(
                            Intent(requireActivity(), TopUpActivity::class.java)
                        )
                    }
                })
            }
        }
    }

    private fun showGrid() {

    }
}