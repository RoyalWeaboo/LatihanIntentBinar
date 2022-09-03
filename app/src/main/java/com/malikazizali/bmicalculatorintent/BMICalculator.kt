package com.malikazizali.bmicalculatorintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class BMICalculator : AppCompatActivity() {
    lateinit var in_umur : EditText
    lateinit var in_tinggi : EditText
    lateinit var in_berat : EditText
    lateinit var bt_i : Button
    lateinit var bt_b : Button
    lateinit var bt_s : Button
    lateinit var bt_p : Button

    lateinit var umurUser : String
    lateinit var tinggiUser : String
    lateinit var beratUser : String
    var tinggiUserDalamMeter : Double = 0.0
    var tinggiUserKuadrat : Float = 0.0f
    var hasilBMI : Float = 0.0f
    lateinit var kategori : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_calculator)
        in_umur = findViewById(R.id.et_umur)
        in_tinggi = findViewById(R.id.et_tinggi)
        in_berat = findViewById(R.id.et_bb)
        bt_i = findViewById(R.id.btn_intent)
        bt_b = findViewById(R.id.btn_bundle)
        bt_s = findViewById(R.id.btn_ser)
        bt_p = findViewById(R.id.btn_par)

        bt_i.setOnClickListener{
            hitungBMI()
            sendIntent()
        }

        bt_b.setOnClickListener{
            hitungBMI()
            sendBundle()
        }

        bt_s.setOnClickListener{
            hitungBMI()
            sendSerializable()
        }

        bt_p.setOnClickListener{
            hitungBMI()
            sendParcelable()
        }
    }


    fun hitungBMI(){
        umurUser = in_umur.text.toString()
        tinggiUser = in_tinggi.text.toString()
        beratUser = in_berat.text.toString()

        tinggiUserDalamMeter = tinggiUser.toDouble()/100
        tinggiUserKuadrat = tinggiUserDalamMeter.toFloat()*tinggiUserDalamMeter.toFloat()
        hasilBMI = beratUser.toFloat()/tinggiUserKuadrat


        if(hasilBMI.toDouble() < 16){
            kategori = "Terlalu Kurus"
        }else if(hasilBMI.toDouble() in 16.1..17.0){
            kategori = "Cukup Kurus"
        }else if(hasilBMI.toDouble() in 17.1..18.5){
            kategori = "Sedikit Kurus"
        }else if(hasilBMI.toDouble() in 18.6..25.0){
            kategori = "Normal"
        }else if(hasilBMI.toDouble() in 25.1..30.0){
            kategori = "Gemuk"
        }else if(hasilBMI.toDouble() in 30.0..35.0){
            kategori = "Obesitas Kelas 1"
        }else if(hasilBMI.toDouble() in 35.1..40.0){
            kategori = "Obesitas Kelas 2"
        }else{
            kategori ="Obesitas Kelas 3"
        }
    }

    fun sendIntent(){
        val i = Intent(this, BMIResult::class.java)
        i.putExtra("method", "1")
        i.putExtra("umur", umurUser)
        i.putExtra("tinggi", tinggiUser)
        i.putExtra("berat", beratUser)
        i.putExtra("bmi", hasilBMI.toString())
        i.putExtra("kategori", kategori)
        startActivity(i)
    }

    fun sendBundle(){
        val bundleBMI = Bundle()
        val i = Intent(this, BMIResult::class.java)
        bundleBMI.putString("umur", umurUser)
        bundleBMI.putString("tinggi", tinggiUser)
        bundleBMI.putString("berat", beratUser)
        bundleBMI.putFloat("bmi", hasilBMI)
        bundleBMI.putString("kategori", kategori)

        i.putExtras(bundleBMI)
        i.putExtra("method", "2")
        startActivity(i)
    }

    fun sendSerializable(){
        val i = Intent(this, BMIResult::class.java)
        val dataSer = BMISer(umurUser, tinggiUser, beratUser, hasilBMI, kategori)
        i.putExtra("method", "3")
        i.putExtra("dataBMISerializable", dataSer)
        startActivity(i)
    }

    fun sendParcelable(){
        val i = Intent(this, BMIResult::class.java)
        val dataP = BMIPar(umurUser, tinggiUser, beratUser, hasilBMI, kategori)
        i.putExtra("method", "4")
        i.putExtra("dataBMIParcelable", dataP)
        startActivity(i)
    }
}