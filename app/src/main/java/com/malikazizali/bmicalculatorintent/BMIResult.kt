package com.malikazizali.bmicalculatorintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class BMIResult : AppCompatActivity() {
    lateinit var metode : TextView
    lateinit var umur : TextView
    lateinit var tinggi : TextView
    lateinit var berat : TextView
    lateinit var bmi : TextView
    lateinit var kategori : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiresult)

        metode = findViewById(R.id.tv_method)
        umur = findViewById(R.id.tv_umur)
        tinggi = findViewById(R.id.tv_tinggi)
        berat = findViewById(R.id.tv_bb)
        bmi = findViewById(R.id.tv_bmi)
        kategori = findViewById(R.id.tv_kategori)

        //cek metode yang dipakai
        val m = intent.getStringExtra("method")
        if(m == "1"){
            ambilDataIntent()
        }else if(m == "2"){
            ambilDataBundle()
        }else if(m == "3"){
            ambilDataSerializable()
        }else if(m == "4"){
            ambilDataParcelable()
        }
    }

    fun ambilDataIntent(){
        val dataUmur = intent.getStringExtra("umur")
        val dataTinggi = intent.getStringExtra("tinggi")
        val dataBerat = intent.getStringExtra("berat")
        val dataBMI = intent.getStringExtra("bmi")
        val dataKategori = intent.getStringExtra("kategori")

        metode.text = "Intent Normal"
        umur.text = dataUmur
        tinggi.text = dataTinggi
        berat.text = dataBerat
        bmi.text = dataBMI
        kategori.text = dataKategori
    }

    fun ambilDataBundle(){
        val bun = intent.extras
        metode.text = "Bundle"
        umur.text = bun!!.getString("umur")
        tinggi.text = bun.getString("tinggi")
        berat.text = bun.getString("berat")
        bmi.text = bun.getFloat("bmi").toString()
        kategori.text = bun.getString("kategori")
    }

    fun ambilDataSerializable(){
        val dataSer = intent.getSerializableExtra("dataBMISerializable") as BMISer
        metode.text = "Serializable"
        umur.text = dataSer.umur
        tinggi.text = dataSer.tinggi
        berat.text = dataSer.berat
        bmi.text = dataSer.bmi.toString()
        kategori.text = dataSer.kategori
    }

    fun ambilDataParcelable(){
        val dataPar = intent.getParcelableExtra("dataBMIParcelable") as BMIPar?
        metode.text = "Parcelable"
        umur.text = dataPar?.umur
        tinggi.text = dataPar?.tinggi
        berat.text = dataPar?.berat
        bmi.text = dataPar?.bmi.toString()
        kategori.text = dataPar?.kategori
    }

}