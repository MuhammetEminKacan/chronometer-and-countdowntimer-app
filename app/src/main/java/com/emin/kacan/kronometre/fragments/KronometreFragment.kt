package com.emin.kacan.kronometre.fragments

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.emin.kacan.kronometre.databinding.FragmentKronometreBinding

class KronometreFragment : Fragment() {
    private lateinit var binding:FragmentKronometreBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       binding = FragmentKronometreBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var zaman :Long = 0

        binding.ivBaslatma.setOnClickListener {
            binding.kronometre.base = SystemClock.elapsedRealtime() +zaman
            binding.kronometre.start()
            binding.ivBaslatma.visibility = View.INVISIBLE
            binding.ivDurdur.visibility = View.VISIBLE

        }
        binding.ivDurdur.setOnClickListener {
            zaman = binding.kronometre.base-SystemClock.elapsedRealtime()
            binding.kronometre.stop()
            binding.ivDurdur.visibility = View.INVISIBLE
            binding.ivDevam.visibility = View.VISIBLE
            binding.ivBitir.visibility = View.VISIBLE
        }
        binding.ivBitir.setOnClickListener {
            binding.kronometre.base = SystemClock.elapsedRealtime()
            binding.kronometre.stop()
            zaman = 0
            binding.ivDevam.visibility = View.INVISIBLE
            binding.ivBitir.visibility = View.INVISIBLE
            binding.ivBaslatma.visibility = View.VISIBLE

        }
        binding.ivDevam.setOnClickListener {
            binding.kronometre.base = SystemClock.elapsedRealtime() +zaman
            binding.kronometre.start()
            binding.ivDurdur.visibility = View.VISIBLE
            binding.ivDevam.visibility = View.INVISIBLE
            binding.ivBitir.visibility = View.INVISIBLE
        }

    }
}