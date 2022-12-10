package com.example.dbgstore.fragment.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dbgstore.data.Games
import com.example.dbgstore.databinding.FragmentHomeBinding
import com.example.dbgstore.topup.TopUpActivity
import com.example.dbgstore.utils.Preferences
import com.google.firebase.database.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val dataList = ArrayList<Games>()

    lateinit var preferences: Preferences
    lateinit var mDatabase: DatabaseReference

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
//                rvListGame.setHasFixedSize(true)
//                list.addAll(GameData.listData)

                preferences = Preferences(requireActivity()!!.applicationContext)
                mDatabase = FirebaseDatabase.getInstance("https://dbg-store-default-rtdb.asia-southeast1.firebasedatabase.app/")
                    .getReference("game")

                rvListGame.layoutManager = GridLayoutManager(requireActivity(), 2)
                getData()


//                val gridGameAdapter = GridGameAdapter(dataList)
//                rvListGame.adapter = gridGameAdapter
//
//                gridGameAdapter.setOnItemClickCallback(object : GridGameAdapter.OnItemClickCallback {
//                    override fun onItemClicked(data: Games) {
//                        startActivity(
//                            Intent(requireActivity(), TopUpActivity::class.java)
//                        )
//                    }
//                })
            }
        }
    }

    private fun getData() {
        with(binding) {
            mDatabase.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    //dataList.clear()
                    for (getsnapshot in snapshot.children) {
                        var game = getsnapshot.getValue(Games::class.java)
                        dataList.add(game!!)
                    }

                    rvListGame.adapter = GridGameAdapter(dataList) {
                        startActivity(Intent(context, TopUpActivity::class.java)
                            .putExtra("data", it))
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, ""+error.message, Toast.LENGTH_LONG).show()
                }

            })
        }
    }
}