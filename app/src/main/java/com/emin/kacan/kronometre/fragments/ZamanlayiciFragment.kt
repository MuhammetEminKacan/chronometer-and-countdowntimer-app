package com.emin.kacan.kronometre.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emin.kacan.kronometre.databinding.FragmentZamanlayiciBinding
import java.util.concurrent.TimeUnit

class ZamanlayiciFragment : Fragment() {
    private lateinit var binding: FragmentZamanlayiciBinding
    private lateinit var countDownTimer: CountDownTimer          // countdowntimer yapısını aldı
    private var kalanZaman: Long = 0
    private var calismaDurumu = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentZamanlayiciBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBaslat.setOnClickListener {
            if (!calismaDurumu) {
                val saat = binding.editTextSaat.text.toString().toLong()
                val dakika = binding.editTextDakika.text.toString().toLong()
                val saniye = binding.editTextSaniye.text.toString().toLong()

                kalanZaman = TimeUnit.HOURS.toMillis(saat)+
                        TimeUnit.MINUTES.toMillis(dakika) +
                        TimeUnit.SECONDS.toMillis(saniye)

                zamanlayiciBaslat(kalanZaman)
                calismaDurumu = true
                binding.ivBaslat.visibility = View.INVISIBLE
                binding.ivDuraklat.visibility = View.VISIBLE
                binding.ivSonlandir.visibility = View.VISIBLE
                binding.txtSure.visibility = View.VISIBLE
                binding.editTextSaat.visibility = View.INVISIBLE
                binding.editTextDakika.visibility = View.INVISIBLE
                binding.editTextSaniye.visibility = View.INVISIBLE
            }
        }

       binding.ivDuraklat.setOnClickListener {
            if (calismaDurumu) {
                countDownTimer.cancel()
                calismaDurumu = false
                binding.ivDuraklat.visibility = View.INVISIBLE
                binding.ivDevamEttir.visibility = View.VISIBLE
            }
        }

        binding.ivDevamEttir.setOnClickListener {
            zamanlayiciBaslat(kalanZaman)
            binding.ivDuraklat.visibility = View.VISIBLE
            binding.ivDevamEttir.visibility = View.INVISIBLE
        }

        binding.ivSonlandir.setOnClickListener {
            countDownTimer.cancel()
            calismaDurumu = false
            kalanZaman = 0
            sureTextGuncelleme()
            binding.ivDuraklat.visibility = View.INVISIBLE
            binding.ivDevamEttir.visibility = View.INVISIBLE
            binding.ivSonlandir.visibility = View.INVISIBLE
            binding.ivBaslat.visibility = View.VISIBLE
            binding.editTextSaat.visibility = View.VISIBLE
            binding.editTextDakika.visibility = View.VISIBLE
            binding.editTextSaniye.visibility = View.VISIBLE
            binding.txtSure.visibility = View.INVISIBLE
        }
    }

    private fun zamanlayiciBaslat(millisInFuture: Long) {
        countDownTimer = object : CountDownTimer(millisInFuture, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                kalanZaman = millisUntilFinished
                sureTextGuncelleme()
            }

            override fun onFinish() {
                calismaDurumu = false
            }
        }.start()
        calismaDurumu = true
    }

    private fun sureTextGuncelleme() {
        val saat = TimeUnit.MILLISECONDS.toHours(kalanZaman)
        val dakika = TimeUnit.MILLISECONDS.toMinutes(kalanZaman) % 60
        val saniye = TimeUnit.MILLISECONDS.toSeconds(kalanZaman) % 60

        val geriSayimMetni = String.format("%02d:%02d:%02d", saat, dakika, saniye)
        binding.txtSure.text = geriSayimMetni
    }
}